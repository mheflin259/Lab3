import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static String stripPunctuation(final String input) {
        String result = "";
        for (int i = 0; i < input.length() - 1; i++) {
            String letter = input.substring(i, i + 1);
            if (!(letter.equals(".") || letter.equals(",") || letter.equals("!") || letter.equals("?")
                    || letter.equals("'") || letter.equals("/") || letter.equals("(") || letter.equals(")"))) {
                result += letter;
            }
        }
        return result;
    }

    public static int wordCount(final String url) {
        String contents = urlToString(url);
        contents = stripPunctuation(contents);
        String[] splitContents = contents.split(" ");
        return splitContents.length;
    }

    public static int countOneWord(final String url, final String word) {
        String contents = urlToString(url);
        contents = stripPunctuation(contents);
        String[] splitContents = contents.split(" ");
        int wordCount = 0;
        for (int i = 0; i < splitContents.length; i++) {
            if (splitContents[i].toLowerCase().equals(word.toLowerCase())) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static int uniqueWordCount(final String url) {
        String contents = urlToString(url);
        contents = stripPunctuation(contents);
        String[] splitContents = contents.split(" ");
        ArrayList<String> uniqueWords = new ArrayList<String>();
        for (int i = 0; i < splitContents.length; i++) {
            if (uniqueWords.indexOf(splitContents[i]) == -1) {
                uniqueWords.add(splitContents[i]);
            }
        }
        return uniqueWords.size();
    }

    public static void main(String[] args) {
        System.out.println(urlToString("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println("Word count: " + wordCount("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println("Word count of 'prince': " + countOneWord("http://erdani.com/tdpl/hamlet.txt", "prince"));
        System.out.println("Unique word count: " + uniqueWordCount("http://erdani.com/tdpl/hamlet.txt"));
    }
}
