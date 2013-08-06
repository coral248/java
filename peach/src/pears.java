public class Animal {
	public Animal() {
		System.out.println("Making an Animal.");
}
}

public class Hippo extends Animal {
	public Hippo() {
		System.out.println("Making a Hippo.");
	}
}

public class TestHippo {
	public static void main (String[] args) {
		System.out.println("Starting...");
		Hippo h = new Hippo();
	}
}