/*
 * Created on Dec 30, 2004
 *
 */

/**
 * @author Dasarath
 *
 * @date Dec 30, 2004
 */
public class LLElement {
	LLElement nextSib, prevSib, firstChild;
	LLAttr fattr;

	public LLElement() {
		firstChild= null;
		nextSib= null;
		prevSib= null;
		fattr= null;
	}

	public void addChild(LLElement child) {
		child.prevSib= null;		
		if (firstChild == null)
			child.nextSib= null;			
		else {		
			child.nextSib= firstChild;
			firstChild.prevSib= child;
		}
		firstChild= child;
	}

	public void addAttr(LLAttr a){
		a.prevSib= null;
		if (fattr == null)
			a.nextSib = null;
		else {
			a.nextSib = fattr;
			fattr.prevSib = a;
		}
		fattr = a;
	}
	
	public LLAttr getAttr(String n){
		LLAttr a= fattr;
		while (a != null) {
			if (a.name.equals(n))
				return a;
			a= a.nextSib;
		}
		return null;
	}	
	
	public LLElement traverse(){
		LLElement e= firstChild;
		while (e.nextSib != null)
			e= e.nextSib;
		return e;		
	}
}
