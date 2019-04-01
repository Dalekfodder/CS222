package grocery;

public class Grocery {
    public static void main(String[] args) {
        Customer customer = new Customer("Tony Stark");

        Product seaBass = new Product(Kind.SPECIAL, "levrek", 35);
        Product hazelnut = new Product(Kind.PRODUCT_OF_THE_WEEK, "findik", 49);
        Product spinach = new Product(Kind.DISCOUNT, "ispanak", 12);
        Product celery = new Product(Kind.DISCOUNT, "kereviz", 9);
        Product almond = new Product(Kind.DISCOUNT, "badem", 100);

        customer.addOrder(new Order(seaBass, 1.5));
        customer.addOrder(new Order(hazelnut, 5));
        customer.addOrder(new Order(spinach, 3.5));
        customer.addOrder(new Order(celery, 2.5));
        customer.addOrder(new Order(almond, 0.8));

        System.out.println(customer.getReceipt());
        System.out.println("=============");

        almond.setKind(Kind.PRODUCT_OF_THE_WEEK);
        System.out.println(customer.getReceipt());
    }
}
