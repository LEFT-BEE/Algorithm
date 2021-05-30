import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



class Solution6 {
	static int elements[];
	static int rank[];
	static int N ,M;
	static int answer=0;
	
	static public void UNION(int root1 , int root2) {
		
		if(rank[root1] < rank[root2]) {elements[root1] = root2;}
		else {elements[root2] = root1;}
		
		if(rank[root1] == rank[root2]) {
			elements[root2] = root1;
			rank[root1]++;
		}
	}
	
	
	public static int FIND(int n) {
		if(elements[n] == -1)
			return n;
		elements[n] = FIND(elements[n]);
		return FIND(elements[n]);
	}
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
		elements = new int[N];
		rank = new int[N];
		
		for(int i= 0 ; i < N; i++) {
			elements[i] = -1;
			rank[i] = 0;
		}
		
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
    		int u = Integer.parseInt(st2.nextToken());
    		int v = Integer.parseInt(st2.nextToken());
    		int root_u = FIND(u);
    		int root_v = FIND(v);
    		if(root_u != root_v) {
    			UNION(root_u, root_v);
    			N--;
    		}
		}	
		
		answer = N;
    	br.close();
    	System.out.println(answer);
    	
    }
}