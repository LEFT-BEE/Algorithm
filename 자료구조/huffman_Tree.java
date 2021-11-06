import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i =0 ; i < T ; i++) {
        	int n = sc.nextInt();
        	HUFFMAN_TREE huffman_tree = new HUFFMAN_TREE();
        	if(n==0){ 
                System.out.println(0);
                    continue;}
        		
        	
        	for(int j =0; j < n; j++) {
        		char data = sc.next().charAt(0);
        		int freq = sc.nextInt();
        		Node newNode = new Node(data , freq);
        		huffman_tree.Node_queue.offer(newNode);
        	}
 
        	huffman_tree.make_tree();
        	huffman_tree.print_weight();
        }
    }
}


class Node{
	public int freq;
	public char data;
	Node left_Node = null;
	Node right_Node = null;
	Node(){}
	Node(char d , int n){
		freq = n;
		data =d;
	}
	
	public boolean is_leaf() {
		if(left_Node == null && right_Node == null)
			return true;
		return false;
	}
}



class compare_Node implements Comparator<Node>{
	public int compare(Node a , Node b) {
		return a.freq - b.freq;
	}
}



class HUFFMAN_TREE {
	
	 public PriorityQueue<Node> Node_queue = new PriorityQueue<Node>(100 , new compare_Node());	
	 public int weight_sum=0;
	 
	 public HUFFMAN_TREE() {} //이후 Node_queue에 값을 집어놓도록 만들어야함
	 
	 public Node huff_insert(Node n1 ,Node n2) {
		 Node rootNode = new Node();
		 rootNode.freq = n1.freq + n2.freq;
		 rootNode.left_Node = n1;
		 rootNode.right_Node = n2;
		 return rootNode; 
	 }
	 public void make_tree() {
		if(Node_queue.size() > 1) {
			while(true) {
				Node first_Node = Node_queue.peek();
				Node_queue.poll();
				Node Second_Node = Node_queue.peek();
				Node_queue.poll();
				Node_queue.add(huff_insert(first_Node , Second_Node));
				if(Node_queue.size() == 1)
					break;
			} 
		}
	 }
	 
	 public void print_weight() {
		 Node root_Node = Node_queue.peek();
		 count_weight_helper(root_Node , 0);
		 System.out.println(weight_sum);
	 }
	 
	 public void count_weight_helper(Node curr , int level) {	 
		 if(curr.is_leaf()) {
			 weight_sum += level * curr.freq;
		 }
		 else{
			 count_weight_helper(curr.left_Node, level+1);
			 count_weight_helper(curr.right_Node, level+1);
		 }
			  
	 }

	 

}