//Name: Mayank Kathuria
//Student number: 1534791
//Section: BH
//QuestionNode objects stores a single node of a binary tree of String.
public class QuestionNode{
   public String data; //data stored at this node
   public QuestionNode left;//reference to left subtree
   public QuestionNode right;//reference to right subtree
   
   //Constructs a leaf node with the given data.
   public QuestionNode(String data){
      this(data, null, null);
   }
   
   //Constructs a leaf or branch node with the given data and links.
   public QuestionNode(String data, QuestionNode left, QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right;
   }
}