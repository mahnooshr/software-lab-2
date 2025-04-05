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


2- اصل `OCP (open-closed principle)`:


3- اصل `LSP (liskov substitution principle)`:


4- اصل `ISP (interface segregation principle)`:


5- اصل `DIP (dependency inversion principle)`: در کد داده شده، به دلیل وابستگی `PaymentProcessor` به توابع سطح پایین تری مانند `processCreditCard،` `processBandTransfer` و `processDigitalWallet` اصل `DIP` نقض شده و می تواند مشکل ساز شود. برای رفع این موضوع می توان از یک `interface` مانند `PaymentService` استفاده کرد و هر کدام از روش های از این رابط استفاده کنند و صرفا `PaymentProcessor` از این `interface` برای انجام کار ها استفاده کند.