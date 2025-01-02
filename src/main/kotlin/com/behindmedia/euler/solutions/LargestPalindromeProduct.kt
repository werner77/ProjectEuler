package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Solution
import kotlin.math.*

class LargestPalindromeProduct: Solution<Long> {

    private fun isPalindrome(x: Long): Boolean {
        val string = x.toString()
        return string == string.reversed()
    }

    override fun run(): Long {
        var best = 0L
        for (i in 100L..999L) {
            for (j in 100L..999L) {
                val value = i * j
                if (isPalindrome(value)) {
                    best = max(best, value)
                }

            }
        }
        return best
    }
}