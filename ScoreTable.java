// Name: Kant Tantasathien

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class ScoreTable {
   /*/
      An array that keep track of the scores of each alphabets.
   /*/
   private static final String[] scores = {
      "" , "aeioulnstr",
      "dg","bcmp","fhvwy",
      "k","","","jx","","qz"
   };
   
   /*/
      Representaion Invariant function. 
      1. 26 Unique Characters in the score table.
   /*/
   private static boolean isValid(){
      Set<Character>unique = new TreeSet<>();
      List<Character>count = new ArrayList<>();
      for(int i =0; i< scores.length; i++){
         for(char c: scores[i].toCharArray()){
            unique.add(c);
            count.add(c);
         }      
      }
      return unique.size() == 26 && count.size() == 26;   
   }
   /*/
      Go through every character in the input and score it according to the score array.
      @param input is the dictionary word found by wordFinder
   /*/
   public static int getScore(String input){
      assert isValid();
      int score = 0;
      for(char c: input.toLowerCase().toCharArray()){            //goes through every string which contains the characters and add in their
         for(int i = 1; i<scores.length;i++){      //scores
            if(scores[i].contains(String.valueOf(c))){
               score+=i;
               break;
            }
         }
      }
      return score;
   }
}
