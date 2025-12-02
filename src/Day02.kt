import jdk.internal.org.jline.utils.Colors.s

fun main() {

    fun part1(input: List<String>): Long {
        var result = 0L
        input.forEach { range ->
            val (start, end) = range.split("-").map { it.toLong() }
            (start..end).forEach { n ->
                val s = n.toString()
                val l = s.length
                if (l % 2 == 0 && s.take(l / 2) == s.substring(l / 2)) result += n
            }
        }
        return result
    }

    val re = Regex("""(\d+)\1+""")

    fun part2(input: List<String>): Long {
        var result = 0L
        input.forEach { range ->
            val (start, end) = range.split("-").map { it.toLong() }
            (start..end).forEach { n ->
                if (re.matches(n.toString())) result += n
            }
        }
        return result
    }

    val testInput = readInput("Day02_test").first().split(",")
    val input = readInput("Day02").first().split(",")

    val t1 = part1(testInput)
    t1.println()
    check(t1 == 1227775554L)

    val r1 = part1(input)
    r1.println()
    check(r1 == 23534117921L)

    val t2 = part2(testInput)
    t2.println()
    check(t2 == 4174379265L)

    val r2 = part2(input)
    r2.println()
    check(r2 == 31755323497L)
}
