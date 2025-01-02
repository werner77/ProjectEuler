package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Solution

class EvenFibonacciNumbers : Solution<Long> {
    override fun run(): Long {
        val dp = mutableListOf<Long>()
        dp += 1
        dp += 2
        var i = 2
        var sum = 2L
        while (true) {
            val next = dp[i - 1] + dp[i - 2]
            if (next > 4_000_000) {
                break
            }
            if (next % 2 == 0L) {
                sum += next
            }
            dp += next
            i++
        }
        return sum
    }
}