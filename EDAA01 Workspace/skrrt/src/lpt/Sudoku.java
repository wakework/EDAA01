package lpt;

public class Sudoku implements SudokuSolver{

	private int [][] grid;
	private boolean solved; 
	
	private long nbr;

	// constructor
	public Sudoku() {
	grid=new int[9][9];
	nbr=0;
	
}
	
/* give the position row,col in the Sudokugrid the value number.
 * @param row - the row of the Sudoku matrix, allowed values between 0 and 8
 * @param col  - the col of the Sudoku matrix, allowed values between  0 and 8
 * @param number - the number placed in the position row,col. allowed valubes are between 1 and 9
 *@throws IllegalArgumentException if row,col or number are not within the allowed ranges.
 */
public void setNumber(int row,int col, int number) {

if(number<0||number>9) {
	
	throw new IllegalArgumentException("only values between 0 and 8 is allowed");
	
} else if (row<0||row>9||col<0||col>9) {
	throw new IllegalArgumentException("only values between 0 and 8 is allowed");
 
} else if (trySetNumber(row,col , number)) {
	grid[row][col]=number;
}
	
	
}
/* return the number in the position row,col. Only values between 0 and 8 is acceptable. 
  * @param row - the row of the Sudoku matrix, allowed values between 0 and 8
 * @param col  - the col of the Sudoku matrix, allowed values between  0 and 8
 * @throws IllegalArgumentException if row and col are not within the allowed range
 * @return the integer value of the number in position row,col
 */
public int getNumber(int row,int col) {
	if(row<0||row>9||col<0||col>9) {
		throw new IllegalArgumentException("only values between 0 and 8 is allowed");
	} else {
		return grid[row][col];
		
	}
}
/*set the number in the position row,col to 0. 
  * @param row - the row of the Sudoku matrix, allowed values between 0 and 8
 * @param col  - the col of the Sudoku matrix, allowed values between  0 and 8
 * @throws IllegalArgumentException if row and col are not within the allowed range
 */
public void removeNumber(int row,int col) {
	if(row<0||row>9||col<0||col>9) {
		throw new IllegalArgumentException("only values between 0 and 8 is allowed");
	} else {
		grid[row][col]=0;
		
	}
	
}




/* give the 9x9 Sudoku matrix the values of the 9x9 matris numbers. 
*@ param - numers an 9x9 matrix representing an sudoku grid
* @throws IllegalArgumentException if numbers do not have the correct dimensions and if the numbers in numbers
*    are not within the allowed range of 1-9
*/
public void setNumbers(int [][] numbers) {
if(numbers.length!=9) {
	throw new IllegalArgumentException("matrix length do no match");
}


	
for(int i=0;i<9;i++) {
for(int k=0;k<9;k++) {
if(numbers[i].length!=9) {
	throw new IllegalArgumentException("matrix length do no match");
} else if(numbers[i][k]<0||numbers[i][k]>9) {
	throw new IllegalArgumentException("only values between 0 and 9 is allowed");
} 
	
	
	
	
}}
for(int i=0;i<9;i++) {
for(int k=0;k<9;k++) {
	
grid[i][k]=numbers[i][k];
	
	
}}


}
	/*
	* clears the sudoku grid and set all positions to 0
	 */
public void clear() {
for(int i=0;i<9;i++) {
for(int k=0;k<9;k++) {
	grid[i][k]=0;
}
	
}
	
	solved=false;
}

	/* solve the suddoku 
	 * @returns true if the sudoku is solvable, otherwise false.
	 */
public boolean solve() {
	
	int [][] temp=new int[9][9];
	for(int i=0;i<9;i++) {
	for(int k=0;k<9;k++) {
		temp[i][k]=grid[i][k];
	}
		
	}
	
	
	solved=solve(0,0);

	if(!solved) {
		grid=temp;
	}
	
	
	return solved;
}
/* 
 * @return an int[][] with the dimensions 9x9 representing the current Sudoku grid
 */
public int [][] getNumbers(){
	
		int [][] numbers= new int [9][9];
	for(int i=0;i<grid.length;i++) {
	for(int k=0;k<grid[i].length;k++)	{
		numbers[i][k]=grid[i][k];
	}}
		
		
		return numbers;
	
}

/*
 * @returns the number of recursions made in solve(), if solve() haven´t been called yet it returns 0
 */

	public long getStats() {
		
		return nbr;
	}
	
	
private boolean solve(int row,int col) {
	nbr++;
	
	// kollar om positionen [row][col] redan är ifylld. Om positionen är ifylld går den vidare. om sista positionen är ifylld
	// kommer row=9 och eftersom de tidigare plaseringarna i algoritmen alltid kollar om dess positioner är okej innebär
	// det att suddokut är löst, således returnas 9
	while(grid[row][col]!=0) {
		col++;
		if(col==9) {
			col=0;
			row++;
		}
		if(row==9) {
			return true;
		}
	}
	
	
	if(row==8&&col==8) {
		return finalCheck();
	}
	
	
	
	int row2=row;
	int col2=col+1;
	if(col>=8) {
		col2=0;
		row2++;
	}
	
	
	for(int i=1;i<10;i++) {  
		
	if(trySetNumber(row,col,i)) {    
		grid[row][col]=i;   
		if(solve(row2,col2)) {
			return true;
		}
		else {
			grid[row][col]=0;}
			
		}	
	
	
	}
	
		
		
	
	
	return false;
}
	
private boolean finalCheck() {
	for(int i=1;i<9;i++) {
		if(trySetNumber(8,8,i)) {
			grid[8][8]=i;
			return true;
		}
			
	}
	
	return false;
}

	
/* Check if it is possible to place the number nbr in the position row,col based on Sudoku rules
 * @param row - the row of the Sudoku matrix, allowed values between 0 and 8
 * @param col  - the col of the Sudoku matrix, allowed values between  0 and 8
 * @param nbr-  allowed values between 0 and 9
 * @throws IllegalArgumentException if row,col or nbr are not within the allowed range
* @return boolean true if nbr can be placed at the position row,col according to suddoku rules
*/
public boolean trySetNumber(int row,int col ,int nbr) {
	if(row<0||row>9||col<0||col>9) {
		throw new IllegalArgumentException("only values between 0 and 8 is allowed for row and col");
		
	} 	else if(nbr<0||nbr>9) {
		throw new IllegalArgumentException("only values between 0 and 9 is allowed");
	}
	
	
	
	
	int row2=row-row%3;
	int col2= col-col%3;
	for(int i=0;i<9;i++) {
		if(grid[i][col]==nbr||grid[row][i]==nbr||grid[row2][col2+i/3]==nbr||grid[row2+1][col2+i/3]==nbr||grid[row2+2][col2+i/3]==nbr) {
			return false;
		}
		
		
	}
	
	
	return true;
}



	
	
	
	
	
	
}
