package kernel.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class MagazineClass {
    private IntegerProperty id;
    private StringProperty name;

    public MagazineClass() {
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return "MagazineClass{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
