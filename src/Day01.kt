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
//            position = ((position + direction * distance) % 100 + 100) % 100
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
    part1(testInput).println()
    check(part1(testInput) == 3)

    val input = readInput("Day01")
    part1(input).println()

    part2(testInput).println()
    check(part2(testInput) == 6)
    part2(input).println()
}
