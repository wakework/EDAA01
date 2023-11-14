package Project;

/**
 * Authors:
 * 
 * @author Jacob Persson
 * @author Stella Johannsen
 */
public class Sudoku implements SudokuSolver {
	private int[][] mainBoard;

	public Sudoku(int[][] board) {
		this.mainBoard = board;
	}

	/**
	 * Sets the digit number in the box row, col.
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * 
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public void setNumber(int row, int col, int number) {
		if (number < 0 || number > 9) {
			throw new IllegalArgumentException("illegal value");
		}

		if (row < 0 || col < 0 || row > 8 || col > 8) {
			throw new IllegalArgumentException("illegal value");
		}

		mainBoard[row][col] = number;
	}

	/**
	 * Kollar om siffran number kan sättas i raden row och kolumnen col, om det inte
	 * går enligt spelreglerna returneras false.
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * 
	 * @return boolean True if the number can be set without breaking sudoku rules,
	 *         false if number breaks the rules
	 * 
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	@Override
	public boolean trySetNumber(int row, int col, int number) {
		if (number < 0 || number > 9) {
			throw new IllegalArgumentException("illegal value");
		}

		if (row < 0 || col < 0 || row > 8 || col > 8) {
			throw new IllegalArgumentException("illegal value");
		}

		return ruleCheck(number, row, col);
	}

	// Row rule check
	private boolean rowRuleCheck(int number, int row) {
		for (int i = 0; i < 9; i++) {
			if (mainBoard[row][i] == number) {
				return false;
			}
		}

		return true;
	}

	// Column rule check
	private boolean colRuleCheck(int number, int col) {
		for (int i = 0; i < 9; i++) {
			if (mainBoard[i][col] == number) {
				return false;
			}
		}

		return true;
	}

	// Region rule check
	private boolean regionRuleCheck(int number, int row, int col) {
		// Decide which region
		int regionRow = (row / 3) * 3;
		int regionCol = (col / 3) * 3;

		for (int i = regionRow; i < regionRow + 3; i++) {
			for (int j = regionCol; j < regionCol + 3; j++)
				if (mainBoard[i][j] == number) {
					return false;
				}
		}

		return true;
	}

	// Main rule check
	private boolean ruleCheck(int number, int row, int col) {
		// Change to 0 to not collide during rule checks
		int copy = mainBoard[row][col];
		mainBoard[row][col] = 0;

		if (rowRuleCheck(number, row) && colRuleCheck(number, col) && regionRuleCheck(number, row, col)) {
			mainBoard[row][col] = copy;
			return true;
		} else {
			mainBoard[row][col] = copy;
			return false;
		}
	}

	/**
	 * Returnerar siffran på raden row och kolumnen col.
	 * 
	 * @param row The row
	 * @param col The column
	 * 
	 * @return int The int value from postion row, col in the sudoku
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public int getNumber(int row, int col) {
		if (row < 0 || col < 0 || row > 8 || col > 8) {
			throw new IllegalArgumentException("illegal value");
		}

		return mainBoard[row][col];
	}

	/**
	 * Tar bort siffran på raden row och kolumnen col.
	 * 
	 * @param row The row
	 * @param col The column
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	@Override
	public void removeNumber(int row, int col) {
		if (row < 0 || col < 0 || row > 8 || col > 8) {
			throw new IllegalArgumentException("illegal value");
		}

		mainBoard[row][col] = 0;
	}

	/**
	 * Tömmer hela sudokut.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				mainBoard[i][k] = 0;
			}
		}
	}

	/**
	 * Löser sudokut och returnerar true om sudokut går att lösa.
	 * 
	 * @return boolean Returns the boolean value if the sudoku is solveable
	 * 
	 */
	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	// Main recursive solve method
	private boolean solve(int row, int col) {
		// Tom ruta
		if (mainBoard[row][col] == 0) {

			// Sista rutan
			if (row == 8 && col == 8) {
				for (int i = 1; i <= 9; i++) {
					if (trySetNumber(row, col, i)) {
						setNumber(row, col, i);
						// Sudokut löst!
						return true;
					}
				}
				// Annars inte
				return false;
			}

			for (int i = 1; i < 10; i++) {
				if (trySetNumber(row, col, i)) {
					setNumber(row, col, i);
					if (col < 8) {
						// Nästa ruta
						if (solve(row, col + 1)) {
							return true;
						}
						// Nästa rad
					} else if (solve(row + 1, 0)) {
						return true;
					}
				}
			}
			// Sätt tillbaka till noll
			setNumber(row, col, 0);
			return false;
			// Rutan inte tom
		} else {
			// Sista rutan
			if (row == 8 && col == 8) {
				return trySetNumber(row, col, getNumber(row, col));
			}

			if (trySetNumber(row, col, getNumber(row, col))) {
				if (col < 8) {
					return solve(row, col + 1);
				} else {
					return solve(row + 1, 0);
				}
			}
		}

		return false;
	}

	/**
	 * Returnerar siffrorna i sudokut.
	 * 
	 * @return int[][] Returns a matrix of the sudoku
	 * 
	 */
	@Override
	public int[][] getNumbers() {
		int[][] returnMatris = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				returnMatris[i][k] = mainBoard[i][k];
			}
		}
		return returnMatris;
	}

	/**
	 * Fyller i siffrorna i numbers i sudokut.
	 * 
	 * @param numbers A matrix of a sudoku
	 * 
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 * 
	 **/
	@Override
	public void setNumbers(int[][] numbers) {
		if (numbers.length > 9 || numbers[0].length > 9) {
			throw new IllegalArgumentException("illegal value");
		}

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				if (numbers[i][k] < 0 || numbers[i][k] > 9) {
					throw new IllegalArgumentException("illegal value"); // Ändra
				}
				mainBoard[i][k] = numbers[i][k];
			}
		}
	}
}
