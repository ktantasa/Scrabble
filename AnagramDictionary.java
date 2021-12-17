// Name: Kant Tantasathien


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.TreeMap;



/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   
   private Map<String,Set<String>> dictionary = new TreeMap<>();
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      File f = new File(fileName);
      if(!f.isFile()){
         throw new IllegalDictionaryException("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
      }
      Scanner fileReader = new Scanner(f);
      while(fileReader.hasNextLine()){
         String value = fileReader.nextLine();
         if(value.length() == 0){
            continue;
         }
         String key = makeKey(value);
         if(!dictionary.containsKey(key)){
            dictionary.put(key,new TreeSet<>());
         }
         if(!dictionary.get(key).add(value)){
            fileReader.close();
            throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + value);
         }
         //System.out.println("adding " + key + " -> " + value);
      }
      fileReader.close();                                                   
   }
   
   /**
      Go through all of the characters in the list two at a time
      Compare those two values and retrun the comparison
      If it's less than 0, a goes before b, if it's 0, a&b are the same
      If it's greater than 0 then b goes before a
      @param input is a singular word in the dictionary
      @return the characters in ascending order
   */
   private String makeKey(String input){
      List<Character>characters = new ArrayList<>();
      for(char c: input.toCharArray()){
         characters.add(c);
      }
      characters.sort((a,b)->a-b);   //Delegate sort
      
      String output = "";
      for(Character c: characters){
         output = output + String.valueOf(c);
      }
      return output;
   }

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public Set<String> getAnagramsOf(String string) {
      Set<String>output = new TreeSet<>();
      String key = makeKey(string);
      if(dictionary.containsKey(key)){
         output.addAll(dictionary.get(key));
      }
      return output;
   }
}