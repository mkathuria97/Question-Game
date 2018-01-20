//Name: Mayank Kathuria
//Student Number: 1534791
//Section: BH
//The QuestionTree class allows the computer to guess the object thought by the user by
//asking a series of yes/no questions. Eventually, the computer makes a guess. If the guess
//is correct, the computer wins; if not, the user win.
import java.util.*;
import java.io.*;
public class QuestionTree{
   private QuestionNode overallRoot;
   private UserInterface userInterface;
   private int win;
   private int total;
   
   //initially the computer contains only a single answer with the word "computer" in it.
   public QuestionTree(UserInterface ui){
      overallRoot = new QuestionNode("computer");
      userInterface = ui;
      win = 0;
      total = 0;
   }
   
   //plays one complete guessing game with the user by asking a series of yes/no questions
   //until reaching an answer object to guess.
   //If the guess is correct, a win message is printed
   //otherwise a lose message is printed and the user is asked for the object he/she was thinking
   //of, a question to distinguish that object from the player's guess, and whether the player's
   //object is yes or no answer for that question.
   //The total number of games is incremented by 1 each time.
   public void play(){
      overallRoot = play(overallRoot);
      total++;
   }
   
   //the computer asks a series of yes/no questions to the user till the leaf node is not
   //reached
   //the computer makes a guess on reaching a leaf node
   //If the guess is correct, a win message is printed and number of games won by the
   //computer is incremented by 1
   //otherwise a lose message is printed and the user is asked for the object he/she was thinking
   //of, a question to distinguish that object from the player's guess, and whether the player's
   //object is the yes or no answer for that question.
   //the distinguishing question is added as a node the tree and the computer's guess and the
   //object becomes the children of that question node based on whether the player's
   //object is yes or no answer for that question.
   private QuestionNode play(QuestionNode root){
      if(root.left == null && root.right == null){
         userInterface.print("Would your object happen to be " + root.data + "?");
         if(userInterface.nextBoolean()){
            userInterface.println("I win!");
            win++;
         }else{
            userInterface.print("I lose. What is your object?");
            String object = userInterface.nextLine();
            userInterface.print("Type a yes/no question to distinguish your item from " +
               root.data + ":");
            String question = userInterface.nextLine();
            userInterface.print("And what is the answer for your object?");
            if(userInterface.nextBoolean()){
               root = new QuestionNode(question, new QuestionNode(object), root);
            }else{
               root = new QuestionNode(question, root, new QuestionNode(object));
            }
         }
      }else{
         userInterface.print(root.data);
         if(userInterface.nextBoolean()){
            root.left = play(root.left);
         }else{
            root.right = play(root.right);
         }
      }
      return root;
   }
   
   //stores a series of questions and answers to an output file represented by the PrintStream
   //Question begin with Q: followed by the question
   //Answer begin with A: followed by the answer
   //Each question and answer are represented in a seperate line
   public void save(PrintStream output){
      save(output, overallRoot);
   }
    
   //stores a series of question and answers to an output file represented by the PrintStream
   //Each line starts with either Q: to indicate a question node or A: to indicate an answer node
   //i.e leaf node. These charcters are followed by the text of that node(question or answer)
   private void save(PrintStream output, QuestionNode root){
      if(root.left == null && root.right == null){
         output.println("A:" + root.data);
      }else{
         output.println("Q:" + root.data);
         save(output, root.left);
         save(output, root.right);
      }
   }
   
   //pre: throws an IllegalArgumentException if there are no lines in the file
   //post: reads a file so that the computer can have a new series of questions and answers
   public void load(Scanner input){
      overallRoot = load(input, new QuestionNode(""));
   }
   
   //Scanner to read from a file and replace the current tree nodes with a new tree
   //using the information in the file.
   private QuestionNode load(Scanner input, QuestionNode root){
      if(input.hasNextLine()){
         String line = input.nextLine();
         root = new QuestionNode(line.substring(2));
         if(!line.substring(0,2).equals("A:")){
            root.left = load(input, root.left);
            root.right = load(input, root.right);
         }
      }
      return root;
   }
   
   //returns the total number of games played
   public int totalGames(){
      return total;
   }
   
   //returns the number of games the computer has won by correctly guessing the object
   public int gamesWon(){
      return win;
   }
}
