import java.util.*;
/*
 * This is the Anagram class that stores a key and a list of
 * all possible anagrams of the key.
 */
public class Anagram {

    String key;
    List<String> values;

    /*
     * Constructor that creates an anagram with the given key.
     */
    public Anagram(String theKey) {
        key = theKey;
        values = new ArrayList<>();
    }

    /*
     * Method that returns the key.
     */
    public String getKey() {
        return key;
    }

    /*
     * Method that returns the list of values
     */
    public List<String> getValues() {
        return values;
    }

    /*
     * Method that the given string to the list of values.
     */
    public void addValue(String value) {
        values.add(value);
    }
}
