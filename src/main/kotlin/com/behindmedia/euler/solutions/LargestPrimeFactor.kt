package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Solution
import com.behindmedia.euler.common.max

class LargestPrimeFactor: Solution<Long> {

    override fun run(): Long {
        var number = 600851475143L

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