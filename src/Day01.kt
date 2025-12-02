fun main() {
    val size = 100
    val target = 0

    fun part1(input: List<String>): Int {
        var position = 50;
        var hits = 0;
        input.forEach {
            val direction = if (it[0] == 'R') +1 else -1
            val distance = it.substring(1).toInt()
            position = (position + (direction * distance)).mod(size)
            if (position == target) hits += 1
        }
        return hits
    }

    fun part2(input: List<String>): Int {
        var position = 50;
        var hits = 0;
        input.forEach { line ->
            val direction = if (line[0] == 'R') +1 else -1
            val distance = line.substring(1).toInt()
            (1..distance).forEach {
                position = (position + direction).mod(size)
                if (position == target) hits += 1
            }
        }
        return hits
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    val t1 = part1(testInput)
    t1.println()
    check(t1 == 3)

    val r1 = part1(input)
    r1.println()
    check(r1 == 995)

    val t2 = part2(testInput)
    t2.println()
    check(t2 == 6)

    val r2 = part2(input)
    r2.println()
    check(r2 == 5847)
}
