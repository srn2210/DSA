import java.util.*;
class SieveOfEratosthenes {
    // A very efficient technique to find out the prime numbers in a given range.
    // Create an array of the range given in the input.
    // Start with 2 and mark off all of its multiples as not prime, then move on to the next unmarked number and repeat.
    
    // Time Complexity : O(n * log (log (n)))
    // Space Complexity : O(n)
    Set<Integer> primeFactors(int x) {                                          // Returns all the prime factors of given number
        var arr = new int[x+1];                                                 // Instead of a boolean array, we initialize an array of int
        for(int i=1; i<=x; i++) arr[i] = i;                                     // We have the prime factor of the number as the value of the array
        for(int i=2; i*i<=x; i++) {                                             
            if(arr[i] == i) {                                                   // Prime numbers will be left with only themselves as the factors
                for(int j=i*i; j<=x; j+=i) {                                    
                    arr[j] = i;                                                 // Non-prime numbers will be filled with their prime factor
                }
            }
        }
        var set = new HashSet<Integer>();                                       // Here instead of a list, we collect the numbers into a set to avoid repeated elements
        while(x != 1) {                                                         // We start off with input number
            set.add(arr[x]);                                                    // Add its factor to the set
            x /= arr[x];                                                        // Divide the number with its factor
        }
        return set;
    }
    List<Integer> listOfPrimes(int n) {                                         // Returns a list of all the prime numbers in the range (2, n) inclusive bounds
        var arr = new boolean[n+1];                                             // Initialize an array of booleans to mark the numbers as prime in the given range
        for(int i=2; i<=n; i++) arr[i] = true;                                  // Initially mark all numbers as prime
        for(int i=2; i*i<=n; i++) {                                             // Start with 2 and check if the square of the number exceeds the input n
            if(!arr[i]) continue;                                               // If the number is marked as not prime, then we skip it
            for(int j=i*i; j<=n; j+=i) {                                        // If the number is marked, we mark all of its multiples as not prime
                arr[j] = false;
            }
        }
        var ans = new ArrayList<Integer>();
        for(int i=2; i<=n; i++) if(arr[i]) ans.add(i);                          // Add all the marked numbers to the list
        return ans;
    }
    public static void main(String[] args) {
        SieveOfEratosthenes obj = new SieveOfEratosthenes();
        System.out.println(obj.listOfPrimes(100));
        System.out.println(obj.primeFactors(42));
    }
}