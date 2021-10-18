package Item;

public class Product {

    private final String productName;
    private final double productPrice;
    private int productStock;

    public Product(String productName, double productPrice, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public boolean checkIfEnoughStock() {
        return this.productStock > 0;
    }

    public void updateProductStock() {
        this.productStock--;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getFirstLetterOfProduct() {
        return this.productName.substring(0,1);
    }

    public int getProductStock() {
        return productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                '}';
    }
}
