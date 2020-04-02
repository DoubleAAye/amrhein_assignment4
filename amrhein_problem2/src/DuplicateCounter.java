import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class DuplicateCounter {

    private HashMap<String, Integer> wordCount;

  //Method to read data file and count duplicates.
    public void count (String dataFile) {
    	wordCount = new HashMap<>();
    	
        try (Scanner input = new Scanner(Paths.get(dataFile))) {
            while (input.hasNext()) {
                String activeSelection = input.next().toLowerCase();
                if (wordCount.containsKey(activeSelection)) {
                    int count = wordCount.get(activeSelection);
                    wordCount.put(activeSelection, count + 1);
                } else {
                    wordCount.put(activeSelection, 1);
                }
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

    //Method to write unique words and their count into a new file or overwrite existing file.
    public void write (String outputFile) {
    	FileWriter writeOut;
		PrintWriter writeToFile;
		try {
			writeOut = new FileWriter(new File(outputFile));
			writeToFile = new PrintWriter(writeOut);
			for(Map.Entry<String, Integer> word : wordCount.entrySet())
			{
				writeToFile.write(word.getKey() + " appears " + word.getValue() + " time(s)" + "\n");
            }
			//Message to be displayed once application successfully writes new text file.
            System.out.println("All unique word counts are now in " + outputFile + ". Thank you!");
            
            //Clears all excess resources
            writeToFile.flush();
    		writeOut.close();
    		writeToFile.close();
        }
       
		//Prevents error from incorrect or missing file.
        catch (FileNotFoundException e) {
            System.out.println("No file found.");
            
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}