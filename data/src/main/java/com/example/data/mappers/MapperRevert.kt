package com.example.data.mappers

interface MapperRevert<T, R> {
    fun convert(data: T): R
    fun revert(data: R): T
}