 # LocaleEx
Android localization extension

[![](https://jitpack.io/v/SaschaZ/LocaleEx.svg)](https://jitpack.io/#SaschaZ/LocaleEx)


`LocaleEx` provides functionality to define an exclusive `Locale` in your app without changing the Android system `Locale`.

Features:
  - applied `Locale` is stored persistent and is restored on app restart
  - to make sure the defined `Locale` is also set for your `Application`s `Context` and any or your `Service`s `Context`s the app is automatically restarted when a new `Locale` is set by the user
  - to fine tune `LocaleEx` for your needs nearly every action can be configured. see the `ILocaleExPreferences` interface and demo app for all available options and how they effect the `Locale`

Import:
  - root build.gradle:
    ```groovy
    allprojects {
      repositories {
        ...
        maven { url "https://jitpack.io" }
      }
    }
    ```
  - app build.gradle:
    ```groovy
    dependencies {
      implementation "com.github.SaschaZ:LocaleEx:1.0.5"
    }
    ```

To use <a href="https://github.com/SaschaZ/LocaleEx/blob/master/localeex/src/main/java/de/gapps/localeex/LocaleEx.kt">`LocaleEx`</a> you need to extend all of your `Activity`s from <a href="https://github.com/SaschaZ/LocaleEx/blob/master/localeex/src/main/java/de/gapps/localeex/impl/LocaleExActivity.kt">`LocaleExActivity`</a> and your
`Application` from <a href="https://github.com/SaschaZ/LocaleEx/blob/master/localeex/src/main/java/de/gapps/localeex/impl/LocaleExApplication.kt">`LocaleExApplication`</a>. If you do not have a custom `Application` please add
the following to your `application` tag in your `Manifest.xml`:
```xml
<application
    android:name="de.gapps.localeex.impl.LocaleExApplication"
```

Now you can use the <a href="https://github.com/SaschaZ/LocaleEx/blob/03c73036c063b1e73d5cbe3b7612721f56192b5e/localeex/src/main/java/de/gapps/localeex/LocaleEx.kt#L58">`LocaleEx.locale`</a> property to define your custom `Locale`:
```java
LocaleEx.apply { context.locale = Locale("en", "EN") }
```
Be sure to apply any custom settings to <a href="https://github.com/SaschaZ/LocaleEx/blob/master/localeex/src/main/java/de/gapps/localeex/preferences/LocaleExPreferences.kt">`LocaleExPreferences`</a> before setting your custom `Locale`.
<br><br>
If you do not want to extend your `Activity`s and `Application` from the `LocaleEx` ones you can
also let them implement `ILocaleEx by LocaleEx` and copy the appropriate `LocaleEx` calls
to your implementations. 
<br><br>
Known issues:
- All views that are created directly by Android and not by the app (permission request dialogs for example) still using the system locale.  