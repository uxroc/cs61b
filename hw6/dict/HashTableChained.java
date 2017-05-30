/* HashTableChained.java */

package dict;
import java.util.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 *  Place any data fields here.
	 **/

	private Vector<List<Entry>> table;
	private int sizeT;
	private int num;
	private int coll;
	private ArrayList<Integer> numOfKeysEach;

	/**
	 * Return a prime integer to select the budget size.
	 **/
	private int nextPrime(int sizeEstimate) {
		boolean[] primes = new boolean[sizeEstimate + 100];
		for (int i = 2; i < sizeEstimate + 100; i++) {
			if (!primes[i]) {
				if (i >= sizeEstimate) {
					return i;
				}
			}
			for (int j = 2; j <= i && i * j < sizeEstimate + 100; j++) {
				primes[i * j] = true;
				if (i % j == 0) break;
			}
		}
		return 0;
	}

	/**
	 *  Construct a new empty hash table intended to hold roughly sizeEstimate
	 *  entries.  (The precise number of buckets is up to you, but we recommend
	 *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/
	public HashTableChained(int sizeEstimate) {
		// Your solution here.
		this.sizeT = nextPrime(sizeEstimate);
		this.table = new Vector();
		for (int i = 0; i < this.sizeT; i++)
			table.add(null);
		this.num = 0;
		numOfKeysEach = new ArrayList<Integer>();
	}

	/**
	 *  Construct a new empty hash table with a default size.  Say, a prime in
	 *  the neighborhood of 100.
	 **/

	public HashTableChained() {
		// Your solution here.
		this.sizeT = nextPrime(100);
		this.table = new Vector(this.sizeT);
		for (int i = 0; i < this.sizeT; i++)
			table.add(null);
		this.num = 0;
		numOfKeysEach = new ArrayList<Integer>();
	}

	/**
	 *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 *  to a value in the range 0...(size of hash table) - 1.
	 *
	 *  This function should have package protection (so we can test it), and
	 *  should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		// Replace the following line with your solution.
		long tmp = (long)code;
		tmp = ((tmp * 13L + 31L) % 1000000007L) % ((long)(sizeT));
		return (int)tmp;
	}

	/**
	 *  Returns the number of entries stored in the dictionary.  Entries with
	 *  the same key (or even the same key and value) each still count as
	 *  a separate entry.
	 *  @return number of entries in the dictionary.
	 **/

	public int size() {
		// Replace the following line with your solution.
		return this.sizeT;
	}

	/**
	 *  Tests if the dictionary is empty.
	 *
	 *  @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		// Replace the following line with your solution.
		return this.num == 0;
	}

	/**
	 *  Create a new Entry object referencing the input key and associated value,
	 *  and insert the entry into the dictionary.  Return a reference to the new
	 *  entry.  Multiple entries with the same key (or even the same key and
	 *  value) can coexist in the dictionary.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the key by which the entry can be retrieved.
	 *  @param value an arbitrary object.
	 *  @return an entry containing the key and value.
	 **/

	public Entry insert(Object key, Object value) {
		// Replace the following line with your solution.
		int idx = this.compFunction(key.hashCode());
		if (table.get(idx) == null) {
			table.set(idx, new LinkedList<Entry>());
		}
		Entry newEntry = new Entry();
		newEntry.key = key;
		newEntry.value = value;
		table.get(idx).add(newEntry);
		this.num++;
		return newEntry;
	}

	/**
	 *  Search for an entry with the specified key.  If such an entry is found,
	 *  return it; otherwise return null.  If several entries have the specified
	 *  key, choose one arbitrarily and return it.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the search key.
	 *  @return an entry containing the key and an associated value, or null if
	 *          no entry contains the specified key.
	 **/

	public Entry find(Object key) {
		// Replace the following line with your solution.
		int idx = this.compFunction(key.hashCode());
		Iterator<Entry> iter = table.get(idx).iterator();
		while (iter.hasNext()) {
			Entry ent = iter.next();
			if (ent.key.equals(key)) {
				return ent;
			}
		}
		return null;
	}

	/**
	 *  Remove an entry with the specified key.  If such an entry is found,
	 *  remove it from the table and return it; otherwise return null.
	 *  If several entries have the specified key, choose one arbitrarily, then
	 *  remove and return it.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the search key.
	 *  @return an entry containing the key and an associated value, or null if
	 *          no entry contains the specified key.
	 */

	public Entry remove(Object key) {
		// Replace the following line with your solution.
		int idx = this.compFunction(key.hashCode());
		Iterator<Entry> iter = table.get(idx).iterator();
		while (iter.hasNext()) {
			Entry ent = iter.next();
			if (ent.key.equals(key)) {
				table.get(idx).remove(ent);
				this.num--;
				return ent;
			}
		}
		return null;
	}

	/**
	 *  Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		// Your solution here.
		this.num = 0;
		for (int i = 0; i < table.size(); i++)
			table.set(i, null);
	}

	private int collisons() {
		int ret = 0;
		numOfKeysEach.clear();
		Iterator<List<Entry>> iter = table.iterator();
		while (iter.hasNext()) {
			List<Entry> l = iter.next();
			if (l != null && !l.isEmpty()) {
				ret += l.size() - 1;
				numOfKeysEach.add(l.size());
			} else {
				numOfKeysEach.add(0);
			}
		}
		this.coll = ret;
		return ret;
	}

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("bucket size: " + this.size() + "\n");
		strb.append("number of entries: " + this.num + "\n");
		strb.append("expected collison: " + (this.num - this.size() + this.size() * Math.pow((1.0 - 1.0 / (this.size() * 1.0)), this.num)) + "\n");
		strb.append("actual collison: " + this.collisons() + "\n");
		strb.append("--------------------------------\n");
		strb.append("number of entries in each bucket:\n");
		Iterator<Integer> it = numOfKeysEach.iterator();
		int i = 0;
		while (it.hasNext()) {
			int u = it.next();
			if (u > 0) strb.append("bucket " + i + ": " + u + "\n");
			i++;
		}
		strb.append("-----------------END------------------\n");
		return strb.toString();
	}
}
