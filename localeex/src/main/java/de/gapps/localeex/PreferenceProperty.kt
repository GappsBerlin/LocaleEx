package de.gapps.localeex

import android.content.Context
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("FunctionName")
inline fun <PARENT : Context, reified VALUE_TYPE : Any?> PreferenceProperty(
    keyPrefs: String,
    keyValue: String,
    default: VALUE_TYPE,
    crossinline changedListener: (VALUE_TYPE) -> Unit = {}
) = object : ReadWriteProperty<PARENT, VALUE_TYPE> {

    @Suppress("UNCHECKED_CAST")
    var PARENT.valuePref: VALUE_TYPE
        get() {
            val sharedPrefs = getSharedPreferences(keyPrefs, Context.MODE_PRIVATE)
            return when (VALUE_TYPE::class) {
                Boolean::class -> sharedPrefs.getBoolean(keyValue, default as Boolean) as VALUE_TYPE
                Int::class -> sharedPrefs.getInt(keyValue, default as Int) as VALUE_TYPE
                Float::class -> sharedPrefs.getFloat(keyValue, default as Float) as VALUE_TYPE
                String::class -> sharedPrefs.getString(keyValue, default as String) as VALUE_TYPE
                Long::class -> sharedPrefs.getLong(keyValue, default as Long) as VALUE_TYPE
                Set::class -> sharedPrefs.getStringSet(
                    keyValue,
                    default as Set<String>
                ) as VALUE_TYPE
                else -> throw IllegalStateException("get() invalid type keyValue=$keyValue; type=${VALUE_TYPE::class}")
            }
        }
        set(value) {
            val sharedPrefs = getSharedPreferences(keyPrefs, Context.MODE_PRIVATE)
            when (VALUE_TYPE::class) {
                Boolean::class -> sharedPrefs.edit { putBoolean(keyValue, value as Boolean) }
                Int::class -> sharedPrefs.edit { putInt(keyValue, value as Int) }
                Float::class -> sharedPrefs.edit { putFloat(keyValue, value as Float) }
                String::class -> sharedPrefs.edit { putString(keyValue, value as String) }
                Long::class -> sharedPrefs.edit { putLong(keyValue, value as Long) }
                Set::class -> sharedPrefs.edit { putStringSet(keyValue, value as Set<String>) }
                else -> throw IllegalStateException("set($value) invalid type keyValue=$keyValue; type=${VALUE_TYPE::class}")
            }
        }

    override fun getValue(thisRef: PARENT, property: KProperty<*>): VALUE_TYPE = thisRef.valuePref

    override fun setValue(thisRef: PARENT, property: KProperty<*>, value: VALUE_TYPE) {
        if (value != thisRef.valuePref) {
            thisRef.valuePref = value
            changedListener(value)
        }
    }
}