package lpt;

public interface SudokuSolver {

	public void setNumber(int row,int col,int number);
	public boolean trySetNumber(int row,int col,int number);
	public int getNumber(int row,int col);
	public void removeNumber(int row,int col);
	public void clear();
	public boolean solve();
	public int[][] getNumbers();
	public void setNumbers(int [][] numbers);
	public long getStats();
	

}
