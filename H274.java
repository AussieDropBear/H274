////////////////////////////// PROBLEM STATEMENT ////////////////////////////
// Write a program that reads the file phillip.txt and:                    //
//                                                                         //
// 1.  Read a text file using BufferedReader. (5 marks)                    //
// 2.  Print a sorted list of the words in the file and a count of the     // 
//     number of occurrences of each word (words are only printed once)    //
//     when the count is greater than 500. A word is a section of text     //
//     delimited by at least one blank either end. (5 Marks)               //
// 3.  Print the total number of words in the file. (2 Marks)              //
// 4.  Print a count of the number of unique words in the file. (3 Marks)  //
// 5.  Print a count of the duplicate words (a word followed by the same   //
//     word) in the file. (3 Marks)                                        //
// 6.  If there are any numbers in the file separated by spaces from other //
//     text then add these up and print the total. You must use an         //
//     exception when doing this. (3 Marks)                                //
// 7.  Print a sorted list of the number of occurrences of each letter     //
//     (ignoring case). (3 Marks)                                          // 
/////////////////////////////////////////////////////////////////////////////

import java.io.*;import java.util.*;public class H274
{public static void main (String[] args) throws FileNotFoundException
  {while (JPL.test()){
  // >>>>>> Your Java Code Fragment starts here <<<<<<
  File filename = new File( "phillip.txt");
  try{
    BufferedReader in = new BufferedReader(new FileReader(filename));          
    HashMap<String, Integer> words = sortWords(in);  // word - count 
    // My Temp Files
    int d = (Integer)words.get("Double"); // way to transfer information with one return
    words.remove("Double");
    int n = ((Integer)words.get("Total"));   // way to transfer information with one return
    words.remove("Total");
    int sum = ((Integer)words.get("counter"));   // way to transfer information with one return
    words.remove("counter");
    // End temps
    ArrayList<String> arraylist = new ArrayList<String>(words.keySet());
    int x = arraylist.size()-1;
    HashMap<Character, Integer> letters = getLetters(words);
    Collections.sort(arraylist);
    find500(arraylist, words);   
    System.out.println("Word count = " + n + ",");  //73057 that is correct! // Unique 11128  // Duplicate 72 // sum  1465905 // the 5485
    System.out.println("Unique Words = " + x + ",");
    System.out.println("Duplicate Words = " + d + ",");
    System.out.println("Sum of numbers = " + sum);
    ArrayList<Character> abc = new ArrayList<Character>(letters.keySet());
    Collections.sort(abc);
    printLetters(abc, letters);
    in.close();
  }catch (IOException iox){ System.out.println("Problem reading filename" + filename); }  
  // >>>>>> Your Java Code Fragment ends here <<<<<<     
}}
//////////////////////////////////////////////////////////////////////////////
public static HashMap<Character, Integer> getLetters(HashMap<String, Integer> words) throws IOException{
  HashMap<Character, Integer> letters = new HashMap<Character, Integer>(); 
  
  for (String str : words.keySet()){
    Integer cnt = words.get(str);
    char[] chars = str.toCharArray();
    for (char ch : chars){
      Integer count = letters.get(ch);
      if (count == null) count = 0;
      count = count + cnt;
      letters.put(ch, count);
    }}
  return letters;
}
//////////////////////////////////////////////////////////////////////////////  
public static HashMap<String, Integer> sortWords(BufferedReader in) throws IOException {
  String line = "x";
  int d = 0;
  HashMap<String, Integer> wordsCount = new HashMap<String, Integer>();
  wordsCount.put("Double", 0);
  wordsCount.put("counter", 0);
  String prevWord = "starter";
  while ((line = in.readLine()) != null) {
    for(String word : line.toLowerCase().split(" ")) {
      if (prevWord.equals(word)) {
        Integer ctr = wordsCount.get("Double"); // for loop to find the duplicates
        ctr++; 
        wordsCount.put("Double", ctr);
      } try {
        if (isInt(word)) {
          Integer cntr = wordsCount.get("counter");       // trying to get the count of numbers
          cntr = cntr + Integer.parseInt(word);
          wordsCount.put("counter", cntr);                // Got Numbers
        } }catch (NumberFormatException ex) { } 
        Integer count = wordsCount.get(word);             // Get current count for this word
        char[] chars = word.toCharArray();
      for (char c : chars) {
        if (count == null) count = 0;                 // Initialize on first appearance
        count++;                                      // Update counter
        d++;                                          // Test purposes
        wordsCount.put(word, count);                  // Save the updated value
        prevWord = word;
        break; }}}
  wordsCount.put("Total", d);                           //Test purposes  
  return wordsCount;
}  
//////////////////////////////////////////////////////////////////////////////     
static boolean isInt(String str){
  try{ 
    for (char c : str.toCharArray()){
      if (!Character.isDigit(c)) return false;
    }return true;
  } catch (ClassCastException ex) { return false; }
  catch (NumberFormatException x){ return false;} }
//////////////////////////////////////////////////////////////////////////////    
static void find500(ArrayList words, HashMap<String, Integer> map){
  for (int i = 0; i < words.size(); i++) {
    String key = (String)words.get(i);
    Integer count = (Integer)map.get(key);
    if (count > 500) {
      System.out.println(key + " = " + count);
    }}}
////////////////////////////////////////////////////////////////////////////// 
static int countUnique(HashMap<String, Integer> words){
  int count = 0;
  for (String str : words.keySet()){
    Integer cnt = words.get(str);
    if (cnt == 1) {
      count++;
    }}
  return count; 
}
//////////////////////////////////////////////////////////////////////////////    
static void printLetters(ArrayList abc, HashMap<Character, Integer> map){
  for (int i = 0; i < abc.size(); i++) {
    char key = (char)abc.get(i);
    Integer count = (Integer)map.get(key);
    key = Character.toLowerCase(key);
    if (Character.isLetter(key) && (key - 'a') < 27)
      System.out.println("Letter " + key + " = " + count);
  }}
//////////////////////////////////////////////////////////////////////////////
}



