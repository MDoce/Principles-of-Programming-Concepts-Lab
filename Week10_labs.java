import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		//1. Collect elements into a Set
		Set<String> fruitset = fruit.stream().collect(Collectors.toSet()); //puts fruits in set no duplcates
		System.out.println("FruitSet: " + fruitset);
		System.out.println(); //so print statements arent so close together

        //2. Collect the fruit into groups based on their first character
		Map<Character, List<String>> byFC = fruit.stream().collect(Collectors.groupingBy(f -> f.charAt(0))); //charAt(0) gets first char of each string and then groups by that charcater. That character becomes key
		Map<Character, Set<String>> byFCSet = fruit.stream().collect(Collectors.groupingBy(f -> f.charAt(0), Collectors.toSet())); //this does the same thing as above but now we dont have duplicates because its a set

		System.out.println("Grouped By First Char: " + byFC);
		System.out.println("Grouped By First Char Set: " + byFCSet);
		System.out.println();

		//3. Group fruit by the length of the name
		Map<Integer, List<String>> byLength1 = fruit.stream().collect(Collectors.groupingBy(f -> f.length())); //same patterns as before but now we use f.length() to group by length. Integer from f.length becomes key.
		Map<Integer, List<String>> byLength2 = fruit.stream().collect(Collectors.groupingBy(String::length)); //method reference
		System.out.println("Grouped By Lenght 1: " + byLength1);
		System.out.println("Grouped By Length 2: " + byLength2);
		System.out.println();

		//4. Collect the fruit that has erry in it
		List<String> containErry = fruit.stream().filter(f -> f.contains("erry")).collect(Collectors.toList()); //.filter because only fruits that matter are ones w/ erry
		System.out.println("Contains erry: " + containErry);
		System.out.println();


		//5. Create a partition of fruit based on if it contains erry
		Map<Boolean, List<String>> partitionErry = fruit.stream().collect(Collectors.partitioningBy(f -> f.contains("erry"))); //partioningBy() splits stream into two groups. Either contains (true) or doesnt (false) so key for boolean.
		System.out.println("Partition erry: " + partitionErry);																	
		System.out.println();		


		//6. collect/ the fruit that has 5 or less symbols
		List<String> fiveFewer = fruit.stream().filter(f -> f.length() <= 5).collect(Collectors.toList()); //combining elements of 4 and 5. We wanted list with fruit with 5 or fewer symbols. In this case every symbol is a character
		System.out.println("Five or Fewer: " + fiveFewer);																
		System.out.println();	

		//7. find the total number of symbols in all the fruit stored
		int totalCharOfFruitList = fruit.stream().mapToInt(f -> f.length()).sum(); //mapToInt --> each string to its length and now we can get that sum
		int totalCharOfFruitList2 = fruit.stream().mapToInt(String::length).sum(); // method reference
		System.out.println("Total Char 1: " + totalCharOfFruitList);		// prints with lambda expession
		System.out.println("Total Char 2: " + totalCharOfFruitList2);		//	prints with method reference 																							
		System.out.println();	



		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        //8. Partition data based on if >=50
		Map<Boolean, List<Integer>> partition50 = data.stream().collect(Collectors.partitioningBy(d -> d >= 50)); //similar to 5 in fruit list, but now we are using data list which is integers
		System.out.println("Partition 50: " + partition50);	
		System.out.println();																

		//9. divide data into groups based on the remainder when divided by 7
		Map<Integer, List<Integer>> modulus7 = data.stream().collect(Collectors.groupingBy(d -> d % 7)); //uses d%7 as the key so each is grouped by its remainder
		System.out.println("Modulus 7: " + modulus7);	
		System.out.println();	

		//10. find the sum of the data
		int sum = data.stream().mapToInt(d -> d).sum();     //uses mapToInt so when can then use .sum() and get the sum of all numbers
		int sum2 = data.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum of data: " + sum);	
		System.out.println("Sum of data: " + sum2);	
		System.out.println();			


		//11. collect the unique values
		Set<Integer> dataSet = data.stream().collect(Collectors.toSet()); //set cant have duplicates so all values are unique
		System.out.println("Data Set: " + dataSet);
		System.out.println();

        //12. compute the cube of each values
		List<Integer> cubes = data.stream().map(d -> d*d*d).collect(Collectors.toList()); //.map() is used so each element is now nultiplied by itself 3 times
		System.out.println("Cubes: " + cubes);
		System.out.println();

		//13. find the sum of the cubes of each value
		int cubesSum = data.stream().mapToInt(d -> d*d*d).sum(); //.mapToInt() so we can use .sum()
		System.out.println("Sum of all cubes: " + cubesSum);
		System.out.println();

		//14. increase the value of each element by 5
		List<Integer> addFive = data.stream().map(d -> d+5).collect(Collectors.toList()); //map() is used to add 5 to each element then we get the list of elements + 5
		System.out.println("Elements + 5: " + addFive);
		System.out.println();

		//15. compute the cube of the even values
		List<Integer> cubesEven = data.stream().filter(d -> d%2==0).map(d -> d*d*d).collect(Collectors.toList()); //uses .filter() to get only even numbers and then uses .map() to get cubes and then we put it all in a list 
		System.out.println("Even cubes: " + cubesEven);
		System.out.println();

   }
}
