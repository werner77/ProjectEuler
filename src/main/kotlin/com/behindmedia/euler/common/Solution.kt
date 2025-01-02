package com.behindmedia.euler.common

interface Solution <T> {
    fun run(): T
}

interface SolutionWithInput<T, I> {
    fun run(input: I): T
}
