package sample.entities;

import java.sql.Date;

/**
 * Date: 16.02.2019 (суббота)
 * Project name: TestApplication
 * Package name: sample.entities
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Order {

    private int number;
    private Date date;
    private String step;
    private String order;
    private String manager;
    private int count;
    private int summa;

    public Order(int number, Date date, String step, String order, String manager, int count) {
        this.number = number;
        this.date = date;
        this.step = step;
        this.order = order;
        this.manager = manager;
        this.count = count;
    }

    public Order(int number, Date date, String step, String order, String manager, int count, int summa) {
        this.number = number;
        this.date = date;
        this.step = step;
        this.order = order;
        this.manager = manager;
        this.count = count;
        this.summa = summa;
    }

    public Order(Date date, int number, String step, String order, String manager, int summa) {
        this.number = number;
        this.date = date;
        this.step = step;
        this.order = order;
        this.manager = manager;
        this.summa = summa;
    }

    public Order(Date date, String step, String order, String manager, int summa) {
        this.date = date;
        this.step = step;
        this.order = order;
        this.manager = manager;
        this.summa = summa;
    }

    public Order() {
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Заказ {" +
                "Номер заказа: " + number +
                ", Дата: " + date +
                ", Этап выполнения: '" + step + '\'' +
                ", Заказчик: '" + order + '\'' +
                ", Менеджер: '" + manager + '\'' +
                ", Количество изделий: " + count +
                ", Стоимость: " + summa +
                '}';
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "number=" + number +
//                ", date=" + date +
//                ", step='" + step + '\'' +
//                ", order='" + order + '\'' +
//                ", manager='" + manager + '\'' +
//                ", count=" + count +
//                '}';
//    }
}
