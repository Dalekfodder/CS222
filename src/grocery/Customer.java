package grocery;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Order> orders;
    private String name;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public String getReceipt() {
        String receipt = "Customer: " + name + "\n";
        double totalPrice = 0;
        double totalPoints = 0;

        receipt += "-------------\n";

        boolean isProductOfTheWeek = false;
        for (Order order : orders) {
            receipt += order.getProduct().getName() + ": "
                    + order.getWeight() + " kg @ "
                    + order.getProduct().getPrice() + " TL/kg\n";

            double orderPrice = order.calculatePrice();
            totalPrice += orderPrice;
            receipt += "    " + orderPrice + " TL";

            double orderPoints = order.calculatePoints();
            totalPoints += orderPoints;
            receipt += ", " + orderPoints + " points\n";

            if(order.getProduct().getKind() == Kind.PRODUCT_OF_THE_WEEK)
                isProductOfTheWeek = true;
        }

        if (isProductOfTheWeek)
            totalPoints += 2;

        receipt += "\n";
        receipt += "Total cost: " + totalPrice + "\n";
        receipt += "Total points: " + totalPoints;
        return receipt;
    }
}