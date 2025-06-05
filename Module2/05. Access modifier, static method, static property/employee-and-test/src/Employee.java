
public class Employee {
	private int id;
	private String name, position;
	private double salary;
	private String hireDate, department;

	public Employee() {

	}

	public Employee(String name, String position) {
		this.name = name;
		this.position = position;
	}

	public Employee(int id, String name, String position, double salary, String hireDate, String department) {
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

	public void calculateBonus(double percentage) {
		if (percentage < 0) {
			System.out.println("Percentage cannot be negative.");
			return;
		}
		double bonus = this.salary * (percentage / 100);
		System.out.println("Salary bonus:" + bonus);
		;

	}

	public void raiseSalary(double percentage) {
		if (percentage < 0) {
			System.out.println("Percentage cannot be negative.");
			return;
		}
		this.salary += this.salary * (percentage / 100);
		System.out.println("New salary after raise" + this.salary);
	}

	public void displayInfo() {
		System.out.println("Employee [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary
				+ ", hireDate=" + hireDate + ", department=" + department + "]");
	}

	public boolean isManager() {
		if (position.equals("Manager"))
			return true;
		else
			return false;
	}
}
