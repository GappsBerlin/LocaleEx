 # LocaleEx
Android localization extension

[![](https://jitpack.io/v/SaschaZ/LocaleEx.svg)](https://jitpack.io/#SaschaZ/LocaleEx)


`LocaleEx` provides functionality to define an exclusive `Locale` in your app without changing the operating systems `Locale`.

Features:
  - applied `Locale` is stored persistent and is restored on app restart
  - to make sure the defined `Locale` is also set for your `Application`s `Context` and any or your `Service`s `Context`s the app is automatically restarted when a new `Locale` is set by the user
  - to fine tune `LocaleEx` for your needs nearly every action can be configured. see the `ILocaleExPreferences` interface and demo app for all available options and how they effect the `Locale`

Import:
```groovy
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
```groovy
dependencies {
  implementation "com.github.SaschaZ:LocaleEx:1.0.1"
}
```

To use `LocaleEx` you need to extends all of your `Activity`s from `LocaleExActivity` and you
`Application` from `LocaleExApplication`. If you do not have a custom `Application` please add
the following to your `application` tag in your `Manifest.xml`:
```xml
<application
    android:name="de.gapps.localeex.impl.LocaleExApplication"
```

Now you just need to call `applyLocale` to define you custom `Locale`:
```java
LocaleEx.apply { context.locale = Locale("en", "EN") }
```

If you do not want to extend your `Activity`s and `Application` from the `LocaleEx` ones you can
also let them implement `ILocaleEx by LocaleEx` and copy the appropriate `LocaleEx` calls
to your implementations. 
