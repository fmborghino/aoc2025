fun main() {
    open class Vector(val input: List<String>) {
        val width = input[0].length
        val height = input.size
        fun at(x: Int, y: Int): Char? = if (y in 0..<height && x in 0..<width) input[y][x] else null
        fun neighborsCardinal(x: Int, y: Int) = listOf(at(x, y - 1), at(x + 1, y), at(x, y + 1),  at(x - 1, y))
        fun neighborsDiagonal(x: Int, y: Int) = listOf(at(x - 1, y - 1), at(x - 1, y + 1), at(x + 1, y + 1), at(x + 1, y - 1))
        fun neighbors(x: Int, y: Int) = neighborsCardinal(x, y) + neighborsDiagonal(x, y)
        fun neighborsOccupied(x: Int, y: Int, c: Char) = neighbors(x, y).count { it == c }
        fun iterateAll() = sequence {
            for (y in 0..<height) {
                for (x in 0..<width) {
                    yield(x to y)
                }
            }
        }
        fun printVector() = input.forEach { println(it) }
    }

    class MutableVector(private val mutableInput: MutableList<String>) : Vector(mutableInput) {
        fun write(x: Int, y: Int, c: Char) { mutableInput[y] = mutableInput[y].replaceRange(x, x + 1, c.toString()) }
    }

    val day = "04"

    fun part1(input: List<String>): Int {
        val vector = Vector(input)
        val locationsWithItems = vector.iterateAll().filter { (x, y) -> vector.at(x, y) == '@' }
        val count = locationsWithItems.count { (x, y) -> vector.neighborsOccupied(x, y, '@') < 4 }
        return count
    }

    fun part2(input: List<String>): Int {
        val vector = MutableVector(input.toMutableList())
        var count = 0

        do {
            val locationsWithItems = vector.iterateAll().filter { (x, y) -> vector.at(x, y) == '@' }
            val removables = locationsWithItems.filter { (x, y) -> vector.neighborsOccupied(x, y, '@') < 4 }.toList()
            val changes = removables.size
            removables.forEach { (x, y) -> vector.write(x, y, 'x') }
            count += changes
        } while(changes > 0)

        return count
    }

    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    val t1 = part1(testInput)
    t1.println()
    check(t1 == 13)

    val r1 = part1(input)
    r1.println()
    check(r1 == 1424)

    val t2 = part2(testInput)
    t2.println()
    check(t2 == 43)

    val r2 = part2(input)
    r2.println()
    check(r2 == 8727)
}
