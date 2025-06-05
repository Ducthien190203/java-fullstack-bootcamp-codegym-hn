
public class TestCustomer {
	public static void main(String[] args) {
		Customer thienCustomer = new Customer(01, "Thiện", "ducthien@", "0123459", "11/10/2003", 3000);

		thienCustomer.displayInfo();
		System.out.println(thienCustomer.isVip());
		thienCustomer.makePurchases(5000);
		System.out.println(thienCustomer.getTotalPurchases());
		System.out.println(thienCustomer.isVip());

	}
}
