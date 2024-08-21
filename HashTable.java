import java.util.Arrays;

/*
 * This is the HashTable class that
 */
public class HashTable {
    Anagram[] hTable;
    private int collisionCount = 0;

    /*
     * Constructor that creates a HashTable with the given size.
     */
    public HashTable(int capacity) {
        hTable = new Anagram[capacity];
    }

    /*
     * Method that returns the hashcode of the given key.
     */
    public int hashCode(String key) {
        return Math.abs(key.hashCode()) % hTable.length;
    }

    /*
     * Method that returns the key.
     */
    public void add(String elem) {
        String key = stringSort(elem.toLowerCase()); //Converts string to lowercase than finds key.
        int index = hashCode(key);

        if (hTable[index] == null) { // If spot is available
            hTable[index] = new Anagram(key);
            hTable[index].addValue(elem);
        } else if (hTable[index].getKey().equals(key)) { // If key already exists
            hTable[index].addValue(elem);
        } else { // If spot is not available and key does not match.
            int currentIndex = probe(index, key, false);
            if (hTable[currentIndex] == null) { // If new index is open
                hTable[currentIndex] = new Anagram(key);
                hTable[currentIndex].addValue(elem);
            } else { // If key exists at new index
                hTable[currentIndex].addValue(elem);
            }
        }
    }

    /*
     * Method that searches for index of the given string's key.
     */
    public int search(String elem) {
        String key = stringSort(elem.toLowerCase());
        int index = hashCode(key);
        if (hTable[index] == null) {
            return index;
        }
        if (!hTable[index].getKey().equals(key)) { // If key not found at hashcode index.
            return probe(index, key, true);
        }
        return index;
    }

    /*
     * Method that returns the number of collisions.
     */
    public int getCollisionCount() {
        return collisionCount;
    }

    /*
     * Method that returns the anagram at the given index.
     */
    public Anagram getAnagram(int index) {
        return hTable[index];
    }

    /*
     * Method that probes for a new index
     */
    private int probe(int startIndex, String key, boolean search) {
        int currentIndex = startIndex;
        int step = 1;
        do {
            // If current index is empty or the key exists there, break loop
            if (hTable[currentIndex] == null || hTable[currentIndex].getKey().equals(key)) {
                break;
            }
            if (!search) { //Only increment collision count if probing for adding.
                collisionCount++;
            }
            //Open Addressing with Quadratic probing.
            currentIndex = (int) (startIndex + Math.pow(step, 2)) % hTable.length;
            step++;
        } while (currentIndex != startIndex);
        return currentIndex;
    }

    private String stringSort(String theString) {
        char[] charArray = theString.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
