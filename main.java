4a. Write a program that reads a numbers, uses a Map to 
count the occurrences of each unique number, and then 
prints the number counts. 
Code: 
  import java.util.*;

public class Practical4a {
    public static void main(String[] args) {

        int a[] = {1, 13, 4, 1, 41, 31, 31, 4, 13, 2};
        String b[] = {"Sakshi", "Dhanashree", "Snehal", "Urvashi"};


        ArrayList<Integer> al = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            al.add(a[i]);
        }

        
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < al.size(); i++) {
            hm.putIfAbsent(al.get(i), Collections.frequency(al, al.get(i)));
        }

        System.out.println(hm);

       
        ArrayList<String> bl = new ArrayList<>();

        for (int i = 0; i < b.length; i++) {
            bl.add(b[i]);
        }

        
        HashMap<String, String> hm1 = new HashMap<>();

        for (int i = 0; i < bl.size(); i++) {
            hm1.putIfAbsent(bl.get(i),
                    String.valueOf(Collections.frequency(bl, bl.get(i))));
        }

        System.out.println(hm1);
    }
}

4b. Write a program that reads a text file, uses a Map to 
count the occurrences of each unique word, and then prints 
the word counts. 
Code: 
import java.util.*; 
public class HashMap1 { 
public static void main(String[] args) { 
HashMap<Integer, String> map = new HashMap<>(17,0.5f); 
map.put(31, "Urvashi"); 
map.put(11, "Snehal"); 
map.put(2, "Sakshi"); 
map.put(2, "Dhanu"); 
map.put(2, "Sonu"); 
System.out.println(map); 
String student = map.get(31); 
System.out.println(student); 
String s = map.get(69); 
System.out.println(s); 
System.out.println(map.containsKey(2)); 
System.out.println(map.containsValue("Sakshi")); 
for (int i : map.keySet()) { 
System.out.println(map.get(i)); 
} 
Set<Map.Entry<Integer, String>> entries = map.entrySet(); 
for (Map.Entry<Integer, String> entry : entries) { 
entry.setValue(entry.getValue().toUpperCase()); 
} 
System.out.println(map); 
boolean res = map.remove(31, "Sonu"); 
System.out.println("REMOVED ? :" + res); 
System.out.println(map); 
List<Integer> list = Arrays.asList(2, 4, 32, 43, 4, 432); 
list.contains(32); 
} 
} 

4c. Write a program that reads a text and number, uses a 
Map to count the occurrences of each unique word and 
number, and then prints the counts.  
Code: 
import java.util.*; 
public class Count { 
public static void main(String[] args) { 
Scanner sc = new Scanner(System.in); 
// Input text from user 
System.out.println("Enter a text (words and numbers):"); 
String input = sc.nextLine(); 
// Split input into tokens (words/numbers) 
String[] tokens = input.split("\\s+"); 
// Map to store word/number counts 
Map<String, Integer> countMap = new HashMap<>(); 
// Count occurrences 
for (String token : tokens) { 
token = token.toLowerCase(); // normalize 
countMap.put(token, countMap.getOrDefault(token, 0) + 1); 
} 
// Print the results 
System.out.println("\nOccurrences of each word/number:"); 
for (Map.Entry<String, Integer> entry : countMap.entrySet()) { 
System.out.println(entry.getKey() + " : " + entry.getValue()); 
} 
sc.close(); 
} 
} 

5a.Processing a List with Sort and Comparator 
Code: 
import java.util.ArrayList; 
import java.lang.reflect.Field; 
public class Practical_5a { 
    public static void main(String[] args) throws Exception { 
        ArrayList<Integer> list = new ArrayList<>(11); // initial capacity is 11 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        list.add(1); 
        Field elementDataField = 
ArrayList.class.getDeclaredField("elementData"); 
        elementDataField.setAccessible(true); 
        Object[] elementData = (Object[]) elementDataField.get(list); 
        System.out.println("ArrayList capacity: " + elementData.length); 
        list.add(1); // add one more element, capacity should increase 
        elementData = (Object[]) elementDataField.get(list); 
        System.out.println("ArrayList capacity: " + elementData.length); 
    } 
} 

5b.check ArrayList size,whether shrinks automatically even 
after removing elements from the array. 
Code: 
import java.util.ArrayList; 
import java.lang.reflect.Field; 
public class Practical_5b { 
public static void main(String[] args) throws Exception{ 
ArrayList<Integer> list = new ArrayList<>(11); // array size is 11 
list.add(1); // You can write for loop,but to make u understand the 
…how Arraylist size works 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
list.add(1); 
Field field = ArrayList.class.getDeclaredField("elementData"); 
field.setAccessible(true); 
Object[] elementData = (Object[]) field.get(list); 
System.out.println("ArrayList capacity: " + elementData.length); 
list.add(1); 
elementData = (Object[]) field.get(list); 
System.out.println("ArrayList capacity: " + elementData.length); 
list.remove(2); //one time removed , the size was same..so try to remove 
more elements 
list.remove(2); 
list.remove(2); 
list.remove(2); 
list.remove(2); 
list.remove(2); 
list.remove(2);
list.remove(2);// size was not reduced, we can trim the size 
elementData = (Object[]) field.get(list); 
System.out.println("ArrayList capacity: " + elementData.length); 
list.trimToSize(); // It reduce the size,to save the internal memory 
elementData = (Object[]) field.get(list); 
System.out.println("ArrayList capacity: " + elementData.length); 
} 
} 

5c.Sort the Integer and String element in ascending and 
descending order by using Lambda expression. 
Code: 
import java.util.Arrays; 
import java.util.Collections; 
import java.util.Comparator; 
public class Practical_5c { 
public static void main(String[] args) { 
// Integer array 
Integer[] intArray = {7, 4, 9, 3}; 
// String array 
String[] strArray = {"Sita", "Ram", "Laxman"}; 
// Sorting integers in ascending order using lambda 
Arrays.sort(intArray, (a, b) -> a - b); 
System.out.println("Integers Ascending: " + Arrays.toString(intArray)); 
// Sorting integers in descending order using lambda 
Arrays.sort(intArray, (a, b) -> b - a); 
System.out.println("Integers Descending: " + Arrays.toString(intArray)); 
// Sorting strings in ascending order using lambda 
Arrays.sort(strArray, (a, b) -> a.compareTo(b)); 
System.out.println("Strings Ascending: " + Arrays.toString(strArray)); 

Arrays.sort(strArray, (a, b) -> b.compareTo(a)); 
System.out.println("Strings Descending: " + Arrays.toString(strArray));
} 
} 

5d.Sorting collection with a Comparator. 
Code: 
import java.util.*; 
class Student { 
private String name; 
private double gpa; 
public Student(String name, double gpa) { 
this.name = name; 
this.gpa = gpa; 
} 
public String getName() { 
return name; 
} 
public double getGpa() { 
return gpa; 
} 
} 
public class Practical_5d { 
public static void main(String[] args) { 
List<Student> students = new ArrayList<>(); 
students.add(new Student("Charlie", 3.5)); 
students.add(new Student("Bob", 3.7)); 
students.add(new Student("Alice", 3.5)); 
students.add(new Student("Akshit", 3.9)); 
Comparator<Student> comparator = Comparator 
.comparingDouble(Student::getGpa).reversed() 
.thenComparing(Student::getName); 
Collections.sort(students, comparator); 
for (Student s : students) { 
System.out.println(s.getName() + ": " + s.getGpa()); 
} 
} 
} 


6a)Convert all strings to uppercase. 
Code: 
import java.util.*; 
import java.util.stream.Collectors; 
public class Practical_6a { 
public static void main(String[] args) { 
List<String> words = Arrays.asList("apple", "banana", "apricot", "grape", 
"avocado", "mango"); 
// Convert to uppercase 
List<String> upperCaseWords = words.stream() 
.map(String::toUpperCase) 
.collect(Collectors.toList()); 
System.out.println("Original List: " + words); 
System.out.println("Uppercase List: " + upperCaseWords); 
} 
} 

6b)Filter out strings that start with a specific letter. 
Code: 
import java.util.*; 
import java.util.stream.Collectors; 
public class Practical_6b { 
public static void main(String[] args) { 
List<String> words = Arrays.asList("APPLE", "BANANA", "APRICOT", 
"GRAPE", "AVOCADO", "MANGO"); 
char filterLetter = 'A'; 
// Filter out strings starting with 'A' 
List<String> filteredWords = words.stream() 
.filter(word -> !word.startsWith(String.valueOf(filterLetter))) 
.collect(Collectors.toList()); 
System.out.println("Original List: " + words); 
System.out.println("Filtered List (not starting with '" + filterLetter + "'): " 
+ filteredWords); 
} 
} 

6c)Sort the remaining strings. 
Code: 
import java.util.*; 
import java.util.stream.Collectors; 
public class Practical_6c { 
public static void main(String[] args) { 
List<String> words = Arrays.asList("BANANA", "GRAPE", "MANGO"); 
// Sort alphabetically 
List<String> sortedWords = words.stream() 
.sorted() 
.collect(Collectors.toList()); 
System.out.println("Original List: " + words); 
System.out.println("Sorted List: " + sortedWords); 
} 
}

6d)Collect the result into a new list. 
Code: 
import java.util.*; 
import java.util.stream.Collectors; 
public class Practical_6d { 
public static void main(String[] args) { 
List<String> words = Arrays.asList("BANANA", "GRAPE", "MANGO"); 
// Collect into a new list 
List<String> finalList = words.stream() 
.collect(Collectors.toList()); 
System.out.println("Original List: " + words); 
System.out.println("Final Collected List: " + finalList); 
} 
} 

Combination of Practical 6: 
Code: 
import java.util.*; 
import java.util.stream.Collectors; 
 
public class Practical_6 { 
    public static void main(String[] args) { 
        // Step 0: Sample list of strings 
        List<String> words = Arrays.asList("apple", "banana", "apricot", "grape", 
"avocado", "mango"); 
 
        // Step 1: Convert all strings to uppercase (6a) 
        List<String> upperCaseWords = words.stream() 
                .map(String::toUpperCase) 
                .collect(Collectors.toList()); 
        System.out.println("Step 6a - Uppercase: " + upperCaseWords); 
 
        // Step 2: Filter out strings starting with a specific letter (6b) 
        char filterLetter = 'A'; 
        List<String> filteredWords = upperCaseWords.stream() 
                .filter(word -> !word.startsWith(String.valueOf(filterLetter))) 
                .collect(Collectors.toList()); 
        System.out.println("Step 6b - Filtered: " + filteredWords); 
 
        // Step 3: Sort the remaining strings alphabetically (6c) 
        List<String> sortedWords = filteredWords.stream() 
                .sorted() 
                .collect(Collectors.toList()); 
        System.out.println("Step 6c - Sorted: " + sortedWords); 
 
        // Step 4: Collect the result into a new list (6d) 
        List<String> finalList = new ArrayList<>(sortedWords); 
        System.out.println("Step 6d - Final Collected List: " + finalList); 
    } 
} 

7a.Write a program to check the pattern whether it matches the string 
Code: 
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
public class Practical_7a { 
public static void main(String[] args) { 
//String regex = "a"; 
Pattern pattern = Pattern.compile("a"); 
Matcher matcher = pattern.matcher("a"); 
boolean matches = matcher.matches(); 
System.out.println("result : "+matcher); 
System.out.printf("result : "+matches); 
Pattern pattern1 = Pattern.compile("a*b*"); 
Matcher matcher1 = pattern1.matcher("aab"); 
boolean matches1 = matcher1.matches(); 
System.out.println("result1 : "+matcher1); 
System.out.printf("result1 : "+matches1); 
} 
} 

7b.Create RE that accept alphanumeric characters only 
Code: 
import java.util.Scanner; 
public class Practical_7b { 
public static void main(String[] args) { 
Scanner sc = new Scanner(System.in); 
System.out.print("Enter a string to check if it contains only 
alphanumeric characters: "); 
String input = sc.nextLine();  
boolean isAlphanumeric = input.matches("^[a-zA-Z0-9]+$"); 
System.out.println("Is alphanumeric? " + isAlphanumeric); 
sc.close(); 
} 
} 

7c.Create RE that accept 10 digits numbers only. 
Code: 
import java.util.Scanner; 
public class Practical_7c { 
public static void main(String[] args) { 
Scanner sc = new Scanner(System.in); 
System.out.print("Enter a number to check if it is a 10-digit number: "); 
String input = sc.nextLine(); 
boolean is10Digit = input.matches("^[0-9]{10}$"); 
System.out.println("Is a valid 10-digit number? " + is10Digit); 
sc.close(); 
} 
} 

7d.Write a RE to match email address 
Code: 
import java.util.Scanner; 
public class Practical_7d { 
public static void main(String[] args) { 
Scanner sc = new Scanner(System.in); 
System.out.print("Enter an email address to validate: "); 
String input = sc.nextLine(); 
String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA
Z]{2,6}$"; 
boolean isValidEmail = input.matches(emailRegex); 
System.out.println("Is a valid email? " + isValidEmail); 
sc.close(); 
} 
} 

8a.Create a simple JavaBean with few properties and appropriate getter 
and setter methods. 
Code: 
Person.java 
public class Person { 
    private String name; 
    private int age; 
    private String city; 
 
    public Person() {} 
 
    // Getter and Setter for name 
    public String getName() { 
        return name; 
    } 
    public void setName(String name) { 
        this.name = name; 
    } 
 
    // Getter and Setter for age 
    public int getAge() { 
        return age; 
    } 
    public void setAge(int age) { 
        this.age = age; 
    } 
 
    // Getter and Setter for city 
    public String getCity() { 
        return city; 
    } 
    public void setCity(String city) { 
        this.city = city; 
    } 
} 
 
Practical_8.java 
public class Practical_8 { 
    public static void main(String[] args) { 
        Person person = new Person();
         person.setName("Urvashi Patel"); 
        person.setAge(20); 
        person.setCity("Mumbai"); 
 
        System.out.println("Name: " + person.getName()); 
        System.out.println("Age: " + person.getAge()); 
        System.out.println("City: " + person.getCity()); 
    } 
} 

9a)  import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamIntermediateOperationsExample {
    public static void main(String[] args) {

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("Reflection", "Collection", "Stream"),
                Arrays.asList("Structure", "State", "Flow"),
                Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
        );


        Set<String> intermediateResults = new HashSet<>();


        List<String> result = listOfLists.stream()
                .flatMap(List::stream)
                .filter(s -> s.startsWith("S"))
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .peek(s -> intermediateResults.add(s))
                .collect(Collectors.toList());


        System.out.println("Intermediate Results:");
        intermediateResults.forEach(System.out::println);


        System.out.println("Final Result:");
        result.forEach(System.out::println);
    }
}

9b)  import java.util.*;
import java.util.stream.Collectors;
​
public class StreamTerminalOperationsExample {
    public static void main(String[] args) {
        // Sample data
        List<String> names = Arrays.asList( "Reflection", "Collection", "Stream",
            "Structure", "Sorting", "State"  );
​
        // forEach: Print each name
        System.out.println("forEach:");
        names.stream().forEach(System.out::println);
​
        // collect: Collect names starting with 'S' into a list
        List<String> sNames = names.stream()
                                   .filter(name -> name.startsWith("S"))
                                   .collect(Collectors.toList());
        System.out.println("\ncollect (names starting with 'S'):");
        sNames.forEach(System.out::println);
​
        // reduce: Concatenate all names into a single string
        String concatenatedNames = names.stream().reduce(
            "",
            (partialString, element) -> partialString + " " + element
        );
        System.out.println("\nreduce (concatenated names):");
        System.out.println(concatenatedNames.trim());
​
        // count: Count the number of names
        long count = names.stream().count();
        System.out.println("\ncount:");
        System.out.println(count);
​
        // findFirst: Find the first name
        Optional<String> firstName = names.stream().findFirst();
        System.out.println("\nfindFirst:");
        firstName.ifPresent(System.out::println);
​
        // allMatch: Check if all names start with 'S'
        boolean allStartWithS = names.stream().allMatch(
            name -> name.startsWith("S")
        );
        System.out.println("\nallMatch (all start with 'S'):");
        System.out.println(allStartWithS);
​
        // anyMatch: Check if any name starts with 'S'
        boolean anyStartWithS = names.stream().anyMatch(
            name -> name.startsWith("S")
        );
        System.out.println("\nanyMatch (any start with 'S'):");
        System.out.println(anyStartWithS);
    }
}