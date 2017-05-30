import java.util.*;

public class TestNullVec {
	public static void main(String args[]) {
		Vector<String> vec = new Vector<>(10);
		vec.add("abc");
		vec.add("world");
		Iterator<String> it = vec.iterator();
		while (it.hasNext()) {
			System.out.print(it.next());
			it.remove();
		}
		System.out.println(vec.size());
	}
}
