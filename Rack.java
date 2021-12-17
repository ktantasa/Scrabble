// Name: Kant Tantasathien


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A Rack of Scrabble tiles
 */

public class Rack {
   private String unique;
   private List<Integer> mult = new ArrayList<>();
   
   /*/
      Invariant Representation
      1. Unique contains at least 1 character
      2. All characters in unique must be unique
      3. Size of mult equal to size of the Unique String
      4. Mult contains value greater than or equal to 1
   /*/
   private boolean isValid(){
      if(unique.length() == 0 || unique.length() != mult.size()){
         return false;
      }
      for(Integer i: mult){
         if(i<1){
            return false;
         }
      }
      Set<Character>chars = new TreeSet<>();
      for(char c: unique.toCharArray()){
         chars.add(c);
      }

      return chars.size() == unique.length();
   }
   
   /**
      Rack constructor.
      @param input is the word the user is searching for
   */
   public Rack(String input) {
      unique = "";
      for (char c : sortString(input).toCharArray()) {
         int index = unique.indexOf(String.valueOf(c));
         if (index < 0) {
            unique += String.valueOf(c);
            mult.add(1);
         } else {
            mult.set(index, mult.get(index) + 1);
         }
      }
      assert isValid();
   }

   /**
      Go through all of the characters in the list two at a time
      Compare those two values and retrun the comparison
      If it's less than 0, a goes before b, if it's 0, a&b are the same
      If it's greater than 0 then b goes before a
      @param input is a singular word in the dictionary
      @return the characters in ascending order
   */
   private String sortString(String input) {
      List<Character> characters = new ArrayList<>();
      for (char c : input.toCharArray()) {
         characters.add(c);
      }
      characters.sort((a, b) -> a - b);

      String output = "";
      for (Character c : characters) {
         output = output + String.valueOf(c);
      }
      return output;
   }

   /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of
    * the char unique.charAt(i). PRE: mult.length must be at least as big as
    * unique.length() 0 <= k <= unique.length()
    * 
    * @param unique a string of unique letters
    * @param mult   the multiplicity of each letter from unique.
    * @param k      the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset. Unlike the multiset in the
    *         parameters, each subset is represented as a String that can have
    *         repeated characters in it.
    * @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) { // multiset is empty
         allCombos.add("");
         return allCombos;
      }

      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

      // prepend all possible numbers of the first char (i.e., the one at position k)
      // to the front of each string in restCombos. Suppose that char is 'a'...

      String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {
         for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
                                                       // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));
         }
         firstPart += unique.charAt(k); // append another instance of 'a' to the first part
      }

      return allCombos;
   }
   /**
      Getter fuction for unique
   */
   public String getUnique() {
      return unique;
   }
   /**
      Getter fuction for mult
      @return the mult list as an array so that it can be used it in the all subset function
   */
   public int[] getMult(){
      int[] output = new int[mult.size()];
      for (int i = 0; i < mult.size(); i++) {
         output[i] = mult.get(i);
      }
      return output;
   }
   /**
      Run the allSubset function with information from our rackk
      @return the all of the subset the rack can produce.
   */
   public ArrayList<String> getAllString() {
      ArrayList<String> output = allSubsets(unique, getMult(), 0);
      return output;
   }

}
