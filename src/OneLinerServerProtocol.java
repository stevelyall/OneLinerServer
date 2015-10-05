/**
 * Provides phrases obtained from:
 * http://www.oneliners-and-proverbs.com/engels/computer.html
 *
 * @author stevelyall
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OneLinerServerProtocol {


    // location for file
    final String filePath = "oneliners.txt";
    int phrasesSize;
    List<String> phrases = new ArrayList<>();

    /**
     * Instantiates a new OneLinerServerProtocol object
     *
     * Precondition: the text file is present at the provided path
     * Postcondition: a OneLinerServerProtocol object exists
     * Complexity: O(n)
     *
     * @return random phrase as a string
     */
    public OneLinerServerProtocol() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String line;
            while ((line = br.readLine()) != null) {
                phrases.add(line);
            }
            phrasesSize = phrases.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a random phrase
     *
     * Precondition: ServerProtocol object has been instantiated
     * Postcondition: The phrases structure is unchanged
     * Complexity: O(1)
     *
     * @return random phrase as a string
     */
    public String getPhrase() {
        int phraseNum = (int) Math.floor(Math.random() * phrasesSize);
        return phrases.get(phraseNum);
    }

}


