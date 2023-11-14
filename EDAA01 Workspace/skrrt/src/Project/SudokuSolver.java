package Project;

public interface SudokuSolver {

	/**
	 * Sets the digit number in the box row, col.
	 * 
	 * @param row    The row
	 * @param col    The column
	 * @param number The digit to insert in row, col
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	void setNumber(int row, int col, int number);

	/**
	 * Kollar om siffran number kan sÃ¤ttas i raden row och kolumnen col, om det
	 * inte gÃ¥r enligt spelreglerna returneras false
	 * 
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	boolean trySetNumber(int row, int col, int number);

	/**
	 * Returnerar siffran pÃ¥ raden row och kolumnen col.
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	int getNumber(int row, int col);

	/**
	 * Tar bort siffran pÃ¥ raden row och kolumnen col.
	 * 
	 * @throws IllegalArgumentException if row or col is outside [0..8]
	 */
	void removeNumber(int row, int col);

	/**
	 * TÃ¶mmer hela sudokut
	 */
	void clear();

	/**
	 * LÃ¶ser sudokut och returnerar true om sudokut gÃ¥r att lÃ¶sa.
	 */
	boolean solve();

	/**
	 * Returnerar siffrorna i sudokut.
	 */
	int[][] getNumbers();

	/**
	 * Fyller i siffrorna i numbers i sudokut.
	 * 
	 * @throws IllegalArgumentException if not all numbers in [0..9]
	 **/
	void setNumbers(int[][] numbers);
}
