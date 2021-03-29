import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

	char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	float[] prob = new float[26];
	final int ALPHABET_LENGTH = 26;
	HashMap testHash;
	HashMap readHash;
	HashMap targetHash;
	ArrayList<String> wordList;
	int[] letterIndices = new int[ALPHABET_LENGTH + 1];
	int lambdaCounter = 0;
	int lambdaIndex = 1;
	char lambdaChar = 'a';

	//Populate array list
	public void readWords() {
        //Open the text file as an input stream
      //  InputStream ins = 

     //   InputStreamReader isr = new InputStreamReader(ins, StandardCharsets.UTF_8);
      //  BufferedReader br = new BufferedReader(isr);
		
     String fileName = "C:\\Users\\mason\\eclipse-workspace\\EECS4443_Project_resource_Generator\\src\\words.txt";
     File file = new File(fileName);
     wordList = new ArrayList<String>();
     System.out.println("got here");
       // br.lines().forEach(line -> wordList.add(line));
		 try
		  {
		    BufferedReader reader = new BufferedReader(new FileReader(file));
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		      wordList.add(line);
		    }
		    reader.close();
		  }
		 catch (Exception e){
		      e.printStackTrace();

		 }
	
}
	
	//Compute the alphabetic indexes of the array list
	public void precomputeAlphabeticIndices() {

		letterIndices[0] = 0;

		wordList.forEach(word -> {
		char first = word.charAt(0);
		first = Character.toLowerCase(first);

		if(first != lambdaChar && Character.isAlphabetic(lambdaChar)) {
		    letterIndices[lambdaIndex] = lambdaCounter + 1;
		    lambdaChar = first;
		    lambdaIndex++;
		}
		lambdaCounter++;
		});

		//For grabbing all the z's
		letterIndices[26] = wordList.size() - 1;
		}
	
	private int getLetterIndex(char t) {
		for(int i = 0; i < ALPHABET_LENGTH; i ++) {
		if(alphabet[i] == t) {
		    return i;
		}
		}
		return 0;
		}

	//Give a string, return an array of possible next character counts
	private int[] getLetterProbabilities(String s) {

        int[] results = new int[27];
        //Index of the new letter to probe
        int targetLetterIndex = s.length();
        Pattern pattern = Pattern.compile("^" + s, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
    if (!s.isEmpty()) {
    for(int i = letterIndices[getLetterIndex(s.charAt(0))]; i < letterIndices[getLetterIndex(s.charAt(0)) + 1]; i++) {
        String targetWord = wordList.get(i);
        matcher = pattern.matcher(targetWord);
        if (matcher.find()) {
            if (targetWord.length() > s.length()) {
                //Partial match, good
                char targetLetter = targetWord.charAt(targetLetterIndex);
                results[targetLetter - 'a']++;

            } else {
                results[26]++;
            }
        }
    }
        }
    return results;
	}
	
	//Given an array of counts, convert to an array of probabilities represented as floating point numbers
	public float[] getKeyProbabilityPercents(int[] freq) {
		float[] results = new float[26];
		float total = 0;
		for(int i = 0; i < freq.length; i++) {
		    total += freq[i];
		}

		for(int q = 0; q < results.length; q++) {
		    results[q] = freq[q] / total;
		}


		return results;
		}

	public void precomputeProbabilities() {
		targetHash = new HashMap();
		
        wordList.forEach(word -> {
            System.out.println(word);
            for (int i = 0; i < word.length(); i++) {

                    String substring = word.substring(0, i);


                    if (!targetHash.containsKey(substring))
                    	targetHash.put(substring, getKeyProbabilityPercents(getLetterProbabilities(substring)));

                }

        });

    }
	
	 public void serializeHashMap() throws IOException {
		
	        ObjectOutputStream oos = null;
	        FileOutputStream fout = null;
	        try{

	            String fileName = "C:\\Users\\mason\\eclipse-workspace\\EECS4443_Project_resource_Generator\\src\\hash.ser";
	            fout = new FileOutputStream(fileName, true);
	            oos = new ObjectOutputStream(fout);
	            oos.writeObject(targetHash);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if(oos != null){
	                oos.close();
	            }
	        }
	    }
	 
	 @SuppressWarnings("unchecked")
	public void deserializeHashMap() throws IOException, ClassNotFoundException {
		 
		 try
	        {   
	            String fileName = "C:\\Users\\mason\\eclipse-workspace\\EECS4443_Project_resource_Generator\\src\\hash.ser";

	            // Reading the object from a file
	            FileInputStream file = new FileInputStream(fileName);
	            ObjectInputStream in = new ObjectInputStream(file);
	              
	            // Method for deserialization of object
	            readHash = (HashMap<String, String>) in.readObject();
	              
	            in.close();
	            file.close();
	              
	            System.out.println("Object has been deserialized ");
	            System.out.println(readHash.get("Wew"));
	        
	        }
	          
	        catch(IOException ex)
	        {
	            System.out.println("IOException is caught");
	        }
		  catch(ClassNotFoundException ex)
	        {
	            System.out.println("ClassNotFoundException is caught");
	        }
	          
	 }
}

	
	