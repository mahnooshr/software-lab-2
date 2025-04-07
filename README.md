# software-lab-2
هدف: پیاده سازی یک سیستم پردازش آنلاین پرداخت با مقیاس و اصول پیاده سازی شی گرا در چهار مرحله 

# فاز ۲: بازآرایی و انتزاع

در فاز دوم پروژه، کد به منظور بهبود ساختار و رعایت اصول طراحی شیءگرای SOLID بازآرایی شد. هدف اصلی این فاز معرفی انتزاع و کپسوله‌ سازی منطق پردازش پرداخت‌ ها بود. در این مرحله، کلاس‌ های خاص برای هر نوع پرداخت (کارت اعتباری، کیف پول دیجیتال، و انتقال بانکی) ایجاد شده‌ اند.

## تغییرات اعمال شده
### ۱. ایجاد کلاس انتزاعی Payment:

کلاس Payment به عنوان یک کلاس انتزاعی برای تمام انواع پرداخت‌ ها طراحی شده است. این کلاس ویژگی‌ های مشترک مانند `amount`, `currency`, `customerInfo`, و `paymentDetails` را تعریف می‌ کند. همچنین متد های انتزاعی `()validatePayment` و `()processPayment` را برای پیاده‌سازی در کلاس‌های زیرمجموعه تعیین کرده است.

### ۲. ایجاد زیرکلاس‌ها برای هر نوع پرداخت:

سه کلاس زیرمجموعه برای انواع مختلف پرداخت‌ ها ایجاد شدند:

`CreditCardPayment`: برای پردازش پرداخت‌های با کارت اعتباری

`DigitalWalletPayment`: برای پردازش پرداخت‌های با کیف پول دیجیتال

`BankTransferPayment`: برای پردازش پرداخت‌های با انتقال بانکی




هر کدام از این کلاس‌ ها متد های `()validatePayment` و `()processPayment` را به‌ طور خاص خود پیاده‌ سازی کرده‌ اند.

### معرفی زیرکلاس `CreditCardPayment`:

این کلاس مسئول پردازش پرداخت‌ های با استفاده از کارت‌ های اعتباری است. این زیر کلاس از کلاس انتزاعی Payment ارث‌ بری می‌ کند و متدهای `()validatePayment` و `()processPayment` را به‌طور خاص برای پرداخت با کارت اعتباری پیاده‌ سازی می‌ کند.


ویژگی‌ ها:

`amount`: مبلغ پرداخت که از طریق سازنده دریافت می‌ شود

`currency`: واحد ارزی که برای پرداخت استفاده می‌ شود 

`customerInfo`: اطلاعات مشتری (مثلاً نام و ایمیل)

`paymentDetails`: جزئیات پرداخت

`config`:API تنظیمات برای اتصال به  پردازش کارت اعتباری


متد های اصلی:

`validatePayment()`: این متد بررسی می‌کند که آیا اطلاعات کارت اعتباری  معتبر است یا خیر

`processPayment()`: این متد پردازش پرداخت با کارت اعتباری را انجام می‌دهد


### معرفی زیرکلاس `DigitalWalletPayment`:

این کلاس مسئول پردازش پرداخت ها با استفاده از کیف پول دیجیتال می باشد. این کلاس مانند کلاس `CreditCardPayment` از کلاس `Payment` ارث بری می کند و متد های آن را `override` می کند.

فیلد های زیر در این کلاس استفاده می شوند:

`amount`: مبلغ مورد نظر برای پرداخت از طریق کیف پول

`currency`: واحد پول استفاده شده در پرداخت

`customerInfo`: اطلاعات پرداخت کننده

`paymentDetails`: جزئیات پرداخت از جمله شناسه کیف پول

`config`: پیکربندی مورد استفاده برای استفاده از درگاه پردازش کیف پول دیجیتال

متد های کلاس:

`validatePayment()`:

.

 از این متد برای مطمئن شدن از وجود شناسه کیف پول در `paymentDetails` استفاده می شود.

`processPayment()`: 

این متد پس از اتصال به API کیف پول دیجیتال، پرداخت را پردازش می کند.



### معرفی زیرکلاس `BankTransferPayment`:


این کلاس مسئول پردازش پرداخت ها به وسیله پرداخت بانکی می باشد. این کلاس مانند کلاس `CreditCardPayment` از کلاس `Payment` ارث بری می کند و متد های آن را `override` می کند.

فیلد های زیر در این کلاس استفاده می شوند:

`amount`: مبلغ مورد نظر برای پرداخت از طریق کیف پول

`currency`: واحد پول استفاده شده در پرداخت

`customerInfo`: اطلاعات پرداخت کننده

`paymentDetails`: جزئیات پرداخت از جمله شناسه کیف پول

`config`: پیکربندی مورد استفاده برای استفاده از درگاه پردازش کیف پول دیجیتال

متد های کلاس:

`validatePayment()`:

.

 از این متد برای مطمئن شدن از وجود شناسه کیف پول در `paymentDetails` استفاده می شود.

`processPayment()`: 

این متد پس از اتصال به API کیف پول دیجیتال، پرداخت را پردازش می کند.



# فاز ۳: وراثت و چندریختی با رابط `PaymentGateway`

در فاز سوم پروژه، یک رابط به نام `PaymentGateway` برای جداسازی یکپارچه‌ سازی درگاه‌ های پرداخت مختلف از منطق اصلی پرداخت را پیاده سازی کردیم. این رابط به ما این امکان را می‌ دهد که منطق پردازش پرداخت را از جزئیات پیاده‌ سازی درگاه‌ های پرداخت جدا کنیم و امکان گسترش سیستم را با اضافه کردن درگاه‌ های جدید فراهم کنیم.

## ۱. طراحی رابط PaymentGateway:

یک رابط به نام `PaymentGateway` تعریف شد که متد های لازم برای پردازش پرداخت، بازپرداخت و دریافت وضعیت تراکنش‌ ها را در خود دارد.

`processPayment`: برای پردازش یک تراکنش پرداخت

`refundPayment`: برای پردازش بازپرداخت تراکنش‌ها

`getTransactionStatus`: برای دریافت وضعیت یک تراکنش


#  ۲. پیاده‌ سازی درگاه‌ های مشخص:

در این مرحله، درگاه‌ های پرداخت مختلف مانند `StripeGateway` و `PayPalGateway` که از رابط `PaymentGateway` پیروی می‌ کنند، طراحی و پیاده‌ سازی شدند.


