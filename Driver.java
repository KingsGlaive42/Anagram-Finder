import java.io.File;
import java.util.List;
import java.util.Scanner;

/*
 * This is the driver class that reads a dictionary into the hashtable.
 * It displays the number of collisions, and asks the user for a word.
 * The program than returns the number of anagrams and each anagram.
 */
public class Driver {

    static HashTable hTable = new HashTable(47137);

    public static void main(String[] args) {
        Scanner scan = null;
        File text;
        try {
            text = new File("words.txt"); // Reads text file
            scan = new Scanner(text);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(-1); // Ends program if error occurs.
        }
        while (scan.hasNext()) { // Adds every word in text file to HashTable.
            hTable.add(scan.next());
        }
        System.out.println("Collision Count: " + hTable.getCollisionCount()); // Prints collision count.
        scan = new Scanner(System.in);
        boolean end = false;

        while (!end) { // Loops program until user exits.
            int askCount = 0;
            while (askCount < 3) { // runs 3 times before asking to continue.
                askCount++;
                System.out.print("Input a word to search for: ");
                String word = scan.next();
                int index = hTable.search(word);
                String result = "Anagrams: ";
                int anagramCount = 0;
                List<String> values = null;
                if (hTable.getAnagram(index) == null) { //If key doesn't exist, clears result string.
                    result = "";
                } else { // key is found
                    values = hTable.getAnagram(index).getValues(); // Stores anagram values.
                    // If key exists, but no anagrams exist, clears result string
                    if (values.size() == 1 && values.contains(word)) {
                        result = "";
                    } else {
                        anagramCount = values.size(); // Number of anagrams.
                        for (String s : values) {
                            if (!s.equals(word)) {
                                result += s + " ";
                            } else { // If anagram equals user input, do not print and reduce anagram count.
                                anagramCount--;
                            }
                        }
                    }
                }
                System.out.println("Count: " + anagramCount);
                System.out.print(result + "\n");
            }
            System.out.print("Do you want to search for more words [Y/N]: ");
            String ans = scan.next().toUpperCase();
            while (!ans.equals("N") && !ans.equals("Y")) { // Loop if user input is invalid.
                System.out.print("Please input Y or N: ");
                ans = scan.next();
            }
            if (ans.equals("N")) { // If 'N', then end program.
                end = true;
            }
        }
    }
}
