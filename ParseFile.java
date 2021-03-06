import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class ParseFile {
    private String Name;
    private int SIZE;
    private HashMap<Integer, Integer> Integers = new HashMap<>();
    private ArrayList<Pair<Integer, Integer>> IntegerTop;
    private HashMap<String, Integer> Words = new HashMap<>();
    private ArrayList<Pair<String, Integer>> WordsTop;
    private HashMap<Character, Integer> Characters = new HashMap<>();
    private ArrayList<Pair<Character, Integer>> CharactersTop;

    // Constructors
    ParseFile(){
        Name = "";
        SIZE = 10;

        IntegerTop = new ArrayList<>(SIZE);        // default top size to 10
        WordsTop = new ArrayList<>(SIZE);
        CharactersTop = new ArrayList<>(SIZE);
    }
    ParseFile(String name){
        Name = name;
        SIZE = 10;

        IntegerTop = new ArrayList<>(SIZE);        // default top size to 10
        WordsTop = new ArrayList<>(SIZE);
        CharactersTop = new ArrayList<>(SIZE);
    }

    ParseFile(String name, int size){
        Name = name;
        if (size > 0)
            SIZE = size;

        IntegerTop = new ArrayList<>(SIZE);
        WordsTop = new ArrayList<>(SIZE);
        CharactersTop = new ArrayList<>(SIZE);
    }

    // created the Maps of the given values
    void parse(){
        Scanner s;
        try {
            s = new Scanner(new File(Name));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;     // if scanner cannot be created return
        }

        while(s.hasNext()) {        // get all data in the file
            String token = s.next();    // one string at a time
            token = token.toLowerCase();

            if (isInteger(token)) {     // check if token is an Int
                Integer i = Integer.parseInt(token);
                add(Integers, i, IntegerTop);
            }
            else {  // else token is treated as w word
                add(Words, token, WordsTop);
            }

            for(int i = 0; i < token.length(); i++) {   // always check each character
                add(Characters, token.charAt(i), CharactersTop);
            }

        }
    }   // end parse

    // returns true if string is an int and false otherwise
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e ){
            return false;
        }

    }

    // generic function for adding elements to a map
    public <K> void add(HashMap<K, Integer> m, K token, ArrayList<Pair<K, Integer>> top){
        if(m.containsKey(token)){       // if token already in map
            int n = m.get(token);
            n++;
            m.replace(token, n);
        }
        else {                          // else add it
            m.put(token, 1);
        }

        remove(top, token);     // only removes pairs in list that match token

        if((top.size() < SIZE || m.get(token) >= top.get(top.size() - 1).getValue())) {
            Pair<K, Integer> p = new Pair<>(token, m.get(token));
            top.add(p);
            top.sort(new CountCompare());       // sorts the list

            // should only run once but use a while loop to be sure
            while(top.size() > SIZE) {      // keep list size SIZE
                top.remove(top.size() -1);
            }
        }
    }   // end add

    // Removes the pair that is of the same type
    private <K> void remove(ArrayList<Pair<K, Integer>> list, K t) {
        for(int i = 0; i < list.size(); i++){
            if(t.equals(list.get(i).getKey())){
                list.remove(i);
            }
        }
    }

    public ArrayList<String> compareWords(Map<String, Integer> rhs){
        ArrayList<String> ret = new ArrayList<>();

        for(String name : Words.keySet()) {
            if(rhs.containsKey(name)) {
                ret.add(name);
            }
        }

        return ret;
    }

    public ArrayList<Character> compareCharacters(Map<Character, Integer> rhs){
        ArrayList<Character> ret = new ArrayList<>();

        for(Character name : Characters.keySet()) {
            if(rhs.containsKey(name)){
                ret.add(name);
            }
        }

        return ret;
    }

    public ArrayList<Integer> compareIntegers(Map<Integer, Integer> rhs){
        ArrayList<Integer> ret = new ArrayList<>();

        for(Integer name : Integers.keySet()) {
            if(rhs.containsKey(name)){
                ret.add(name);
            }
        }

        return ret;
    }

    // print functions
    // only prints the top ArrayLists
    public void printTop(){
        if(!CharactersTop.isEmpty()) {
            System.out.printf("The total unique characters is %d \n\t\tand the top characters are\n", Characters.size());
            for (Pair<Character, Integer> characterIntegerPair : CharactersTop) {
                System.out.printf("%-10c %d\n", characterIntegerPair.getKey(), characterIntegerPair.getValue());
            }
        }
        if (!WordsTop.isEmpty()) {
            System.out.printf("\nThe total unique words are %d\n\t\tand the top words are\n", Words.size());
            for (Pair<String, Integer> StringIntegerPair : WordsTop) {
                System.out.printf("%-10s %d\n", StringIntegerPair.getKey(), StringIntegerPair.getValue());
            }
        }

        if(!IntegerTop.isEmpty()) {
            System.out.printf("\nThe total unique integers are %d\n\t\t and the top integers are\n", Integers.size());
            for (Pair<Integer, Integer> IntegerIntegerPair : IntegerTop) {
                System.out.printf("%-10d %d\n", IntegerIntegerPair.getKey(), IntegerIntegerPair.getValue());
            }
        }
    }

    // prints all characters in the maps
    void printMaps() {
        System.out.printf("Number of characters in %s: %d\n", Name, Characters.size());

        for(Character name : Characters.keySet()) {
            System.out.printf("%s: %d\n", name, Characters.get(name));
        }

        System.out.printf("Number of Words in %s: %d\n", Name, Words.size());
        for(String name : Words.keySet()) {
            System.out.printf("%s: %d\n", name, Words.get(name));
        }

        System.out.printf("Number of Integers in %s: %d\n", Name, Integers.size());
        for(Integer name : Integers.keySet()) {
            System.out.printf("%s: %d\n", name, Integers.get(name));
        }
    }

    // implement Comparator to compare Strings by length
    private static class CountCompare implements Comparator {
        public int compare(Object o1, Object o2)
        {
            return  ((Pair< ?, Integer>)o2).getValue() - ((Pair< ?, Integer>)o1).getValue();
        }
    }

    // Get functions
    public String getName(){
        return Name;
    }

    public int getTopSize(){
        return SIZE;
    }

    public Map<String, Integer> getWords(){
        return Words;
    }

    public Map<Character, Integer> getCharacters() {
        return Characters;
    }

    public Map<Integer, Integer> getIntegers() {
        return Integers;
    }

    }   // end of Parse File
