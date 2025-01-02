package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.Primes
import com.behindmedia.euler.common.Solution
import com.behindmedia.euler.common.defaultMutableMapOf
import com.behindmedia.euler.common.findAllMaximalCliques

private const val N = 1_000_000

class PrimePairSets : Solution<Long> {

    private val primes = Primes(N)
    private val cache = mutableMapOf<Long, Boolean>()

    private fun isPrime(a: Long): Boolean {
        return cache.getOrPut(a) {
            primes.isPrime(a)
        }
    }

    private fun check(first: Int, second: Int): Boolean {
        val value = (first.toString() + second.toString()).toLong()
        return isPrime(value)
    }

    override fun run(): Long {
        // We're building up a graph
        val connections = defaultMutableMapOf<Int, MutableSet<Int>>(true) { mutableSetOf() }
        val primes = primes.values
        var lowerBound = 1
        var upperBound = 2
        while (true) {
            for (i in lowerBound until upperBound) {
                val first = primes[i]
                for (j in 0 until i) {
                    val second = primes[j]
                    if (check(first, second) && check(second, first)) {
                        // Found connection
                        val firstSet = connections[first]
                        val secondSet = connections[second]
                        firstSet += second
                        secondSet += first
                    }
                }
            }
            val maxClique = findAllMaximalCliques(connections).filter { it.size > 4 }.minByOrNull { clique ->
                clique.sumOf { it.toLong() }
            }
            if (maxClique != null) {
                println(maxClique)
                return maxClique.sumOf { it.toLong() }
            }
            lowerBound = upperBound
            upperBound *= 2
        }
    }
}