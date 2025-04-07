# software-lab-2
هدف: پیاده سازی یک سیستم پردازش آنلاین پرداخت با مقیاس و اصول پیاده سازی شی گرا در چهار مرحله 


# مشکلات کد (Bad smell)

1- تخطی از DIP: تابع سطح بالای `PaymentProcessor` مستقیما به پیاده سازی های سطح پایین مثل `processCreditCard` یا `processDigitalWallet` وابسته است.

2- کد غیر منعطف: اگر روش پرداخت جدیدی اضافه شود، نیاز به تغییر `PaymentProcessor` خواهد بود. استفاده از `switch-case` نشان دهنده وابستگی شدید می باشد.

3- بیش از حد بزرگ بودن و مسئولیت چندگانه داشتن : کلاس `PaymentProcessor` چندین کار از قبیل اعتبار سنجی، پرداخت و لاگ گیری را درون خود دارد.

4- وجود متد ها با پارامتر های ورودی زیاد: وجود پارامتر های زیاد ورودی باعث می شود خوانایی کد پایین بیاید و نگهداری از آن دشوار می شود. می توان از یک شی مانند `PaymentRequest` برای مختصر کردن ورودی ها استفاده کرد.

5- وجود کد های تکراری: بخش های زیادی از توابع `processCreditCard`, `processDigitalWallet` و `processBankTransfer` دارای مشابهت می باشند.

6- استفاده نکردن از مکانیزم های کنترل `exception`: در توابعی که از `API` های خارجی استفاده می کنند، در صورتی که سرویس مورد نظر به هر دلیلی پاسخ مورد نظر را ندهد یا به مشکل بخورد، استفاده از مکانیزمی مانند `try-catch` می تواند کمک کننده باشد.




# بررسی اصول SOLID

1- اصل `SRP (single responsibility principle)`:

پردازش های مربوط به حالات متفاوت
payment
همگی در کلاس
PaymentProcessor
انجام میشود که در تعارض با این اصل است. می توان به ازای هر کدام از حالات یک کلاس جداگانه داشت
و عملیات های مربوط در آن ها انجام شوند.
همچنان عملیات 
validation 
باید در کلاسی جداگانه اتفاق بیافتد تا کلاس اصلی به کار های متفاوتی نپردازد.

2- اصل `OCP (open-closed principle)`:

از آن جایی که همواره
switch case
در تعارض با 
open-closed
است و در صورت اضافه شدن حالت به حالات پرداخت مجبوریم این کلاس را تغییر دهیم می توانیم به جای
switch case
از 
polymorphism
استفاده کنیم.
برای این کار باید به ازای هر حالت یک کلاس جداگانه ایجاد کنیم و به جای آن که
switch case
را در کلاس اصلی  بگذاریم در جایی دیگر مانند
factory
قرار دهیم. همچنان به جای 
switch case
در
factory
می توانیم از یک 
Map
استفاده کنیم که هر حالت پرداخت را به پیاده سازی مربوط وصل می کند.
در آن صورت کلاس فعلی تغییر نخواهد کرد و اصل
open closed
برای آن اعمال میشود.

همچنان نکته دیگری که وجود دارد مربوط به 
logging
است.
در حالت فعلی در جا های مختلف کد اطلاعات در کنسول 
print 
شده اند. لیکن اگر بخواهیم به جای کنسول در فایل بنویسیم باید تغییرات زیادی بدهیم
و در تعارض با این اصل است.
برای جلوگیری از این مورد می توان عملیات
logging
را به یک 
interface
ورودی بدهیم و این مسئولیت را در جای دیگری مدیریت کنیم.


3- اصل `LSP (liskov substitution principle)`: اصل `LSP` بیان می‌کند که اشیای یک کلاس فرزند باید بتوانند بدون ایجاد تغییر در رفتار، جایگزین کلاس والد خود شوند. در کد فعلی، `PaymentProcessor` به‌طور مستقیم وابسته به متدهای خاصی برای پردازش هر نوع پرداخت (`processCreditCard`, `processDigitalWallet`, `processBankTransfer`) است. این مسئله باعث می‌شود که اگر بخواهیم یک روش پرداخت جدید (مثلاً پرداخت از طریق رمزارز) اضافه کنیم، مجبور باشیم کد `processPayment` را تغییر دهیم. این نقض اصل LSP است، زیرا امکان جایگزینی و گسترش کلاس‌ها بدون تغییر در رفتار کلاس والد وجود ندارد.

برای اصلاح این مشکل، باید یک رابط مشترک (`PaymentMethod`) برای تمامی روش‌های پرداخت ایجاد کنیم. سپس هر روش پرداخت (مانند `CreditCardPayment`, `DigitalWalletPayment`, `BankTransferPayment`) از این اینترفیس پیروی کند و متد مربوط به پردازش پرداخت را درون خود پیاده‌سازی کند. در این حالت، `PaymentProcessor` به جای استفاده از `switch-case` برای تشخیص نوع پرداخت، فقط یک نمونه از `PaymentMethod` را دریافت کرده و متد پردازش را فراخوانی می‌کند. این تغییر باعث می‌شود که افزودن روش‌های جدید پرداخت، بدون تغییر در `PaymentProcessor` امکان‌پذیر شود و اصل `LSP` رعایت گردد.

4- اصل `ISP (interface segregation principle)`: اصل `ISP` بیان می‌کند که اینترفیس‌ها باید کوچک و تخصصی باشند، به‌طوری‌که پیاده‌سازی‌کنندگان مجبور به استفاده از متدهایی که نیاز ندارند، نشوند. در کد فعلی، کلاس `PaymentProcessor` چندین وظیفه مختلف را انجام می‌دهد: اعتبارسنجی پرداخت، پردازش پرداخت و ثبت گزارش تراکنش‌ها. این امر باعث می‌شود که اگر در آینده تغییری در یکی از این بخش‌ها لازم باشد، کل کلاس تحت تأثیر قرار بگیرد. همچنین، ممکن است برخی از پیاده‌سازی‌های آینده نیازی به بعضی از این متدها نداشته باشند، اما به‌ناچار مجبور به استفاده از آن‌ها شوند، که این نقض اصل ISP است.

برای برطرف کردن این مشکل، باید دو اینترفیس مستقل ایجاد کنیم: یکی برای اعتبارسنجی پرداخت `(PaymentValidator)` و دیگری برای پردازش پرداخت `(PaymentHandler)`. این کار باعث می‌شود که کلاس‌هایی که فقط به وظیفه خاصی نیاز دارند، فقط اینترفیس مربوطه را پیاده‌سازی کنند. مثلاً، اگر در آینده ماژول جدیدی برای پردازش پرداخت اضافه شود، نیازی به درگیر شدن با متدهای اعتبارسنجی نخواهد داشت. این تغییر علاوه بر افزایش انعطاف‌پذیری، باعث می‌شود که کلاس‌های مختلف فقط مسئولیت‌های مشخص خود را بر عهده بگیرند، که در نهایت منجر به کدی تمیزتر و قابل نگهداری‌تر خواهد شد.


5- اصل `DIP (dependency inversion principle)`: در کد داده شده، به دلیل وابستگی `PaymentProcessor` به توابع سطح پایین تری مانند `processCreditCard،` `processBandTransfer` و `processDigitalWallet` اصل `DIP` نقض شده و می تواند مشکل ساز شود. برای رفع این موضوع می توان از یک `interface` مانند `PaymentService` استفاده کرد و هر کدام از روش های از این رابط استفاده کنند و صرفا `PaymentProcessor` از این `interface` برای انجام کار ها استفاده کند.
