//without generic

import java.util.ArrayList;
public class main{
    public static void main(String[] args){
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add(124);
        list.add(34);
        String str = (String) list.get(0);
        String str1 = (String) list.get(1);
    }
}
-------------------------------------------------------------------------
With Generics 
 import java.util.ArrayList;  
 public class Main {  
 public static void main(String[] args) {  
ArrayList<String> list = new ArrayList<>(); 
list.add("Hello");
list.add("World");
 String s = list.get(0); 
 String s1 = list.get(1);      } } 

---------------------------------------------------------------------------------------------------------------
string,int,float,double

public class Box<T> {
 private T value;     
 
  public void setValue(T value) {  
   this.value = value;     
   }
   public T getValue() { 
    return value;     
    }  
    java public class pract1_3 {
     public static void main(String[] args) { 
     Box<Integer> box = new Box<>();       
    box.setValue(15);         
    Integer i = box.getValue(); // no cast needed  
    // System.out.println(i);     } } 
---------------------------------------------------------------------------------
    one or more parameter

    class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class Main {

    public static void main(String[] args) {

        Pair<String, Integer> pair =
            new Pair<>("Age", 30);

        System.out.println("Key: " + pair.getKey());
        System.out.println("Value: " + pair.getValue());
    }
}

----------------------------------------------------------------------------------
 using  Lambda Expression with ArrayList

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        numbers.forEach((n) -> {
            System.out.println(n);
        });
    }
}
------------------------------------------------------------------------
using lambda method

interface StringFunction1 {
    String run(String str);
}

public class Main2 {

    public static void main(String[] args) {

        // Lambda expression to add !
        StringFunction1 exclaim = (s) -> s + "!";

        // Lambda expression to add ?
        StringFunction1 ask = (s) -> s + "?";

        // Calling method with exclaim lambda
        printFormatted("Hello", exclaim);

        // Calling method with ask lambda
        printFormatted("Hello", ask);
    }

    // Method that accepts String and Lambda expression
    public static void printFormatted(String str, StringFunction1 format) {

        String result = format.run(str);

        System.out.println(result);
    }
}

-----------------------------------------------------------
 lambda with parameter 

interface Add {
    int add(int a, int b);
}

public class parameter {
    public static void main(String[] args) {

        Add addition = (a, b) -> a + b;

        int result = addition.add(90, 22);

        System.out.println("The result of 90 + 22 is: " + result);
    }
}
--------------------------------------------------------------------------------
lambda string exampla

@FunctionalInterface
interface StringLength {
    int getLength(String s);
}

public class StringLength1 {
    public static void main(String[] args) {

        StringLength lengthc = (s) -> s.length();

        int length = lengthc.getLength("Urvashi Patel");

        System.out.println("The length of the string is: " + length);
    }
}
--------------------------------------------------------------------
lamdda to print each element

import java.util.ArrayList;

public class Element {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(5);
        numbers.add(9);
        numbers.add(4);
        numbers.add(3);
        numbers.add(7);

        for (Integer n : numbers) {
            System.out.println(n);
        }
    }
}
------------------------------------------------------------------
lamda format string
interface StringFunction {
    String run(String str);
}

public class formatstr {
    public static void main(String[] args) {

        StringFunction exclaim = (s) -> s + "!";
        StringFunction ask = (s) -> s + "?";

        printFormatted("Hello Ram", exclaim);
        printFormatted("Hello Sita", ask);
        printFormatted("Hello Laxman", exclaim);
    }

    public static void printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        System.out.println(result);
    }
}

