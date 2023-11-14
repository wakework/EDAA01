package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MultiWordCounter implements TextProcessor {
	private Map <String, Integer> m;
	
	public MultiWordCounter (String [] landskap) {
		m = new TreeMap <>();
		
		for (int i = 0; i < landskap.length; i++) {
			m.put(landskap[i], 0);
		}
	}
	
	public void process (String w) {
		if (m.containsKey(w)) {
			m.put(w, m.get(w) + 1);
		}
	}
	
	public void report () {
		for (String key : m.keySet()) {
			System.out.println(key + ": " + m.get(key));
		}
	}
}
