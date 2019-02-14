package sample.entities;

/**
 * Date: 12.02.2019 (вторник)
 * Project name: TestApplication
 * Package name: sample
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Material {

    private String articul;
    private String name;
    private String marka;
    private String color;
    private int length;
    private int width;
    private int price;

    public Material(String articul,
                    String name,
                    String marka,
                    String color,
                    int length,
                    int width,
                    int price) {
        this.articul = articul;
        this.name = name;
        this.marka = marka;
        this.color = color;
        this.length = length;
        this.width = width;
        this.price = price;
    }

    public Material() {
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

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Material{" +
                "articul='" + articul + '\'' +
                ", name='" + name + '\'' +
                ", marka='" + marka + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", price=" + price +
                '}';
    }
}
