package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Solution

class MultiplesOfThreeOrFive: Solution<Long> {
    override fun run(): Long {
        var result: Long = 0
        for (i in 1 until 1000) {
            if (i % 3 == 0 || i % 5 == 0) {
                result += i.toLong()
            }
        }
        return result
    }
}