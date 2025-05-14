
public class Employee {
	private int id;
	private double salary;
	private String name, position, hireDate, department;

	public Employee() {

	}

	public Employee(String name, String position) {
		this.name = name;
		this.position = position;
	}

	public Employee(int id, double salary, String name, String position, String hireDate, String department) {
		super();
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.position = position;
		this.hireDate = hireDate;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public calculateBonus(double percentage) {
		
		
		
	}

}
