package kernel.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Order implements Serializable {
    private IntegerProperty id;
    private IntegerProperty uid;
    private StringProperty userName;
    private IntegerProperty mid;
    private StringProperty magazineName;
    private IntegerProperty month;
    private IntegerProperty copiesNum;
    private IntegerProperty totalPrice;

    public Order() {
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getMagazineName() {
        return magazineName.get();
    }

    public StringProperty magazineNameProperty() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName.set(magazineName);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUid() {
        return uid.get();
    }

    public IntegerProperty uidProperty() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid.set(uid);
    }

    public int getMid() {
        return mid.get();
    }

    public IntegerProperty midProperty() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid.set(mid);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getCopiesNum() {
        return copiesNum.get();
    }

    public IntegerProperty copiesNumProperty() {
        return copiesNum;
    }

    public void setCopiesNum(int copiesNum) {
        this.copiesNum.set(copiesNum);
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    public IntegerProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", userName=" + userName +
                ", mid=" + mid +
                ", magazineName=" + magazineName +
                ", month=" + month +
                ", copiesNum=" + copiesNum +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
