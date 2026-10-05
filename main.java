6.A) Create a function that calculates the sum of a slice of numbers by dividing the slice into two parts, calculating the sum of each part in a separate goroutine, and then combining the results using channels. 

package main

import "fmt"

func sumSlice(slice []int, c chan int) {
	sum := 0

	for _, v := range slice {
		sum += v
	}

	c <- sum
}

func calculateConcurrentSum(numbers []int) int {
	c := make(chan int)

	mid := len(numbers) / 2

	go sumSlice(numbers[:mid], c)
	go sumSlice(numbers[mid:], c)

	sum1 := <-c
	sum2 := <-c

	return sum1 + sum2
}

func main() {
	numbers := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}

	fmt.Printf("Original slice: %v\n", numbers)

	total := calculateConcurrentSum(numbers)

	fmt.Printf("Concurrent sum: %d\n", total)
}


6.B)Write a Go program that finds prime numbers up to a given limit using multiple goroutines to speed up the process. Use channels to collect the prime numbers. Code : 

package main

import (
	"fmt"
	"sync"
	"time"
)

func isPrime(n int) bool {
	if n < 2 {
		return false
	}

	for i := 2; i*i <= n; i++ {
		if n%i == 0 {
			return false
		}
	}

	return true
}

func findPrimes(start, end int, wg *sync.WaitGroup, ch chan<- int) {
	defer wg.Done()

	for i := start; i <= end; i++ {
		if isPrime(i) {
			ch <- i
		}
	}
}

func main() {
	var limit int

	fmt.Print("Enter upper limit: ")
	fmt.Scan(&limit)

	startTime := time.Now()

	primes := make(chan int)
	var wg sync.WaitGroup

	workers := 4
	step := (limit - 1) / workers

	for i := 0; i < workers; i++ {
		a := 2 + i*step
		b := a + step - 1

		if i == workers-1 {
			b = limit
		}

		wg.Add(1)
		go findPrimes(a, b, &wg, primes)
	}

	go func() {
		wg.Wait()
		close(primes)
	}()

	var results []int

	for p := range primes {
		results = append(results, p)
	}

	fmt.Printf("Found %d primes in %v:\n", len(results), time.Since(startTime))
	fmt.Println(results)
}

7a)  server
package main

import (
	"fmt"
	"log"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	if r.URL.Path != "/" {
		http.NotFound(w, r)
		return
	}

	fmt.Fprintf(w, "Hello, World!")
}

func main() {
	http.HandleFunc("/", helloHandler)

	port := ":8080"

	log.Printf("Server starting on port %s\n", port)
	log.Println("Access it at http://localhost:8080")

	err := http.ListenAndServe(port, nil)

	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}

8.A) Create a Go program that defines a struct representing a person (with name, age, and city). Encode an instance of this struct into JSON format and then decode a given JSON string back into the struct.
		
        package main

import (
	"encoding/json"
	"fmt"
	"log"
)

type Person struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
	City string `json:"city"`
}

func main() {
	personToEncode := Person{
		Name: "Alice",
		Age:  30,
		City: "New York",
	}

	fmt.Println("--- Encoding Struct to JSON ---")
	fmt.Printf("Original Struct: %+v\n", personToEncode)

	jsonData, err := json.Marshal(personToEncode)

	if err != nil {
		log.Fatalf("Error marshalling to JSON: %s", err)
	}

	fmt.Printf("Encoded JSON (as byte slice): %v\n\n", jsonData)
	fmt.Printf("Encoded JSON: %s\n", string(jsonData))

	fmt.Println("\n--- Decoding JSON to Struct ---")

	var personToDecode Person

	err = json.Unmarshal(jsonData, &personToDecode)

	if err != nil {
		log.Fatalf("Error unmarshalling JSON: %s", err)
	}

	fmt.Printf("Decoded Struct: %+v\n", personToDecode)

	fmt.Printf(
		"Name: %s, Age: %d, City: %s\n",
		personToDecode.Name,
		personToDecode.Age,
		personToDecode.City,
	)
}

8.b)Create a Go program that reads a JSON file containing information about books (title, author, publication year) and prints the details of each book.
Code :
package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
)

type Book struct {
	Title           string `json:"title"`
	Author          string `json:"author"`
	PublicationYear int    `json:"publication_year"`
}

func main() {
	fileName := "books.json"

	jsonData, err := ioutil.ReadFile(fileName)

	if err != nil {
		log.Fatalf("Error reading JSON file: %s", err)
	}

	var books []Book

	err = json.Unmarshal(jsonData, &books)

	if err != nil {
		log.Fatalf("Error unmarshalling JSON data: %s", err)
	}

	fmt.Println("Successfully parsed book data:")

	for i, book := range books {
		fmt.Printf("\n--- Book %d ---\n", i+1)
		fmt.Printf("Title: %s\n", book.Title)
		fmt.Printf("Author: %s\n", book.Author)
		fmt.Printf("Year: %d\n", book.PublicationYear)
	}
}
books.json make a folder init

[
    {
        "title": "The Go Programming Language",
        "author": "Alan A. A. Donovan & Brian W. Kernighan",
        "publication_year": 2015
    },
    {
        "title": "The Fault in Our Stars",
        "author": "John Green",
        "publication_year": 2012
    }
]

9.A)Write a Go program that takes a directory and a search string as input and finds all files in that directory (and its subdirectories) that contain the search string. 
Code :
package main

import (
	"bufio"
	"fmt"
	"io/fs"
	"os"
	"path/filepath"
	"strings"
)

func searchInFile(path string, query string) (bool, error) {
	file, err := os.Open(path)

	if err != nil {
		return false, err
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		if strings.Contains(scanner.Text(), query) {
			return true, nil
		}
	}

	return false, scanner.Err()
}

func main() {
	searchDir := "./"
	searchQuery := "TODO"

	fmt.Printf("Searching for %q in %s...\n", searchQuery, searchDir)

	err := filepath.WalkDir(searchDir,
		func(path string, d fs.DirEntry, err error) error {
			if err != nil {
				return err
			}

			if d.IsDir() {
				return nil
			}

			found, err := searchInFile(path, searchQuery)

			if err != nil {
				fmt.Fprintf(os.Stderr, "Error reading %s: %v\n", path, err)
				return nil
			}

			if found {
				fmt.Println(path)
			}

			return nil
		})

	if err != nil {
		fmt.Printf("Error walking the path: %v\n", err)
	}
}


10.A) Choose one or more of the functions you implemented in the previous exercises (e.g., the string reversal function, the calculator functions, or the palindrome checker) and write a comprehensive set of unit tests for it using the testing package. 
Code :
package main
import (
	"bufio"
	"fmt"
	"os"
)
func main() {
	var filePath string
	fmt.Print("Enter the path to the text file: ")
	fmt.Scanln(&filePath)
	file, err := os.Open(filePath)
	if err != nil {
		if os.IsNotExist(err) {
			fmt.Printf("Error: File not found at '%s'\n", filePath)
		} else {
			fmt.Printf("Error opening file: %v\n", err)
		}
		return
	}
	defer file.Close()
	scanner := bufio.NewScanner(file)
	fmt.Println("\nFile Content")
	fmt.Println("*************")
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}
	if err := scanner.Err(); err != nil {
		fmt.Printf("Error reading file: %v\n", err)
	}
	fmt.Println("*************")
}

. Save the code

Create a folder:

D:\tyit31\Pract10A

Create a file:

main.go

Put your code inside it.

Also remove the backslashes before *. These:

\*\*\*\*\*\*\*\*\*\*\*\*\*

should be:

*************************
2. Create a text file

In the same folder, create:

manthan.txt

Put some text inside, for example:

Hello Krishna
This is a Go file reading practical.
Go programming is interesting.

Your folder should be:

D:\tyit31\Pract10A
│
├── main.go
└── manthan.txt
3. Open CMD

Run:

cd /d D:\tyit31\Pract10A

Then:

go mod init pract10a
4. Run the program
go run main.go

It will ask:

Enter the path to the text file:

Type:

manthan.txt
5. Expected output
Enter the path to the text file: manthan.txt

File Content
*************************
Hello Krishna
This is a Go file reading practical.
Go programming is interesting.
*************************
But for Question 10.A

The question specifically asks for unit tests using the testing package. So if this is really your 10.A practical, this main.go is likely the wrong code for that question.

For the palindrome version I gave you earlier, you should instead have:

palindrome.go
palindrome_test.go
go.mod

and run:

go test -v

--------------------------------------------------------------------------------------------------------------------------
palindrom.go
package main

func isPalindrome(s string) bool {
	for i := 0; i < len(s)/2; i++ {
		if s[i] != s[len(s)-1-i] {
			return false
		}
	}

	return true
}


palindrome_test.go


package main


import "testing"

func TestIsPalindrome(t *testing.T) {
	tests := []struct {
		input    string
		expected bool
	}{
		{"madam", true},
		{"racecar", true},
		{"hello", false},
		{"level", true},
		{"world", false},
		{"", true},
	}

	for _, test := range tests {
		result := isPalindrome(test.input)

		if result != test.expected {
			t.Errorf(
				"isPalindrome(%q) = %v, expected %v",
				test.input,
				result,
				test.expected,
			)
		}
	}
}

step to run 10 Code

go mod init pract10a
go test