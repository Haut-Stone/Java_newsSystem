package kernel.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Admin implements User, Serializable {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;

    @Override
    public String showUsername() {
        return getUsername();
    }

    @Override
    public String showPassword() {
        return getPassword();
    }

    @Override
    public String showType() {
        return "管理员";
    }

    public Admin() {
    }

    public Admin(Integer id, String username, String password) {
        this.id = new SimpleIntegerProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                '}';
    }
}
