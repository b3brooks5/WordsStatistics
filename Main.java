import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner Passwords, Dictionary;
        Scanner Files[] = new array[args.length];
        boolean gotFiles = false;

        if (args.length == 2) {
            try {
                Passwords = new Scanner(new File(args[0]));
                Dictionary = new Scanner(new File(args[1]));
                gotFiles = true;
            }catch (FileNotFoundException e) {
                System.out.println("One or more files no found");
            }
        }

        if(gotFiles){
            Map<Character, Integer> PasswordCount;

        }
    }
}
