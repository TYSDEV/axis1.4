
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Dasarath
 * 
 * @date Dec 30, 2004
 */
public class ALElement {
	ArrayList al;

	public ALElement() {
		al = new ArrayList();
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