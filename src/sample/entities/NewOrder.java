package sample.entities;

/**
 * Date: 24.02.2019 (воскресенье)
 * Project name: TestApplication
 * Package name: sample.entities
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class NewOrder extends Order {

    private String product;
    private int count;
    private int price;

    public NewOrder() {
    }

    public NewOrder(String product, int count, int price) {

        this.product = product;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        return "NewOrder{" +
                "product='" + product + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
