
public class Main {
    public static void main(String[] args) {

        ParseFile file = new ParseFile(args[0]);
        System.out.println(args[0]);

        file.parse();

        file.printCharacters();


    }
}
