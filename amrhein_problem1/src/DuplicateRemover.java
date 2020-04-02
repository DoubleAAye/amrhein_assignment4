import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Formatter;

public class DuplicateRemover {

	//Method to read data file and remove duplicates.
    private Set<String> uniqueWords;

    public void remove (String dataFile) {
    	uniqueWords = new HashSet<>();
    	
        try (Scanner input = new Scanner(Paths.get(dataFile))) {
            while (input.hasNext()) {
                uniqueWords.add(input.next().toLowerCase());
            }
            input.close();
        }      
        //Prevents error from incorrect or missing file.
        catch (FileNotFoundException e) {
            System.out.println(("No file found."));
        }
        catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    
    //Method to write unique words into a new file or overwrite existing file.
    public void write (String outputFile) {
        try (Formatter output = new Formatter(outputFile)) {
            for (String word : uniqueWords) {
                output.format("%s ", word);
            }
            //Message to be displayed once application successfully writes new text file.
            System.out.println("All unique words are now in " + outputFile + ". Thank you!");
            
            output.flush();
            output.close();
        }
      //Prevents error from incorrect or missing file.
        catch (FileNotFoundException e) {
            System.out.println("No file found...");
        }
    }
}