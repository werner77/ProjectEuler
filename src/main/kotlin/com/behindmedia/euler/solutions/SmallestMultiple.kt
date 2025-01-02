package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Solution
import com.behindmedia.euler.common.leastCommonMultiple

class SmallestMultiple: Solution<Long> {
    override fun run(): Long {
        var result = 1L
        for (i in 1L..20L) {
            result = leastCommonMultiple(result, i)
        }
        return result
    }
}