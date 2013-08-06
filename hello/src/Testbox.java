
public class Testbox {
	
	Integer i;
	int j;
	
	public static void main(String[] args) {
		Testbox t = new Testbox();
		t.go();

	}
	
	public void go() {
		j=i;
		System.out.println(j);
		System.out.println(i);
	}

}
