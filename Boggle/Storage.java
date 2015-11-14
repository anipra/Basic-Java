
import java.util.Deque;
import java.util.Stack;
import java.util.ArrayDeque;

/**
 * @author Anipra
 *
 */

public class Storage {
	
	String str;
	boolean flag = false;
	
	Stack<Move> s = new Stack<Move>();
	Deque<Move> q = new ArrayDeque<Move>();
	
	public void stackOrQueue(String s){
		this.str = s;
		if(str.equals("-s")) {
			flag = true;
		}
		else if(str.equals("-q"))
			flag = false;
		else
			System.out.println("Wrong argument passed!");
	}
	

	public void store(Move m) {
		if(flag == true){
			s.push(m);	
		}
		else {
			q.offerFirst(m);
		}
	}
	
	public Move retrieve() {
		Move m = null;
		if(flag ==  true)
			m = s.pop();
		else
			m = q.pollFirst();
		return m;
	}
	
	public boolean isEmpty() {
		boolean b = false;
		if(flag == true)
			b = s.empty();
		else
			b = q.isEmpty();
		return b; 
	}

}
