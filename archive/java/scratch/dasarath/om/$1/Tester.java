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
	public static final long M = 1000000;
	private String[] names1= new String[10];
	private String[] names2= new String[10];
	private ALAttr[] attrsa= new ALAttr[10];
	private LLAttr[] attrsl= new LLAttr[10];

	private long numChildren;

	private long numTraversals;

	Tester(long c, long t) {
		numChildren = c;
		numTraversals = t;
		names1[3] = "m0ustUnderstand";
		names1[2] = "mu1stUnderstand";
		names1[7] = "mus2tUnderstand";
		names1[4] = "must3Understand";
		names1[9] = "mustU4nderstand";
		names1[1] = "mustUn5derstand";
		names1[5] = "mustUnd6erstand";
		names1[8] = "mustUnde7rstand";
		names1[6] = "mustUnder8stand";
		names1[0] = "mustUnders9tand";
		
		names2[0] = names1[3];
		names2[1] = names1[2];
		names2[2] = names1[7];
		names2[3] = names1[4];
		names2[4] = names1[9];
		names2[5] = names1[1];
		names2[6] = names1[5];
		names2[7] = names1[8];
		names2[8] = names1[6];
		names2[9] = names1[0];
		
		for (int i= 0; i < 10; i++){
			attrsa[i]= new ALAttr(names1[i]);
			attrsl[i]= new LLAttr(names1[i]);
		}	
	}
	
	void testLLAttr() throws Exception {
		for (int i = 0; i < M; i++) {
			LLElement e = new LLElement();
			for (int j = 0; j < 4; j++)
				e.addAttr(attrsl[j]);
			for (int k = 0; k < 4; k++)
				e.getAttr(names2[k]);
		}
	}

	void testALAttr() throws Exception {
		for (int i = 0; i < M; i++) {
			ALElement e = new ALElement();
			for (int j = 0; j < 4; j++)
				e.addAttr(attrsa[j]);
			for (int k = 0; k < 4; k++)
				e.getAttr(names2[k]);
		}
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

	private static void testAttr() throws Exception {
		Tester instance = new Tester(0, 0);
		long t1 = System.currentTimeMillis();
		instance.testALAttr();
		long t2 = System.currentTimeMillis();
		long r = (t2 - t1) / N;
		System.out.println("ALAttr= " + r + "ms");
		t1 = System.currentTimeMillis();
		instance.testLLAttr();
		t2 = System.currentTimeMillis();
		r = (t2 - t1) / N;
		System.out.println("=LLAttr= " + r + "ms");
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
		testAttr();
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