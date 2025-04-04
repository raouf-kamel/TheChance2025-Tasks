package task.sudoku_checker

fun main() {

// Valid 4x4 Sudoku
    val valid4x4Sudoku = listOf(
        listOf('1', '2', '3', '4'),
        listOf('1', '4', '1', '2'),
        listOf('2', '3', '4', '1'),
        listOf('4', '1', '2', '3')
    )

//  4x4 Sudoku with duplicate in box
    val invalid4x4Sudoku = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', '4'), // duplicate 4
        listOf('4', '1', '2', '3')
    )

//  4x4 Sudoku with empty cells but valid
    val sudokuWithEmptyCells = listOf(
        listOf('1', '2', '-', '4'),
        listOf('3', '4', '1', '-'),
        listOf('2', '-', '4', '1'),
        listOf('-', '1', '2', '3')
    )

//  Row with duplicate
    val invalidRowDuplicate = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '3', '1', '2'), // duplicate 3
        listOf('2', '1', '4', '1'),
        listOf('4', '1', '2', '3')
    )

//  Column with duplicate
    val invalidColumnDuplicate = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('1', '3', '4', '1'), // duplicate 1 in column 0
        listOf('4', '1', '2', '3')
    )

//  Invalid character
    val invalidCharacter = listOf(
        listOf('1', '2', 'X', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', '1'),
        listOf('4', '1', '2', '3')
    )

//  Not square board (3x4)
    val nonSquareBoard = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', '1')
    )

//  Valid 9x9 Sudoku with empty cells
    val valid9x9Sudoku = listOf(
        listOf('5','3','-','-','7','-','-','-','-'),
        listOf('6','-','-','1','9','5','-','-','-'),
        listOf('-','9','8','-','-','-','-','6','-'),
        listOf('8','-','-','-','6','-','-','-','3'),
        listOf('4','-','-','8','-','3','-','-','1'),
        listOf('7','-','-','-','2','-','-','-','6'),
        listOf('-','6','-','-','-','-','2','8','-'),
        listOf('-','-','-','4','1','9','-','-','5'),
        listOf('-','-','-','-','8','-','-','7','9')
    )

//  Row with missing elements
    val rowWithMissingElement = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4'),         // only 3 elements
        listOf('4', '1', '2', '3')
    )

//  Column has non-numeric character
    val columnWithNonNumericChar = listOf(
        listOf('1', '2', '3', '4'),
        listOf('3', '4', '1', '2'),
        listOf('2', '3', '4', 'Z'),    // non-digit
        listOf('4', '1', '2', '3')
    )

//  Multiple duplicates in board
    val multipleDuplicateBoard = listOf(
        listOf('1', '2', '3', '4'),
        listOf('1', '4', '1', '2'),    // duplicate 1 in row
        listOf('2', '3', '4', '1'),
        listOf('4', '1', '2', '2')     // duplicate 2 in row
    )

//  Empty board
    val emptyBoard = listOf(
        listOf('-', '-', '-', '-'),
        listOf('-', '-', '-', '-'),
        listOf('-', '-', '-', '-'),
        listOf('-', '-', '-', '-')
    )
 


    //  Valid Sudoku Boards
    check("When the Sudoku is a valid 4x4 board, then return true", isValidSudoku(valid4x4Sudoku), true)
  check("When the Sudoku has empty cells but no duplicates, then return true", isValidSudoku(sudokuWithEmptyCells), true)
    check("When the Sudoku is a valid 9x9 board with empty cells and no duplicates, then return true", isValidSudoku(valid9x9Sudoku), true)


     //  Invalid Sudoku Boards
     check("When the Sudoku has a duplicate in a box, then return false", isValidSudoku(invalid4x4Sudoku), false)
     check("When a row contains duplicate numbers, then return false", isValidSudoku(invalidRowDuplicate), false)
     check("When a column contains duplicate numbers, then return false", isValidSudoku(invalidColumnDuplicate), false)
     check("When the Sudoku contains an invalid character, then return false", isValidSudoku(invalidCharacter), false)
     check("When the board is not square, then return false", isValidSudoku(nonSquareBoard), false)

     // ➕ Extra Tests
     check("When a row has missing elements, then return false", isValidSudoku(rowWithMissingElement), false)
     check("When a column has non-numeric character, then return false", isValidSudoku(columnWithNonNumericChar), false)
     check("When the Sudoku has duplicates in multiple areas, then return false", isValidSudoku(multipleDuplicateBoard), false)
     check("When the Sudoku board is completely empty, then return false", isValidSudoku(emptyBoard), false)



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

    if (board.any { it.size != size }) return false

    // التحقق من أن اللوحة ليست فارغة بالكامل
    if (board.all { row -> row.all { cell -> cell == '-' } }) return false

    // التحقق من أن جميع العناصر في اللوحة هي أرقام من 1 إلى 9 أو '-'
    for (row in board) {
        for (cell in row) {
            if (cell != '-' && (cell !in '1'..'9')) {
                return false // إذا كان هناك حرف غير صالح
            }
        }
    }

    // التحقق من التكرار في الصفوف والأعمدة
    for (i in 0 until size) {
        val rowSet = mutableSetOf<Char>()
        val colSet = mutableSetOf<Char>()

        for (j in 0 until size) {
            val rowVal = board[i][j]
            val colVal = board[j][i]

            // التحقق من التكرار في الصفوف والأعمدة
            if (rowVal != '-' && !rowSet.add(rowVal)) return false
            if (colVal != '-' && !colSet.add(colVal)) return false
        }
      //  println(rowSet)
     //   println(colSet)
    }

    // التحقق من التكرار في المربعات الفرعية
    val boxSize = Math.sqrt(size.toDouble()).toInt()
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

