package sample.entities;

/**
 * Date: 15.02.2019 (пятница)
 * Project name: TestApplication
 * Package name: sample.entities
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Furniture {

    private String articul;
    private String name;
    private String type;
    private String length;
    private String width;
    private String weight;
    private String image;
    private int price;

    public Furniture(String articul,
                     String name,
                     String type,
                     String length,
                     String width,
                     String weight,
                     String image,
                     int price) {
        this.articul = articul;
        this.name = name;
        this.type = type;
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.image = image;
        this.price = price;
    }

    public Furniture() {
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "articul='" + articul + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", weight='" + weight + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
