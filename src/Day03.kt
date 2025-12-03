fun main() {

    fun part1(input: List<String>): Long {
        var result = 0L
        input.forEach { line ->
            // highest first digit, but it can't be the last digit
            val a = line.take(line.length - 1).map { it.digitToInt() }.sortedDescending().take(1).map { it.digitToChar() }[0]
            // second highest after that first one
            val b = line.substring(line.indexOfFirst { it == a } + 1).map { it.digitToInt() }.sortedDescending().take(1).map { it.digitToChar() }[0]

            result += a.digitToInt() * 10 + b.digitToInt()
        }
        return result
    }

    fun part2(input: List<String>): Long {
        var result = 0L
        input.forEach { line ->
            var s = ""
            var start = 0
            // highest first digit, but it can't be in the last 11, 10, 9, etc
            (11 downTo 0).forEach { skip ->
                val candidateLine = line.substring(start).take(line.length - start - skip)
                val nextLargest = candidateLine.map { it.digitToInt() }.sortedDescending().take(1).map { it.digitToChar() }[0]
                start += candidateLine.indexOfFirst { it == nextLargest } + 1
                s += nextLargest.toString()
            }
            result += s.toLong()
        }
        return result
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    val t1 = part1(testInput)
    t1.println()
    check(t1 == 357L)

    val r1 = part1(input)
    r1.println()
    check(r1 == 17074L)

    val t2 = part2(testInput)
    t2.println()
    check(t2 == 3121910778619L)

    val r2 = part2(input)
    r2.println()
    check(r2 == 169512729575727L)
}
