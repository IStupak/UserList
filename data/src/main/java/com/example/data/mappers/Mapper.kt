package com.example.data.mappers

interface Mapper<T, R> {
    fun convert(data: T): R
}