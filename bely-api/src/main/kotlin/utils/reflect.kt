package com.elouyi.bely.utils

import kotlin.jvm.Throws
import kotlin.reflect.KClass

/*@Throws(Exception::class)
fun <T,R> newInstance(t: T,vararg params: Any? = arrayOf()): R where T: KClass<out R> {

}*/

fun <T,R> T.newInstance(vararg params: Any? = arrayOf()): R where T: KClass<out R> {
    try {
        objectInstance?.let {
            return it
        }
    } catch (e: Exception){}
    for (constructor in constructors) {
        try {
            return constructor.call(*params)
        } catch (e: Exception) {}
    }
    throw Exception("无法实例化${qualifiedName}")
}