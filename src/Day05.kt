fun main() {
    val day = "05"

    // TIL: LongRange is a thing already, oh well
    data class Range<T : Comparable<T>>(val a: T, val b: T) {
        fun contains(n: T) = n in a..b
    }

    // not in the class because Comparable doesn't necessarily define subtraction...
    fun Range<Long>.size() = b - a + 1
    fun Range<Int>.size() = b - a + 1

    // pretty standard interval merging plus my bugs thrown in for free... I smell off-by-one errors...
    fun mergeIntervals(ranges: List<Range<Long>>): List<Range<Long>> {
        val sorted = ranges.sortedBy { it.a }

        val merged = mutableListOf<Range<Long>>()
        for (range in sorted) {
            if (merged.isEmpty() || merged.last().b < range.a - 1) {
                merged.add(range)
            } else {
                val last = merged.removeLast()
                merged.add(Range(last.a, maxOf(last.b, range.b)))
            }
        }

        return merged
    }

    fun part1(input: List<String>): Int {
        // val parts = input.joinToString("\n").split("\n\n")
        // Nope because TIL...
        val firstChunk = input.takeWhile { it.isNotEmpty() }
        val secondChunk = input.drop(firstChunk.size + 1) // or dropWhile { it.isNotEmpty() }.drop(1)

        val ranges: List<Range<Long>> = firstChunk.map { line ->
            val (a, b) = line.split("-").map { it.toLong() }
            Range(a, b)
        }
        val ingredients = secondChunk.map { it.toLong() }

        return ingredients.count { ingredient ->
            ranges.any { range -> range.contains(ingredient) }
        }
    }

    fun part2(input: List<String>): Long {
        val firstChunk = input.takeWhile { it.isNotEmpty() }

        val ranges: List<Range<Long>> = firstChunk.map { line ->
            val (a, b) = line.split("-").map { it.toLong() }
            Range(a, b)
        }

        val merged = mergeIntervals(ranges)

        return merged.sumOf { it.size() }
    }

    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    val t1 = part1(testInput)
    t1.log("t1 ")
    check(t1 == 3)

    val r1 = part1(input)
    r1.log("r1 ")
    check(r1 == 558)

    val t2 = part2(testInput)
    t2.log("t2 ")
    check(t2 == 14L)

    val r2 = part2(input)
    r2.log("r2 ")
    check(r2 == 344813017450467L)
}
