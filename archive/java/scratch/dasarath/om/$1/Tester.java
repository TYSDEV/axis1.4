/*
 * Created on Dec 30, 2004
 *
 */

/**
 * @author Dasarath
 * 
 * @date Dec 30, 2004
 */
public class Tester {
	public static final long N = 200;

	private long numChildren;

	private long numTraversals;

	Tester(long c, long t) {
		numChildren = c;
		numTraversals = t;
	}

	void testLLElement() throws Exception {
		for (int i = 0; i < N; i++) {
			LLElement e = new LLElement();
			for (int j = 0; j < numChildren; j++)
				e.addChild(new LLElement());
			for (int k = 0; k < numTraversals; k++)
				e.traverse();
		}
	}

	void testALElement(boolean useIter) throws Exception {
		for (int i = 0; i < N; i++) {
			ALElement e = new ALElement();
			for (int j = 0; j < numChildren; j++)
				e.addChild(new ALElement());
			for (int k = 0; k < numTraversals; k++)
				e.traverse(useIter);
		}
	}

	private static void test(long c, long t, boolean useIter) throws Exception {
		Tester instance = new Tester(c, t);
		System.out.println("children= " + c + ", traversals= " + t
				+ "using iterator= " + useIter);
		long t1 = System.currentTimeMillis();
		instance.testLLElement();
		long t2 = System.currentTimeMillis();
		long r = (t2 - t1) / N;
		System.out.println("LLElement= " + r + "ms");
		t1 = System.currentTimeMillis();
		instance.testALElement(useIter);
		t2 = System.currentTimeMillis();
		r = (t2 - t1) / N;
		System.out.println("ALElement= " + r + "ms");
	}

	public static void main(String[] args) throws Exception {
		test(1000, 1, false);
		test(10000, 1, false);

		test(100, 100, false);
		test(100, 1000, false);
		test(100, 10000, false);

		test(100, 100, true);
		test(100, 1000, true);
		test(100, 10000, true);
	}
}