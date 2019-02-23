package sample.entities;

/**
 * Класс объекта из таблицы "Изделие"
 * <p>
 * Date: 16.02.2019 (суббота)
 * Project name: TestApplication
 * Package name: sample.entities
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Product {

    private int articul;
    private String name;
    private int length;
    private int width;
    private int height;
    private String image;
    private String comment;

    public Product(int articul, String name, int length, int width, int height, String image, String comment) {
        this.articul = articul;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.image = image;
        this.comment = comment;
    }

    public Product() {
    }

    public int getArticul() {
        return articul;
    }

    public void setArticul(int articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Product{" +
                "articul=" + articul +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", image='" + image + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}

