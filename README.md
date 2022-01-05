Implement a program, called WordFinder, that when given letters that could comprise a Scrabble rack, creates a list of all legal words that can be formed from the letters on that rack. The program consists of a scrabble dictionary. Some particulars of the Scrabble dictionary: it only has words of length two or more, and it includes all forms of a word as separate entries, e.g., singular plus plural, verb conjugations.
For example, if your rack had the letters c m a l you could rearrange the letters to form the words calm or clam, but you could also form shorter words from a subset of the letters, e.g., lam or ma. 
The program displays all such words, with the corresponding Scrabble score for each word, in decreasing order by score. Each letter has a score associated with it, the score for a word is the sum of the scores of each letter in that word. For words with the same scrabble score, the words will appear in alphabetical order. Here are the results for a rack consisting of "cmal" (using the sowpods dictionary) in the output format you will be using for your program (user input is shown in italics):
Rack? cmal
We can make 11 words from "cmal"
All of the words with their scores (sorted by score):
8: calm
8: clam
7: cam
7: mac
5: lac
5: lam
5: mal
4: am
4: ma
2: al
2: la
