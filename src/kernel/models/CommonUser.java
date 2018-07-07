package kernel.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommonUser implements User{
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;
    private StringProperty trueName;
    private StringProperty sid;
    private StringProperty tel;
    private StringProperty address;

    @Override
    public String showUsername() {
        return getPassword();
    }

    @Override
    public String showPassword() {
        return getPassword();
    }

    @Override
    public String showType() {
        return "普通用户";
    }

    public CommonUser() {
    }

    public CommonUser(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public CommonUser(Integer id, String username, String password, String trueName, String sid, String tel, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.trueName = new SimpleStringProperty(trueName);
        this.sid = new SimpleStringProperty(sid);
        this.tel = new SimpleStringProperty(tel);
        this.address = new SimpleStringProperty(address);
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

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getTrueName() {
        return trueName.get();
    }

    public StringProperty trueNameProperty() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName.set(trueName);
    }

    public String getSid() {
        return sid.get();
    }

    public StringProperty sidProperty() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid.set(sid);
    }

    public String getTel() {
        return tel.get();
    }

    public StringProperty telProperty() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", trueName=" + trueName +
                ", sid=" + sid +
                ", tel=" + tel +
                ", address=" + address +
                '}';
    }
}
