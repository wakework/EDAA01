package Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Project.Sudoku;
import Project.SudokuSolver;

class TestSudoku {
	private SudokuSolver solver;
	private int[][] sudoku;

	@BeforeEach
	void setUp() {
		sudoku = new int[9][9];
		solver = new Sudoku(sudoku);

	}

	@AfterEach
	void tearDown() {
		sudoku = null;
		solver = null;
	}

	@Test
	void testEmptySudoku() {
		sudoku = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		solver = new Sudoku(sudoku);
		assertTrue("Sudoku has no solution", solver.solve());
	}

	@Test
	void testSudokuFromFigure1() {
		sudoku = new int[][] { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 2, 8 }, { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };

		solver = new Sudoku(sudoku);
		assertTrue("Sudoku has no solution", solver.solve());
	}

	@Test
	void testImpossibleSudoku() {
		sudoku = new int[][] { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
				{ 1, 0, 2, 5, 0, 0, 0, 0, 0 }, { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
				{ 6, 0, 0, 0, 0, 0, 0, 6, 8 }, { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, { 8, 6, 8, 0, 3, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 4, 0, 0 } };

		solver = new Sudoku(sudoku);
		assertEquals("Sudoku has solution", false, solver.solve());
	}
}
