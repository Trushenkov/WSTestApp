package sample.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Класс сущности для таблицы "Поступаемые заказы"
 * <p>
 * Date: 14.02.2019 (четверг)
 * Project name: TestApplication
 * Package name: sample.entities
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class IncomingMaterial {

//    private String provider;
//    private String material;
//    private int count;
//    private int purchasePrice;
//    private int sum;

    private SimpleStringProperty provider = new SimpleStringProperty();
    private SimpleStringProperty material = new SimpleStringProperty();
    private SimpleIntegerProperty count = new SimpleIntegerProperty();
    private SimpleIntegerProperty purchasePrice = new SimpleIntegerProperty();
    private SimpleIntegerProperty sum = new SimpleIntegerProperty();

    public IncomingMaterial(SimpleStringProperty provider, SimpleStringProperty material, SimpleIntegerProperty count, SimpleIntegerProperty purchasePrice, SimpleIntegerProperty sum) {
        this.provider = provider;
        this.material = material;
        this.count = count;
        this.purchasePrice = purchasePrice;
        this.sum = sum;
    }

    public IncomingMaterial() {
    }

    public String getProvider() {
        return provider.get();
    }

    public void setProvider(String provider) {
        this.provider.set(provider);
    }

    public SimpleStringProperty providerProperty() {
        return provider;
    }

    public String getMaterial() {
        return material.get();
    }

    public void setMaterial(String material) {
        this.material.set(material);
    }

    public SimpleStringProperty materialProperty() {
        return material;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public int getPurchasePrice() {
        return purchasePrice.get();
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice.set(purchasePrice);
    }

    public SimpleIntegerProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public int getSum() {
        return sum.get();
    }

    public void setSum(int sum) {
        this.sum.set(sum);
    }

    public SimpleIntegerProperty sumProperty() {
        return sum;
    }

    @Override
    public String toString() {
        return "IncomingMaterial {" +
                "provider='" + provider + '\'' +
                ", material='" + material + '\'' +
                ", count=" + count +
                ", purchasePrice=" + purchasePrice +
                ", sum=" + sum +
                '}';
    }
}
