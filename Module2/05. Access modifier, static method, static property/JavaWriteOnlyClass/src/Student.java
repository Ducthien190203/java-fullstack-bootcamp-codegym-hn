
public class Student {
	private String name = "Thien";
	private String classes = "C0325";

	public Student() {
//		this.name = name;
//		this.classes = classes;
	}

	void displayInfo() {
		System.out.println("Student infomation:");
		System.out.println("Name: " + name);
		System.out.println("Classes: " + classes);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

}
