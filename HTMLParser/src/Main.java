import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String FILE_NAME= "index.html";
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите URL-адрес: ");
        String URL_NAME = sc.nextLine();

        start(URL_NAME, FILE_NAME);
    }

    private static void start(String URL_NAME, String FILE_NAME){
        HTMLParser htmlParser = new HTMLParser();
        try{
            htmlParser.downloadHTMLFile(URL_NAME, FILE_NAME);
            String HTMLText = htmlParser.readHTMLFile(FILE_NAME);
            String text = htmlParser.editHTMLText(HTMLText);
            HashMap<String,Integer> countedWords = htmlParser.countWords(text);
            printMap(countedWords);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void printMap(HashMap<String, Integer> map){
        for (HashMap.Entry<String,Integer> pair: map.entrySet())
            System.out.println((pair.getKey()+ " - " + pair.getValue()));
    }
}


