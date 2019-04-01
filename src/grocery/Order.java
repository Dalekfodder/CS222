package grocery;

public class Order {
    private Product product;
    private double weight;

    public Order(Product product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getWeight() {
        return weight;
    }

    public double calculatePrice() {
        Kind orderType = this.product.getKind();
        double price = this.product.getPrice();

        if (orderType == Kind.SPECIAL && weight > 4) {
            return 4 * price + (weight - 4) * price * 0.5;

        } else if (orderType == Kind.DISCOUNT && weight > 5) {
            return 5 * price + (weight - 5) * price * 0.9;

        } else if (orderType == Kind.PRODUCT_OF_THE_WEEK) {
            return weight * price * 0.6;

        } else {
            return weight * price;
        }
    }

    public double calculatePoints() {
        if (this.product.getKind() == Kind.PRODUCT_OF_THE_WEEK) {
            return this.calculatePrice() * 0.125;
        } else {
            return this.calculatePrice() * 0.05;
        }
    }
}
