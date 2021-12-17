// Name: Kant Tantasathien


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/*/
   Sorting class so the output would be in order of their scores
   and in alphabetical order
*/
class sortByScore implements Comparator<String>{
   public int compare(String a, String b){
      int value = ScoreTable.getScore(b) - ScoreTable.getScore(a);
      if(value == 0){
         value = a.compareTo(b);           
      }
      return value;
   }
}
/*/
   Main function that run the program
*/
class WordFinder {
   public static void main(String[] args){
      try{
         String fileName = "sowpods.txt";
         if(args.length > 0){
            fileName = args[0];
         }
         Scanner input = new Scanner(System.in);
         
         AnagramDictionary dictionary = new AnagramDictionary(fileName);
         System.out.println("Type . to quit.");
         while(perform(input,dictionary)); //keep calling this method until it returns false
      }
      catch(Exception e){
         System.out.println(e.getMessage());
         System.out.println("Exiting program.");
      }
   }
   /*/
      Perform rack worth of function.
      Retrieve a single word from the input.
      Put the word into the rack class.
      Get all subsets from the rack.
      Query the dictionary.
      Output the results.
      @param input scanner
      @param dictionary
      @return true if we should continue and ask for more input
   */
   private static boolean perform(Scanner input, AnagramDictionary dictionary){
      System.out.print("Rack? ");
      String userWord = input.nextLine();
      if(userWord.equals(".")){
         return false;
      }
      Rack rack = new Rack(userWord);
      ArrayList<String> words = new ArrayList<>();
      for(String word: rack.getAllString()){
         words.addAll(dictionary.getAnagramsOf(word));
      }
      words.sort(new sortByScore());
      System.out.printf("We can make %d words from \"%s\"\n", words.size(), userWord);
      if(words.size() > 0){
         System.out.println("All of the words with their scores (sorted by score):");
         for(String word: words){
            System.out.println(ScoreTable.getScore(word) + ": " + word);
         }
      }
      return true;
   }
}
