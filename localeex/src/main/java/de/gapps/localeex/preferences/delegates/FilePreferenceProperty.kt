package de.gapps.localeex.preferences.delegates

import android.content.Context
import java.io.File
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("FunctionName")
inline fun <PARENT : Context, reified VALUE_TYPE : Any?> FilePreferenceProperty(
    keyValue: String,
    default: VALUE_TYPE,
    crossinline changedListener: (VALUE_TYPE) -> Unit = {}
) = object : ReadWriteProperty<PARENT, VALUE_TYPE> {

    override fun getValue(thisRef: PARENT, property: KProperty<*>): VALUE_TYPE {
        val file = File(thisRef.filesDir, keyValue)
        val stringValue = if (file.exists()) file.readText().split("=")[1] else null
        return when (VALUE_TYPE::class) {
            Boolean::class -> (stringValue?.toBoolean() ?: default) as VALUE_TYPE
            Int::class -> (stringValue?.toInt() ?: default) as VALUE_TYPE
            Float::class -> (stringValue?.toFloat() ?: default) as VALUE_TYPE
            String::class -> (stringValue ?: default) as VALUE_TYPE
            Long::class -> (stringValue?.toLong() ?: default) as VALUE_TYPE
            else -> throw IllegalStateException("get() invalid type keyValue=$keyValue; type=${VALUE_TYPE::class}")
        }
    }

    override fun setValue(thisRef: PARENT, property: KProperty<*>, value: VALUE_TYPE) {
        if (value != getValue(thisRef, property)) {
            File(thisRef.filesDir, keyValue).writeText("value=$value")
            changedListener(value)
        }
    }
}