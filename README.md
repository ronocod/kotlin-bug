# kotlin-bug
Demo app to reproduce a Kotlin bug

### Bug

Running the app as it is in this repo will give you the following crash on startup:

```
E/AndroidRuntime: FATAL EXCEPTION: main
Process: conorodonnell.kotlinbug, PID: 19963
java.lang.NoClassDefFoundError: Failed resolution of: Lconorodonnell/kotlinbug/BugView$sam$rx_functions_Action1$0;
    at conorodonnell.kotlinbug.BugView.<init>(MainActivity.kt:36)
    at conorodonnell.kotlinbug.MainActivity.onCreate(MainActivity.kt:23)
    at android.app.Activity.performCreate(Activity.java:7009)
    at android.app.Activity.performCreate(Activity.java:7000)
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1214)
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2731)
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2856)
    at android.app.ActivityThread.-wrap11(Unknown Source:0)
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1589)
    at android.os.Handler.dispatchMessage(Handler.java:106)
    at android.os.Looper.loop(Looper.java:164)
    at android.app.ActivityThread.main(ActivityThread.java:6494)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:438)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:807)
 Caused by: java.lang.ClassNotFoundException: Didn't find class "conorodonnell.kotlinbug.BugView$sam$rx_functions_Action1$0" on path: DexPathList[[zip file "/data/app/conorodonnell.kotlinbug-6ln_4Ne9XRRgS-TXh7SB9Q==/base.apk"],nativeLibraryDirectories=[/data/app/conorodonnell.kotlinbug-6ln_4Ne9XRRgS-TXh7SB9Q==/lib/x86, /system/lib, /vendor/lib]]
    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:125)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at conorodonnell.kotlinbug.BugView.<init>(MainActivity.kt:36) 
    at conorodonnell.kotlinbug.MainActivity.onCreate(MainActivity.kt:23) 
    at android.app.Activity.performCreate(Activity.java:7009) 
    at android.app.Activity.performCreate(Activity.java:7000) 
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1214) 
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2731) 
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2856) 
    at android.app.ActivityThread.-wrap11(Unknown Source:0) 
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1589) 
    at android.os.Handler.dispatchMessage(Handler.java:106) 
    at android.os.Looper.loop(Looper.java:164) 
    at android.app.ActivityThread.main(ActivityThread.java:6494) 
    at java.lang.reflect.Method.invoke(Native Method) 
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:438) 
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:807)
  ```
  
If you search in the subdirectories of your `app/build/tmp/kotlin-classes` directory after a build, you'll see a number of `BugView*.class` files. The `BugView$sam$rx_functions_Action1$0` one will be missing.
  
If you follow the instructions in [BugView class](https://github.com/ronocod/kotlin-bug/blob/120838f73f16fe5050c92829aff4f2d681e2638b/app/src/main/java/conorodonnell/kotlinbug/MainActivity.kt#L48-L53), commenting out `A` and uncommenting `B`, you'll see the app compiles and runs fine. 
You can also switch the Kotlin version from 1.2.40 to 1.2.31 in the root `build.gradle` file and the app will compile and run fine.
In either case, the corresponding `.class` file for the missing class will be present with the other ones.
