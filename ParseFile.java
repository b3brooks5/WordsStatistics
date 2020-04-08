import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ParseFile {
    private String Name;
    private HashMap<Integer, Integer> Integers = new HashMap<Integer, Integer>();
    private HashMap<String, Integer> Words = new HashMap<String, Integer>();
    private HashMap<Character, Integer> Characters = new HashMap<Character, Integer>();

    ParseFile(String name){
        Name = name;
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
                add(Integers, i);
            }
            else {  // else token is treated as w word
                add(Words, token);
            }

            for(int i = 0; i < token.length(); i++) {   // always check each character
                add(Characters, token.charAt(i));
            }

        }
    }

    // returns true if string is an in and false otherwise
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e ){
            return false;
        }

    }

    // generic function for adding elements to a map
    public static <K> void add(HashMap<K, Integer> m, K token){
        if(m.containsKey(token)){
            int n = m.get(token);
            n++;
            m.replace(token, n);
        }
        else {
            m.put(token, 1);
        }
    }

    // prints all characters in the maps
    void printCharacters() {
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


}
