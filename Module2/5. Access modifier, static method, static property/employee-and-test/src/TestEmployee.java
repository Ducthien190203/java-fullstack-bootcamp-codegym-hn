
public class TestEmployee {
	public static void main(String[] args) {
		Employee thienEmployee = new Employee(2, "Thiện", "Newbie", 12000, "19/02/2003", "C03");
		thienEmployee.displayInfo();
		thienEmployee.calculateBonus(10);
		thienEmployee.raiseSalary(50);
		System.out.println(thienEmployee.isManager());
		System.out.println();
		thienEmployee.displayInfo();

	}
}
