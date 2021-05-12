package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Solution {
	public static void main(String[] args) throws IOException {
		
		Dictionary<Integer, String> dict = new BST<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			String operation = st.nextToken().strip();
			
			int k;
			String v;
			switch(operation) {
				case "insert":
					k = Integer.parseInt(st.nextToken());
					v = st.nextToken().strip();
					dict.insert(k, v);
					break;
				case "remove":
					k = Integer.parseInt(st.nextToken());
					v = dict.remove(k);
			
					if(v != null)
						System.out.println("Removed value: " + v);
					break;
				case "find":
					k = Integer.parseInt(st.nextToken());
					v = dict.find(k);
					if(v != null)
						System.out.println("Found value: " + v);
					break;
				case "size":
					System.out.println("Dict size: " + dict.size());
					break;
				case "clear":
					System.out.println(inorder(((BST<Integer, String>) dict).root));
					System.out.println(preorder(((BST<Integer, String>) dict).root));
					dict.clear();
					System.out.println("Dict is initialized.");
					break;
			}	
		}
		
		br.close();
	
	}
	
	public static <K, V> String inorder(BinNode<K, V> rt){
		String ret = "";
		
		if(rt == null) return ret;
		
		ret += inorder(rt.left);
		ret += rt.key + ": " + rt.value + "\n";
		ret += inorder(rt.right);
		
		return ret;
	}
	
	public static <K, V> String preorder(BinNode<K, V> rt){
		String ret = "";
		
		if(rt == null) return ret;
		
		ret += rt.key + ": " + rt.value + "\n";
		ret += preorder(rt.left);
		ret += preorder(rt.right);
		
		return ret;
	}
	
}

interface Dictionary<K, V>{
	public void clear(); //담고있는 모든 레코드를 지운다
	public void insert(K k, V v);
	public V remove(K k);
	public V find(K k);
	public int size(); 
}

class BinNode<K, V> {
	public K key;
	public V value;
	public BinNode<K, V> left;
	public BinNode<K, V> right;
	public BinNode(K key, V value, BinNode<K, V> left, BinNode<K, V> right){
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public boolean is_leaf() {
		if(this.left == null & this.right ==null)
			return true;
		
		return false; 
	}
}//key ,value left node , right node 전부설정해줘야지 생성자 완성

class BST<K extends Comparable<K>, V> implements Dictionary<K, V>{

	BinNode<K, V> root;
	int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	public V find(K k) {
		return find_helper(k, root);
	}
	
	private V find_helper(K k, BinNode<K, V> rt) {
		if(rt == null) {
			return null;
		}
		else {
			if(k.compareTo(rt.key) < 0)
				return find_helper(k, rt.left);
			else if(k.compareTo(rt.key) > 0)
				return find_helper(k, rt.right);
			else
				return rt.value;
		}
	}
	//find 는 구현완료 
	
	
	

	/* code here! */
	public void clear() {

			root.left = null;
			root.right = null;
			root.value = null;
			root.key = null;
			size = 0;
		
	}

	
	
	public void insert(K k, V v) {
		BinNode<K,V> newNode = new BinNode<K,V>(k,v,null,null);
		if(size==0) {
			root = newNode;
			size++;
			return;
		}
		
		BinNode<K,V> current  = root;
		BinNode<K,V> parent = null;
		while(true) {
			parent = current;
			if(current.key.compareTo(k) > 0) {
				current = current.left;
				if(current==null) {
					parent.left = newNode;
					size++;
					return ;
				}
			
			}
			else if(current.key.compareTo(k) < 0){
				current = current.right;
				if(current == null) {
					parent.right = newNode;
					size++;
					return;
				}
			}
			else {
				current.value = v;
				return ;
			}
		}
		
	}
	
	
	
	public V remove(K k){
        if(size==0)
			return null;
        BinNode<K,V> parentNode = root;
        BinNode<K,V> currNode = root;
        boolean isLeftChild = false;

        while(currNode.key != k) {
            parentNode = currNode;
            if(currNode.key.compareTo(k) > 0) {
                isLeftChild = true;
                currNode = currNode.left;
            }
            else {
                isLeftChild = false;
                currNode = currNode.right;
            }
            if(currNode == null){
                return null;
            }
        }
        
        V removed_val = currNode.value;
        

        if(currNode.left == null && currNode.right == null) { //ㅅ각제노드의 자식노드가 없을경우 
            if(currNode == root) { //root일경우
                root = null; 
            }
            if(isLeftChild) {
                parentNode.left = null; 
            }
            else {
                parentNode.right = null;  
            }
        } 
        
        else if(currNode.right == null){
        	//삭제노드가 오른쪽이 null일경우
        	if(currNode == root) {
        		root = root.left;
        	}
        	
        	else if(isLeftChild) {
                parentNode.left = (currNode.left);
            }
            else {
                parentNode.right = currNode.left; 
            }

          
        } 
        
        else if(currNode.left == null) {      	
        	if(currNode == root) {
        		root = root.right;
        	}
        	else if(isLeftChild) {
                parentNode.left = (currNode.right);
            }
            else {
                parentNode.right = currNode.right; 
            }   
        }
        
        else {                                        // 3. 자식이 둘인 경우
                BinNode<K,V> minimum = getminimum(currNode);
                if(currNode == root) {
                    root = minimum;
                }
                else if (isLeftChild){
                    parentNode.left = (minimum);             
                }
                else {
                    parentNode.right = minimum;    
                }
                minimum.left=(currNode.left);
    
        }
        size--;
        return removed_val;
    }

    BinNode<K,V> getminimum(BinNode<K,V> deleteNode) {
    	BinNode<K,V> minimum = null;
    	BinNode<K,V> minimumParent = null;
    	BinNode<K,V> currentNode = deleteNode.right;
        while(currentNode != null) {
            minimumParent = minimum;
            minimum = currentNode;
            currentNode = currentNode.left;
        }
     
        if(minimum != deleteNode.right){
            minimumParent.left = minimum.right;
            minimum.right=deleteNode.right;
        }
        return minimum;
    }

	public int size() {
		return size;
	}
	
}

