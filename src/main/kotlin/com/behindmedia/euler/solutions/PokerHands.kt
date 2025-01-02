package com.behindmedia.euler.solutions

import com.behindmedia.euler.common.SolutionWithInput
import com.behindmedia.euler.common.splitTrimmed

private data class Card(val suit: Char, val value: Char) : Comparable<Card> {
    val score: Int
        get() = cardOrder[value]!!

    companion object {

        // We need 4 bits to represent a card score
        private val cardOrder = mapOf<Char, Int>(
            '2' to 1,
            '3' to 2,
            '4' to 3,
            '5' to 4,
            '6' to 5,
            '7' to 6,
            '8' to 7,
            '9' to 8,
            'T' to 9,
            'J' to 10,
            'Q' to 11,
            'K' to 12,
            'A' to 13,
        )

        operator fun invoke(encoded: String): Card {
            require(encoded.length == 2) {
                "Invalid card: $encoded"
            }
            val value = encoded[0]
            val suit = encoded[1]
            return Card(suit, value)
        }
    }

    override fun compareTo(other: Card): Int {
        return this.score.compareTo(other.score)
    }
}

private data class Hand(val cards: List<Card>) : Comparable<Hand> {
    private val cardsBySuit = cards.groupBy { it.suit }
    private val cardsByValue = cards.groupBy { it.value }

    companion object {
        operator fun invoke(encoded: List<String>): Hand {
            val cards = encoded.map { Card(it) }
            return Hand(cards)
        }
    }

    val score: Long
        get() {
            var score = 0L
            val flushHighCard = if (cardsBySuit.size == 1) cards.max() else null
            val straightHighCard = if (cardsByValue.size == 5 && cards.minOf { it.score } == cards.maxOf { it.score } - 4) cards.max() else null
            val quadCard = cardsByValue.values.firstOrNull { it.size == 4 }?.first()
            val tripleCard = cardsByValue.values.firstOrNull { it.size == 3 }?.first()
            val pairs = cardsByValue.values.filter { it.size == 2 }
            val restCards = cards.toMutableSet()
            if (flushHighCard != null && straightHighCard != null) {
                // Straight flush
                score = 1L shl (4 * 15)
                score += flushHighCard.score shl (4 * 6)
                restCards.clear()
            } else if (quadCard != null) {
                // Quads
                score = 1L shl (4 * 14)
                score += quadCard.score shl (4 * 6)
                restCards.removeIf { it.value == quadCard.value }
            } else if (tripleCard != null && pairs.size == 1) {
                // Full House
                val pairCard = pairs.single().first()
                score = 1L shl (4 * 13)
                score += tripleCard.score shl (4 * 7)
                score += pairCard.score shl (4 * 6)
                restCards.clear()
            } else if (flushHighCard != null) {
                score = 1L shl (4 * 12)
                score += flushHighCard.score shl (4 * 6)
                restCards.clear()
            } else if (straightHighCard != null) {
                score = 1L shl (4 * 11)
                score += straightHighCard.score shl (4 * 6)
                restCards.clear()
            } else if (tripleCard != null) {
                score = 1L shl (4 * 10)
                score += tripleCard.score shl (4 * 6)
                restCards.removeIf { it.value == tripleCard.value }
            } else if (pairs.size == 2) {
                score = 1L shl (4 * 9)
                val maxPairCard = pairs.maxBy { it.first().score }.first()
                score += maxPairCard.score shl (4 * 7)
                val minPairCard = pairs.minBy { it.first().score }.first()
                score += minPairCard.score shl (4 * 6)
                restCards.removeIf { it.value == maxPairCard.value || it.value == minPairCard.value }
            } else if (pairs.size == 1) {
                val pairCard = pairs.single().first()
                score = 1L shl (4 * 8)
                score += pairCard.score shl (4 * 6)
                restCards.removeIf { it.value == pairCard.value }
            }
            score += restCards.sorted().withIndex().sumOf { (i, c) -> c.score shl (4 * i) }
            return score
        }

    override fun compareTo(other: Hand): Int {
        return this.score.compareTo(other.score)
    }
}

class PokerHands : SolutionWithInput<Long, String> {

    override fun run(input: String): Long {
        var ans = 0L
        val lines = input.split("\n")
        for (line in lines) {
            val hands = line.splitTrimmed(" ")
            val (hand1, hand2) = hands.chunked(5).map { Hand(it) }
            val comparisonResult = hand1.compareTo(hand2)
            if (comparisonResult > 0) {
                ans ++
            } else {
                require(comparisonResult < 0)
            }
        }
        return ans
    }
}