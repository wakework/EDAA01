package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;
	private double factor = 0.75;

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and the
	 * default load factor (0.75).
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
		capacity = 0;
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
		this.capacity = 0;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}
	}

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(10);
		for (int i = -50; i <= 50; i += 10) {
			map.put(i, i);
		}

		System.out.println(map.show());
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		if (find(index(key), key) != null) {
			return find(index(key), key).getValue();
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		if (capacity > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public V put(K arg0, V arg1) {
		Entry<K, V> n = find(index(arg0), arg0);
		if (n != null) {
			V oldValue = n.getValue();
			n.setValue(arg1);
			return oldValue;
		} else {
			if (size() > factor * table.length) {
				rehash();
			}

			n = new Entry<K, V>(arg0, arg1);
			n.next = table[index(arg0)];
			table[index(arg0)] = n;

			capacity++;
			if (size() > factor * table.length) {
				rehash();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		int length = 2 * table.length;
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[length];
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				Entry<K, V> old = table[i];
				while (old != null) {
					int index = Math.abs(old.getKey().hashCode() % length);
					Entry<K, V> n = new Entry<K, V>(old.getKey(), old.getValue());
					if (newTable[index] != null) {
						n.next = newTable[index];
						newTable[index] = n;
					} else {
						newTable[index] = n;
					}

					old = old.next;
				}
			}
		}

		table = newTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> n = find(index(key), key);
		
		if (table[index(key)] == null) {
			return null;
		} else if (n == null) {
			return null;
		} else {
			if (n.equals(table[index(key)])) {
				table[index(key)] = n.next;
				capacity--;
				return n.getValue();
			}
			
			Entry<K, V> remove = table[index(key)];
			while (!n.equals(remove.next)) {
				remove = remove.next;
			}
			remove.next = n.next;
			capacity--;
			return n.getValue();
		}
	}

	@Override
	public int size() {
		return capacity;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity; i++) {
			if (table[i] != null) {
				sb.append("Index " + i + ": " + table[i].toString());
				Entry<K, V> n = table[i].next;
				while (n != null) {
					sb.append(" " + n.toString());
					n = n.next;
				}
				sb.append("\n");
			}
		}

		return sb.toString();
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % table.length);
	}

	private Entry<K, V> find(int index, K key) {
		if (index < 0) {
			return null;
		}

		Entry<K, V> n = table[index];
		while (n != null) {
			if (n.getKey().equals(key)) {
				return n;
			}
			n = n.next;
		}

		return null;
	}

}
