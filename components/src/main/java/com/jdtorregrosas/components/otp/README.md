# OtpView Component
Easy capturing OTP codes of n digits with the selected customization

![otp gif preview](./otpGif.gif)
## Customization
- Text color
- Text size
- Underline color
- Placeholder color and text
- Number of fields
- Space between fields

## How to use it
1. Import the library component (see library README)
2. Create the component view on the XML file as follows:
```xml
    <com.jdtorregrosas.components.otp.OtpView xmlns:otp="http://schemas.android.com/apk/res-auto"
        otp:hint="|"
        otp:hintColor="#eeaaee"
        otp:size="4"
        otp:spaceBetween="10dp"
        otp:textColor="#ff00ff"
        otp:textSize="20sp"
        otp:tintColor="#aa0"
        otp:enableSms="true"
        otp:smsKeyWord="Ualet"/>
```

The table explains each attribute:

|     Attribute     |           Description              |
| ----------------- | ---------------------------------- |
| hint              | Sets the placeholder symbol        |
| hintColor         | Sets the placeholder color         |
| size              | Sets the number of fields          |
| spaceBetween      | Sets the margin beteween fields    |
| textColor         | Sets the text color                |
| textSize          | Sets the text size dimensions      |
| tintColor         | Sets the underline color           |
| enableSms         | Enables autofill from sms feature  |
| smsKeyWord        | Sets keyword from sms for autofill |

3. Customize what you need with the custom attributes
4. If you'll use autofill you must to register the receiver and unregister it like this:

(Kotlin)
```kotlin
        override fun onCreate(savedInstanceState: Bundle?) {
                ...
                otpView.registerReceiver(this)
                ...
        }
        override fun onPause() {
                ...
                otpView.unregisterReceiver(this)
                ...
        }
        override fun onDestroy() {
                ...
                otpView.unregisterReceiver(this)
                ...
        }
```
(java)
```java
        public void onCreate(Bundle savedInstanceState)
        {
                ...
                otpView.registerReceiver(this);
                ...
        }
        public void onPause()
        {
                ...
                otpView.unregisterReceiver(this);
                ...
        }
        public void onDestroy()
        {
                ...
                otpView.unregisterReceiver(this);
                ...
        }
```

Add permissions on manifest

```xml
        <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
        <uses-permission android:name="android.permission.READ_SMS" />
```
Note:
If you need to support android 6+ you'll need to ask for permissions to the user like this:

(Kotlin)
```kotlin
        private val REQUEST_SMS_RECEIVE = 1550
        override fun onCreate(savedInstanceState: Bundle?) {
                ...
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), REQUEST_SMS_RECEIVE)
                ...
        }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                if (requestCode == REQUEST_SMS_RECEIVE) {
                        otpView.registerReceiver(this)
                }
        }
    }
```
(java)
```java
        private val REQUEST_SMS_RECEIVE = 1550;
        public void onCreate(Bundle savedInstanceState)
        {
                ...
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_SMS_RECEIVE);
                ...
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if (requestCode == REQUEST_SMS_RECEIVE) {
                        otpView.registerReceiver(this)
                }
        }
```

5. To get the code from the inputs you can use the function ```getCode():String```

5. Enjoy!


