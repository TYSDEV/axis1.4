/*
 * Created on Mar 22, 2005
 */

/**
 * @author Dasarath Weeratunge
 */
public class LLAttr {
	public LLAttr nextSib, prevSib;
	public String name;

	public LLAttr(String name) {
		nextSib= null;
		prevSib= null;
		this.name= name;
	}
}
