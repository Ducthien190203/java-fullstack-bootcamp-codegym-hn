
public class Test {
	public static void main(String[] args) {
		Student student1 = new Student();
		student1.displayInfo();

		student1.setClasses("C101");
		student1.setName("MMM");
		System.out.println();
		System.out.println("Changed infomation!");
		student1.displayInfo();
	}
}
