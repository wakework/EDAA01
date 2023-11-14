package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuController {
	public SudokuController(SudokuSolver sudoku) {
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 100, 100));
	}

	public static void main(String[] args) {
		new SudokuController(new Sudoku(new int[9][9]));
	}

	private void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		// frame
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		GridLayout grid = new GridLayout(9, 9);

		// panel and buttons
		JPanel panel = new JPanel();
		panel.setLayout(grid);
		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.LIGHT_GRAY);
		JButton clear = new JButton("Clear");
		clear.setCursor(Cursor.getPredefinedCursor(12));
		JButton solve = new JButton("Solve");
		solve.setCursor(Cursor.getPredefinedCursor(12));

		// creating board of textfields
		JTextField[][] board = new JTextField[9][9];

		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				board[i][k] = new JTextField();
				board[i][k].setText("");

				// setting color to regions
				if (i >= 0 && i < 3) {
					if (k >= 0 && k < 3) {
						board[i][k].setBackground(Color.GREEN);
					} else if (k >= 6 && k < 9) {
						board[i][k].setBackground(Color.GREEN);
					}
				} else if (i >= 6 && i < 9) {
					if (k >= 0 && k < 3) {
						board[i][k].setBackground(Color.GREEN);
					} else if (k >= 6 && k < 9) {
						board[i][k].setBackground(Color.GREEN);
					}
				} else if (k >= 3 && k < 6) {
					board[i][k].setBackground(Color.GREEN);
				}

				panel.add(board[i][k]);
			}
		}

		// solve-buttonfunction
		solve.addActionListener(event -> {
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					if (board[i][k].getText().equals("")) {
						sudoku.setNumber(i, k, 0);
					} else if (board[i][k].getText().length() > 1 || board[i][k].getText().matches("^[a-zA-Z]*$")) {
						board[i][k].setText("");
						JOptionPane.showMessageDialog(pane, "Felaktig inmatning, försök igen");
						return;
					} else {
						int n = Integer.parseInt(board[i][k].getText());
						sudoku.setNumber(i, k, n);
					}
				}
			}

			if (sudoku.solve()) {
				int[][] solved = sudoku.getNumbers();
				for (int i = 0; i < 9; i++) {
					for (int k = 0; k < 9; k++) {
						board[i][k].setText(String.valueOf(solved[i][k]));
					}
				}
				JOptionPane.showMessageDialog(pane, "Lösning existerar! Bra jobbat!");
			} else {
				JOptionPane.showMessageDialog(pane, "Lösning saknas, försök igen!");
			}
		});

		// clear-buttonfunction
		clear.addActionListener(event -> {
			sudoku.clear();
			for (int i = 0; i < 9; i++) {
				for (int k = 0; k < 9; k++) {
					board[i][k].setText("");
				}
			}
			JOptionPane.showMessageDialog(pane, "Brädet tömt på siffror");
		});

		// adding components to pane
		panelButton.add(clear);
		panelButton.add(solve);
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panelButton, BorderLayout.SOUTH);

		// start
		frame.pack();
		frame.setVisible(true);

	}
}