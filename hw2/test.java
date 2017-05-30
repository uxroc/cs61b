import java.util.*;
import java.io.*;
import java.lang.*;

class test {
  public static void main(String args[]) throws Exception{
    String s = new String("   1/ 1/ 01 ///111/");
    String[] ss = s.split("/",3);
    for(int i = 0; i<ss.length; i++)
      System.out.println(ss[i]);
    try {
      int month = Integer.parseInt(ss[0].trim());
      int day = Integer.parseInt(ss[1].trim());
      int year = Integer.parseInt(ss[2].trim());
      System.out.println(month+day+year);
    } catch (NumberFormatException e) {
      System.out.println("not valid.");
      System.exit(0);
    }
  }
}
