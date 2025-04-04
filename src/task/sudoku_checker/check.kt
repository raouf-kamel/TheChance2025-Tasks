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



    return true
}
