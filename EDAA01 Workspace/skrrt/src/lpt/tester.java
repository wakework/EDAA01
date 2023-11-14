package lpt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class tester {
private SudokuSolver sud=new Sudoku();
private SudokuSolver one=new Sudoku();
private SudokuSolver two=new Sudoku();
private int [][] grid;



	@BeforeEach
	void setUp() throws Exception {
	//	SudokuSolver sud= new Sudoku();
		grid=new int [9][9];
	}

	@AfterEach
	void tearDown() throws Exception {
		grid=null;
		one=null;
		two=null;
		sud=null;
	}

	
	
	@Test
	void testSetNumber() {
		
		sud.setNumber(1, 1, 2);
		grid=sud.getNumbers();
		assertEquals(grid[1][1], 2, "");
		for(int i=0;i<grid.length;i++) {
		for( int k=0; k<grid[i].length;k++) {
		if( i!=1 &&k!=1) {
			assertEquals(grid[i][k], 0, "pos" + "["+i+"]"+"["+k+"]"+"should be zero");
		}
			
			
		}}
		
		
		
		try {
			one.setNumber(-11, 1, 1);
			one.setNumber(1, 21, 1);
			one.setNumber(1, 1, -1);
			assertEquals(false,true, "should throw exepction, ");
			
		} catch(IllegalArgumentException a){
			
		}
		
		
		
	}
	
	// kontrollerar att det metoden trySetNumber för rad,kollon samt sektion
	@Test
	void testTrySetNumber() {
		one.setNumber(1, 1, 1);
		two.setNumber(8,8,8);
		sud.setNumber(1, 1, 1);
		assertEquals(one.trySetNumber(1, 7, 1), false, " should be false, cant place same number in same row");
		assertEquals(two.trySetNumber(1, 8, 8), false, "should be false, cant place same number in same col");
		assertEquals(sud.trySetNumber(2, 2, 1), false, "should be false, cant place same number in same box");
		assertEquals(one.trySetNumber(1, 7, 2), true, " should be true, ");
		assertEquals(two.trySetNumber(1, 8, 2), true, "should be true, ");
		assertEquals(sud.trySetNumber(2, 2, 2), true, "should be true, ");
		
		try {
			one.trySetNumber(-11, 1, 1);
			one.trySetNumber(1, 21, 1);
			one.trySetNumber(1, 1, -1);
			assertEquals(false,true, "should throw exepction, ");
			
		} catch(IllegalArgumentException a){
			
		}
		

	
	}
	@Test
	void testGetNumber() {
		one.setNumber(1, 2, 3);
		one.setNumber(2, 3, 4);
one.setNumber(3, 5, 7);
one.setNumber(1, 2, 4);

assertEquals(one.getNumber(1, 2), 4, " ");
assertEquals(one.getNumber(1, 2), 4, " ");
assertEquals(one.getNumber(2, 3), 4, " ");
assertEquals(one.getNumber(3, 5), 7, " ");

try {
	one.getNumber(1, 10);
	one.getNumber(-1, 2);
	one.getNumber(11111, 2);
	
	assertEquals(false,true, "should throw exepction, ");
	
} catch(IllegalArgumentException a){
	
}

		
		
	}
	
	@Test
		void testRemove() {
		one.setNumber(1, 2, 3);
		assertEquals(one.getNumber(1, 2),3, "" );
		one.removeNumber(1, 2);
		assertEquals(one.getNumber(1, 2),0, "" );
		one.removeNumber(3, 3);
		assertEquals(one.getNumber(3, 3),0, "" );
		
		
		try {
			one.removeNumber(333, 3);
			one.removeNumber(3, -3);
			one.removeNumber(0, 9);
			
			assertEquals(false,true, "should throw exepction, ");
			
		} catch(IllegalArgumentException a){
			
		}	
		
		
		
		}
	
	
	@Test
	void testClear() {
   for(int i=0;i<9;i++) {
	 one.setNumber(i, i, i);
   }
   for(int i=0;i<9;i++) {
	   assertEquals(one.getNumber(i, i),i, "");
   }
one.clear();
for(int i=0;i<9;i++) {
	   assertEquals(one.getNumber(i, i),0, "");
}
   
   
		

	}
	
	
	@Test
		void testgetnbrAndSetnbrs() {
		
		 for(int i=0;i<9;i++) {
			 one.setNumber(i, i, i);
			 two.setNumber(i, i, 9-i);
			 grid[i][i]=i;
		   }
		 sud.setNumbers(grid);
	  int [][] oneg=one.getNumbers();
	  int [][] twog=two.getNumbers();
	  
	  
	  for(int i=0;i<9;i++) {
		  assertEquals(oneg[i][i],i, "");
		  assertEquals(twog[i][i],9-i, "");
		  assertEquals(sud.getNumber(i, i),i, "");
		   }
	    
		
		
		}
	
	@Test
		void testSetNumbers() {
		one.setNumber(1, 1,1);
		one.setNumber(3, 3, 4);
grid[1][1]=3; 
grid[2][2]=7; 
grid[8][8]=2; 
grid[7][7]=6;
one.setNumbers(grid);
assertEquals(one.getNumber(1, 1),3, "");
assertEquals(one.getNumber(8, 8),2, "");
assertEquals(one.getNumber(7, 7),6, "");
assertEquals(one.getNumber(4, 8),0, "");
		
int [][] a1=new int[9][9];
int [][] a2=new int[23][9];
int [][] a3=new int[9][9];
a1[2][2]=-3;
a3[3][3]=123;
try {
	one.setNumbers(a1);
	two.setNumbers(a2);
	sud.setNumbers(a3);
	assertEquals(false,true, "should throw exepction, ");
	
} catch(IllegalArgumentException a){
	
}	
assertEquals(one.getNumber(1, 1),3, "");  // kontrollerar att en felaktig setNumbers ej ändrar på matrisen

assertEquals(one.getNumber(8, 8),2, "");
assertEquals(one.getNumber(7, 7),6, "");
assertEquals(one.getNumber(4, 8),0, "");

		
		
			
		}
	
// testfall 3-olösligtSuddoku	
	@Test

	void testUnsolvableSolve() {
		grid[0][0]=1;
		grid[0][1]=2;
		grid[0][2]=3;
		
		grid[1][0]=4;
		grid[1][1]=5;
		grid[1][2]=6;
		grid [2][3]=7;
		
		one.setNumbers(grid);
		boolean b= one.solve();
		assertEquals(b,false, "");
		one.removeNumber(2, 3);
	    b=one.solve();
	    assertEquals(b,true, "");
	    assertEquals(isSolved(one.getNumbers()),true,"" );
		
	}
	@Test
	void testSolve() {
	grid[0][2]=8;
	grid[0][5]=9;
	grid[0][7]=6;
	grid[0][8]=2;
	
	grid[1][8]=5;
	
	grid[2][0]=1;
	grid[2][2]=2;
	grid[2][3]=5;
	
	grid[3][3]=2;
	grid[3][4]=1;
	grid[3][7]=9;
	
	grid[4][1]=5;
	grid[4][6]=6;
	
	grid[5][0]=6;
	grid[5][7]=2;
	grid[5][8]=8;
	
	grid[6][0]=4;
	grid[6][1]=1;
	grid[6][3]=6;
	grid[6][5]=8;
	
	grid[7][0]=8;
	grid[7][1]=6;
	grid[7][4]=3;
	grid[7][6]=1;
	grid[8][6]=4;
	
	one.setNumbers(grid);
	boolean b=one.solve();
	
	
	assertEquals(b,true, "");
	
	assertEquals(isSolved(one.getNumbers()),true,"" );
	
	
	
	
	
	
	
	
	
	
		
		
		
		
		
	}
	
	@Test
	void testSolveextra() {
		two.setNumber(0, 1, 3);
		one.clear();
		sud.setNumber(7, 7, 2);
		boolean a1=two.solve();
		boolean a2=one.solve();
		boolean a3= sud.solve();
		assertEquals(a1,true, "");
		assertEquals(a2,true, "");
		assertEquals(a3,true, "");
		
		assertEquals(isSolved(two.getNumbers()),true, "");
		assertEquals(isSolved(one.getNumbers()),true, "");
		assertEquals(isSolved(sud.getNumbers()),true, "");
		
		
	}
	
	public static boolean isSolved(int [][] grid) {
		
		
		
	for(int i=0;i<9;i++) {
	for(int k=0;k<9;k++) {
	
    int temp=grid[i][k];
    if(temp<=0||temp>9) {
    	return false;
    }
		
	for(int b=0;b<9;b++) {
	if(( grid[i][b]==temp&&( b!=k )  )|| (grid[b][k]==temp&&b!=i)  ) {
		
		return false;
	}
	
		
	}
    for(int row=i-i%3;row<i-i%3+3 && row<9;row++) {
    for(int col=k-k%3;col<k-k%3+3 && col<9;col++) {	
    	
    	if(grid[row][col]==temp&&! (row==i&&col==k)) {
    		System.out.print("row/col"+row+"/"+col+" i/k "+i+"/" +k + "temp/ grid "+ temp +"grid"+grid[row][col]);
    		return false;
    	}
    	
    }}
    
		
		
	}}
		
		
		
		
		
		
		
		
		
		return true;
	}
	
	
	

}
