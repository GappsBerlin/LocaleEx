package de.gapps.localeex

import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("FunctionName")
inline fun <PARENT : Any, reified VALUE_TYPE : Any?> ISharedPreferenceHolder.PreferenceProperty(
    key: String,
    default: VALUE_TYPE,
    crossinline changedListener: (VALUE_TYPE) -> Unit = {}
) = object : ReadWriteProperty<PARENT, VALUE_TYPE> {

    @Suppress("UNCHECKED_CAST")
    var valuePref: VALUE_TYPE
        get() = when (VALUE_TYPE::class) {
            Boolean::class -> sharedPrefs.getBoolean(key, default as Boolean) as VALUE_TYPE
            Int::class -> sharedPrefs.getInt(key, default as Int) as VALUE_TYPE
            Float::class -> sharedPrefs.getFloat(key, default as Float) as VALUE_TYPE
            String::class -> sharedPrefs.getString(key, default as String) as VALUE_TYPE
            Long::class -> sharedPrefs.getLong(key, default as Long) as VALUE_TYPE
            Set::class -> sharedPrefs.getStringSet(key, default as Set<String>) as VALUE_TYPE
            else -> throw IllegalStateException("get() invalid type key=$key; type=${VALUE_TYPE::class}")
        }
        set(value) = when (VALUE_TYPE::class) {
            Boolean::class -> sharedPrefs.edit { putBoolean(key, value as Boolean) }
            Int::class -> sharedPrefs.edit { putInt(key, value as Int) }
            Float::class -> sharedPrefs.edit { putFloat(key, value as Float) }
            String::class -> sharedPrefs.edit { putString(key, value as String) }
            Long::class -> sharedPrefs.edit { putLong(key, value as Long) }
            Set::class -> sharedPrefs.edit { putStringSet(key, value as Set<String>) }
            else -> throw IllegalStateException("set($value) invalid type key=$key; type=${VALUE_TYPE::class}")
        }

    override fun getValue(thisRef: PARENT, property: KProperty<*>): VALUE_TYPE = valuePref

    override fun setValue(thisRef: PARENT, property: KProperty<*>, value: VALUE_TYPE) {
        if (value != valuePref) {
            valuePref = value
            changedListener(value)
        }
    }
}