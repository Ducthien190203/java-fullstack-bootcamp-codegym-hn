public class Customer {
	private int id;
	private String name, email, phone, joinDate;
	private double totalPurchases;

	public Customer() {
	}

	public Customer(String name) {
		this.name = name;
	}

	public Customer(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Customer(int id, String name, String email, String phone, String joinDate, double totalPurchases) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.joinDate = joinDate;
		this.totalPurchases = totalPurchases;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public double getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(double totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

	public void makePurchases(double amount) {
		if (amount > 0) {
			this.totalPurchases += amount;
		}
	}

	public void displayInfo() {
		System.out.println("Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", joinDate=" + joinDate + ", totalPurchases=" + totalPurchases + "]");
		;
	}

	public boolean isVip() {
		if (totalPurchases >= 5000)
			return true;
		else
			return false;
	}

}
