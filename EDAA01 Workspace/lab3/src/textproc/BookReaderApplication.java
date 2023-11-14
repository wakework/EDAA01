package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
	public static void main(String[] args) throws FileNotFoundException {
		List<TextProcessor> p = new ArrayList<>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopWords = new HashSet<String>();
		
		while (scan.hasNext()) {
			stopWords.add(scan.next().toLowerCase());
		}
		
		GeneralWordCounter g = new GeneralWordCounter(stopWords);
		p.add(g);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (TextProcessor t : p) {
				t.process(word);
			}
		}
		
		s.close();
		
		for (TextProcessor t : p) {
			t.report();
		}
		
		BookReaderController reader = new BookReaderController(g);
	}
}
