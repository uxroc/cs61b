import java.io.*;
import java.util.*;

interface I {
	public void print(int a, int b);
	public final int u = 13;
};

class X {
	public final int u = 0;
	public X() {
		System.out.println("X() called.");
	}
	public void print() {
		System.out.println("Xprint() called.");
	}
};

class Y extends X implements I{
	public Y() {
		super();
		System.out.println("...and Y() called.");
	}
	public void print() {
		System.out.println("yprint called.");
	}
	public void print(int a, int b) {
		System.out.println("a"+a+"b"+b);
	}
};

class AssAndCast {
	public static void main(String args[]) {
		X[] xa = new X[5];
		for (int i = 0; i < 5; i++)
			xa[i] = new X();

		Y[] ya = new Y[5];
		for(int i = 0; i < 5; i++)
			ya[i] = new Y();

		X a = (X)(ya[0]);
		X b = ya[0];
		((X)ya[0]).print();

		((Y)xa[0]).print();

	}
};
