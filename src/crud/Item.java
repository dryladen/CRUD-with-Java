package crud;

public class Item {
    private String name;
    private float price;
    private int amount, id, stock;

    public Item() {

    }

    public Item(int id, String name, float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = amount;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getTotal() {
        return price * amount;
    }
}
