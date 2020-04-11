public class phone {
    String brand;
    String model;
    String price;
    String stock;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public phone(String brand, String model, String price, String stock) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;

    }
}
