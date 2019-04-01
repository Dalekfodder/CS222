package grocery;

enum Kind {
    SPECIAL, DISCOUNT, PRODUCT_OF_THE_WEEK, LUXURY;
}


public class Product {

    private Kind kind;
    private String name;
    private double price;

    public Product(Kind kind, String name, double price) {
        this.kind = kind;
        this.name = name;
        this.price = price;
    }

    public Kind getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
}
