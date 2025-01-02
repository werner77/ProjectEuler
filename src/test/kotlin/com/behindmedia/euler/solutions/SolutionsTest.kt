package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.read
import kotlin.test.Test
import kotlin.test.assertEquals

class SolutionsTest {
    @Test
    fun multipleOfThreeAndFive() {
        val solution = MultiplesOfThreeOrFive().run()
        println(solution)
        assertEquals(233168L, solution)
    }

    @Test
    fun evenFibonacci() {
        val solution = EvenFibonacciNumbers().run()
        println(solution)
        assertEquals(4613732L, solution)
    }

    @Test
    fun largestPrimeFactors() {
        val solution = LargestPrimeFactor().run()
        println(solution)
        assertEquals(6857, solution)
    }

    @Test
    fun largestPalindromeProduct() {
        val solution = LargestPalindromeProduct().run()
        println(solution)
        assertEquals(906609L, solution)
    }

    @Test
    fun smallestMultiple() {
        val solution = SmallestMultiple().run()
        println(solution)
        assertEquals(232792560, solution)
    }

    @Test
    fun primePairSets() {
        val solution = PrimePairSets().run()
        println(solution)
        assertEquals(26033, solution)
    }

    @Test
    fun pokerHands() {
        val input = read("/pokerhands.txt")
        val solution = PokerHands().run(input = input)
        println(solution)
    }

}