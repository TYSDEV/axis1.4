
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Dasarath
 * 
 * @date Dec 30, 2004
 */
public class ALElement {
//	ArrayList al;
	Vector al;
	HashMap	attrs;

	public ALElement() {
//		al = new ArrayList();
		al= new Vector();
		attrs= new HashMap();
	}
	
	public void addAttr(ALAttr a){
		attrs.put(a.name, a);
	}
	
	public void getAttr(String n){
		attrs.get(n);
	}

	public void addChild(ALElement child) {
		al.add(child);
	}

	public ALElement traverse(boolean useIter) {
		ALElement e = null;

		if (useIter) {
			Iterator iter = al.iterator();
			while (iter.hasNext())
				e = (ALElement) iter.next();
		} else {
			int n = al.size();
			for (int i = 0; i < n; i++)
				e = (ALElement) al.get(i);
		}
		return e;
	}
}