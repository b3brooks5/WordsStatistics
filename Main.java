import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<ParseFile> Files = new ArrayList<>();

        for(String s : args) {
            System.out.println(s);
            ParseFile file = new ParseFile(s);
            Files.add(file);
            file.parse();
        }

        Scanner s = new Scanner(System.in);
        menu();
        String text = "";
        int number, number2;

        while (!text.equals("exit")) {
            System.out.print("Please enter a command: ");
            text = s.nextLine();
            String[] command = text.split(" ");

            if(text.equals("top")) {
                for (ParseFile file : Files) {
                    System.out.println(file.getName());
                    file.printTop();
                    System.out.print("\n\n");
                }
            }
            else if (text.equals("names")) {
                for(int i = 0; i < Files.size(); i++){
                    System.out.print(i + ": " + Files.get(i).getName() + "\n");
                }
                System.out.println("\n");
            }
            else if (text.equals("words")){
                System.out.println("Please pick two files from the list to compare");

                for(int i = 0; i < Files.size(); i++) {
                    System.out.print(i + " " + Files.get(i).getName() + "\n");
                }
                System.out.print("First file: ");
                number = s.nextInt();
                System.out.print("Second file: ");
                number2 = s.nextInt();

                System.out.println("All the words that appear in both files");

                ArrayList<String> words = Files.get(number).compareWords(Files.get(number2).getWords());
                System.out.printf("Their are %d words that appear in both files", words.size());

                for(String w : words)
                    System.out.println(w);
            }
            else if (text.equals("t")) {
                System.out.println("Enter the name of the file that you\nwant to view statistics on\nOptions");
                for(int i = 0; i < Files.size(); i++) {
                    System.out.print(i + " " + Files.get(i).getName() + "\n");
                }

                number = s.nextInt();

                Files.get(number).printTop();
            }
            else if(text.equals("m")){
                menu();
            }
        }
    }

    public static void menu(){
        System.out.println("Here are your options for stats on the file(s)\n");
        System.out.println("\tTo Print the name of the files found - names");
        System.out.println("\tTo compare the words of two files - words");
        //System.out.println("\tTo compare the numbers of the two files");
        //System.out.println("\tTo compare the characters of the two files");
        System.out.println("\tTo print the top of each file - top");
        System.out.println("\tTo print to top of one file - t");
        System.out.println("\tTo reprint the menu - m");
        System.out.println("\n'exit' to exit");
    }
}
