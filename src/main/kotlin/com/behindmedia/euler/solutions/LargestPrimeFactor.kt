package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.SolutionWithInput
import com.behindmedia.euler.common.max

class LargestPrimeFactor: SolutionWithInput<Long, Long> {

    fun run() = run(input = 600851475143L)

    override fun run(input: Long): Long {
        var number = input

        // Divide the number by each prime we know
        val factors = mutableListOf<Long>()
        var i = 2L
        while (i <= number) {
            while (number % i == 0L) {
                factors += i
                number /= i
            }
            i++
        }
        return factors.max()
    }
}