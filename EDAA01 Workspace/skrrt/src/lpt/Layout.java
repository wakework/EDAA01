package lpt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 1) klassen skapar en ruta 
 2) själva Sudoku grided representeras utav en 3x3 panel som i sin tur har lagrat en 3x3 panel i varje position.
     Dessa 9x9 positionerna inehåller Jbutton objekt som lagras i attributet ButtonGrid, och skapas i metoden 
     createGrid(). 
 3) insättningen sker genom en panel som har 10 stycken Jbutton objekt som representerar 1-9 samt en 
      nollställning. Denna panel skapas i metoden createNbrInput();
 4) Solve och Clear, själva "motorn" av programmet skapas i metoden CreateEngine() och har följande funktion:
     I) samtliga knappar ifrån (1) lagras i 9x9 attributet ButtonGrid där varje knapp har en actionlistener, när man
        trycker på knappen kommer JButton attributet selected att ansättas till den intryckta knappen och ändra färg
        till röd.
     II) när JButton attributet selected har en ansättning(är röd ) kommer man kunna trycka på de 10 knapparna 
         i grided skapat i steg (3), alla dessa knappar har en integer representation(1-9) eller "-" trycker man på knapp  
         X kommer selected att erhålla värdet  X och färgas grön.
     III) Vid intryckning utav Solve() så kommer programmet först använda hjälpklassen inputcontroll för att se om
          insättningen är okej(Sudokusolver kan göra detta själv), om inputet ej är okej kommer inputcontroll returnera
          en String som berättar för användaren vad h*n har gjort fel. Om insättningen är okej kommer metoden
          updateGrid() att anroppas, denna metod kommer föra över de värden lagrad i ButtonGrid till attributet
          int [][] grid. Därefter skickas grid till SudokuSolver, om Sudokut är lösst kommer metoden updateDisplay
          att anroppas och ge varje JButtin i atributet ButtonGrid ett namn som motsvarar det lösta sudokut. 
      
 
 */

public class Layout extends JFrame{
	private JFrame frame;
	private JButton [][] ButtonGrid;
	private int [][] grid;
	private boolean hold;
	private JButton selected;
	private JTextArea message;
	
public Layout() {
	frame= new JFrame();
	  ButtonGrid=new JButton[9][9];
	grid=new int [9][9];
	
	SetUp();
}
private void SetUp () {
	 

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel board=createGrid();
    JPanel inputNbr=createNbrInput();
    JPanel engine= CreateEngine();
    
	
			
	Container pane = frame.getContentPane();
	pane.add(board);
    pane.add(inputNbr,BorderLayout.SOUTH);
    pane.add(engine,BorderLayout.EAST);
	frame.setVisible(true);
	
}
private JPanel createGrid() {
	
	JPanel board=new JPanel();
	board.setLayout(new GridLayout(3,3));
	board.setBorder(BorderFactory.createLineBorder(Color.black) );
	
	for(int i=0;i<9;i++) {
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(3,3));
		p1.setBorder(BorderFactory.createLineBorder(Color.black) );
		board.add(p1);
		for(int k=0;k<9;k++) {
			JButton temp=new JButton();
			
			p1.add(temp);
			ButtonGrid[i-i%3+k/3][k%3+(i%3)*3]=temp;
			temp.addActionListener(e->{
				temp.setBackground(Color.red);
				if(selected!=null) {
					selected.setBackground(null);
				}
			
				selected=temp;
			
				
				
			});
			
			
		
			
		}
		
	}
	return board;
	
}

	private JPanel createNbrInput() {
		
		JPanel p=new JPanel();
		for(int i=1;i<10;i++) {
			JButton temp=new JButton (Integer.toString(i));
			temp.addActionListener(e->{
				if(selected!=null) {
					selected.setText(temp.getText() );
					selected.setName(temp.getText()   );
					selected.setBackground(Color.green); ///////////
					selected=null;
				}
				
				});
			p.add(temp );
		}
		JButton none=new JButton("-");
		none.addActionListener(e->{
			if(selected!=null) {
				selected.setText(null);
				selected.setName(null);
				selected.setBackground(null);
				selected=null;
			}
			
		});
		p.add(none);
		
		return p;
	}
	private JPanel CreateEngine() {
		JPanel j= new JPanel(new GridLayout(3,1));
		message=new JTextArea(5,20);
	message.setEditable(false);
	
		//b.append("fasfas" + "\n"+"fasdfa");
		JButton solve=new JButton("solve");
		JButton clear=new JButton("clear");
     solve.addActionListener(e->{
    	 message.setText(null);
    	 updateGrid();
    	 inputControll kontroll=new inputControll(grid);
    	 if(kontroll.inputOk()) {
    			SudokuSolver m=new Sudoku();
    			m.setNumbers(grid);
    			if(m.solve()) {
    				grid=m.getNumbers();
    				updateDisplay();
    				long  temp=m.getStats();
    				 message.append("nbr of recursions: "+ temp+"\n");
    				
    			} else {
    			long  temp=m.getStats();
    			message.append("cant be solved \n");
    			
    				 message.append(temp+"\n");
    			
    				
    				
    			}
    		 
    		 
    	 }else {
    		 String [] error=kontroll.getErrorMessage();
    		 for(int i=0;i<error.length;i++) {
    			 message.append(error[i]+"\n");
    			
    		 }
    		 
    	 }
    	 
    	 
     });
		
     clear.addActionListener(e->{
    	
    	for(int i=0;i<9;i++) {
    		for(int k=0;k<9;k++) {
    			  ButtonGrid[i][k].setName(null);
    			   ButtonGrid[i][k].setText(null);
    			   grid[i][k]=0;
    			   ButtonGrid[i][k].setBackground(null);
    		}
    	}
    	 message.setText(null);
     });
		
		j.add(solve);
		j.add(clear);
		j.add(message);
		
		
		return j;
	}
	private void updateDisplay() {
   for(int i=0;i<9;i++) {
	   for(int k=0;k<9;k++) {
		   if(grid[i][k]==0) {
		   ButtonGrid[i][k].setName(null);
		   ButtonGrid[i][k].setText(null);
		   } else {
			   ButtonGrid[i][k].setName(Integer.toString(grid[i][k]));
			   ButtonGrid[i][k].setText(Integer.toString(grid[i][k]));
		   }
		   
	   }
   }
		
		
	}
	
	
	private void updateGrid() {
	for(int i=0;i<9;i++) {
	for(int k=0;k<9;k++) {
		
		if(ButtonGrid[i][k].getName()!=null) {
	grid[i][k]=Integer.parseInt(ButtonGrid[i][k].getText());
	
	
	} else {
		grid[i][k]=0;
	}
		
		
	
	}
	
	}
		
		
	}

}
