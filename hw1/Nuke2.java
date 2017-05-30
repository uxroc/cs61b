import java.io.*;
import java.util.Scanner;

public class Nuke2 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		String str;
		str = sc.nextLine();
		str = str.substring(0, 1) + str.substring(2, str.length());
		System.out.println(str);
	}
}
