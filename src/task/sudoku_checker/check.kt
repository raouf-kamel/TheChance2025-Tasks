package task.sudoku_checker
fun main() {
    val valid4x4Sudoku = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', '1'),
        listOf('4', '1', '2', '3')
    )

    val invalid4x4Sudoku = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', '4'), //   الرقم 4 مكرر
        listOf('4', '1', '2', '3')
    )

    val sudokuWithEmptyCells = listOf(
        listOf('1', '2', '-', '4'),
        listOf('3', '4', '1', '-'),
        listOf('2', '-', '4', '1'),
        listOf('-', '1', '2', '3')
    )

    check("Test 1 - Valid 4x4 Sudoku", isValidSudoku(valid4x4Sudoku), true)
    check("Test 2 - Invalid 4x4 Sudoku (Duplicate in Box)", isValidSudoku(invalid4x4Sudoku), false)
    check("Test 3 - Valid 4x4 Sudoku with Empty Cells", isValidSudoku(sudokuWithEmptyCells), true)

}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
fun isValidSudoku(board: List<List<Char>>): Boolean {

    val size = board.size
    val boxSize = Math.sqrt(size.toDouble()).toInt()

    // التحقق من عدم التكرار في الصفوف والأعمدة
    for (i in 0 until size) {
        val rowSet = mutableSetOf<Char>()
        val colSet = mutableSetOf<Char>()

        for (j in 0 until size) {
            val rowVal = board[i][j]
            val colVal = board[j][i]

            if (rowVal != '-' && !rowSet.add(rowVal)) return false
            if (colVal != '-' && !colSet.add(colVal)) return false
        }
    }

    // التحقق من عدم التكرار في المربعات الفرعية
    for (row in 0 until size step boxSize) {
        for (col in 0 until size step boxSize) {
            val boxSet = mutableSetOf<Char>()
            for (i in 0 until boxSize) {
                for (j in 0 until boxSize) {
                    val value = board[row + i][col + j]
                    if (value != '-' && !boxSet.add(value)) return false
                }
            }
        }
    }

    return true
}
