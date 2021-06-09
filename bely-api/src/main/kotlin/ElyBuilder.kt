package com.elouyi.bely

interface ElyBuilder<out R> {
    fun build(): R
}