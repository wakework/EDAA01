package lpt;
/*
hjälpklass vars promära syfte är att komplitera SudokuSolver genpm att
I)  kontrollera om de inledande insättningarna i suddokut är okej(via metoden InputOK). Detta är något
    SuddokuSolver kan lösa själv, men detta skulle ta ett tag på grund utav många rekursioner
II) Skicka ut ett felmedellande till användaren via metoden getErrorMessage, där dessa felmeddelanden 
    meddelar användaren att h*n ej kan sätta in samma värden inom samma rad,kolonn eller sektion.    
 
 */
public class inputControll {
	private boolean inputOk;
   private String [] errorMessage;
public inputControll(int[][] grid) {
	
	check(grid);
	
}
public boolean inputOk() {
	return inputOk;
}
public String [] getErrorMessage() {
	if(!inputOk) {
	return errorMessage;
	}
	return null;
}

private void check(int[][] grid) {
int errorRow=0;
int  errorCol=0;
int  errorSection=0;
int  errorRangeHigh=0;
int  errorRangeLow=0;

for(int i=0;i<9;i++) {

for(int k=0;k<9;k++){
if(grid[i][k]>9 ) {
	errorRangeHigh=1;
}
if(grid[i][k]<0) {
	errorRangeLow=1;
}

if(grid[i][k]!=0) {
	if(errorRow==0) {
		errorRow=rowOk(i,k,grid);
	}
	if(errorCol==0) {
		errorCol=colOk(i,k,grid);
	}
	if(errorSection==0) {
		errorSection=secOk(i,k,grid);
	}
	
	
	
}

	
	
}}


	inputOk=errorRow+errorCol+errorSection+errorRangeHigh+errorRangeLow==0;
errorMessage(errorRow,errorCol,errorSection,errorRangeHigh,errorRangeLow);
	
}



private void errorMessage(int errorRow,int errorCol,int errorSection,int errorRangeHigh,int errorRangeLow) {
	
	
String [] error=new String [errorRow+errorCol+errorSection+errorRangeHigh+errorRangeLow];
int index=0;

if(errorSection==1) {
	error[index]="can't place numbers of the same in the same 3x3 section";
	index++;
}


if(errorRow==1) {
	
	error[index]="can't place numbers of the same in the same row";
	index++;
}
if(errorCol==1) {
	error[index]="can't place numbers of the same in the same colon";
	index++;
}


if(errorRangeHigh==1) {
	error[index]="can't place numbers higher then 9";
	index++;
}
if(errorRangeLow==1) {
	error[index]="can't place negative numbers";
}

errorMessage=error;
	
}


private int colOk(int row,int col,int [][]grid) {
for(int i=0;i<9;i++) {
if(grid [row][col]==grid[i][col]&&i!=row) {

	return 1;
}
	
}
	
	
	return 0;
}
private int rowOk(int row,int col,int [][]grid) {
	for(int i=0;i<9;i++) {
		if(grid [row][col]==grid[row][i]&&i!=col) {
		
			return 1;
			
			
		}
			
		}
	
	
	return 0;
}
private int secOk(int row,int col,int [][]grid) {
	
	for(int i=row-row%3;i<row-row%3+3;i++) {
		for(int k=col-col%3;k<col-col%3+3;k++) {	
		if(grid[row][col]==grid[i][k]&& (i!=row || k!=col)) {
			return 1;
		}
			
	}}
	
	
	return 0;
}

	
}
