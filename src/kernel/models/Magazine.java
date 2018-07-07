package kernel.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Magazine implements Serializable {
    private IntegerProperty id;
    private StringProperty coverPath;
    private StringProperty name;
    private StringProperty office;
    private StringProperty cycle;
    private StringProperty price;
    private StringProperty intro;
    private StringProperty className;
//    private IntegerProperty classNumber;

    public Magazine() {
    }

    public Magazine(Integer id, String coverPath, String name, String office, String cycle, String price, String intro, String className) {
        this.id = new SimpleIntegerProperty(id);
        this.coverPath = new SimpleStringProperty(coverPath);
        this.name = new SimpleStringProperty(name);
        this.office = new SimpleStringProperty(office);
        this.cycle = new SimpleStringProperty(cycle);
        this.price = new SimpleStringProperty(price);
        this.intro = new SimpleStringProperty(intro);
        this.className = new SimpleStringProperty(className);
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

    public String getCoverPath() {
        return coverPath.get();
    }

    public StringProperty coverPathProperty() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath.set(coverPath);
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

    public String getOffice() {
        return office.get();
    }

    public StringProperty officeProperty() {
        return office;
    }

    public void setOffice(String office) {
        this.office.set(office);
    }

    public String getCycle() {
        return cycle.get();
    }

    public StringProperty cycleProperty() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle.set(cycle);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getIntro() {
        return intro.get();
    }

    public StringProperty introProperty() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro.set(intro);
    }

    public String getClassName() {
        return className.get();
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public void setClassName(String className) {
        this.className.set(className);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", coverPath=" + coverPath +
                ", name=" + name +
                ", office=" + office +
                ", cycle=" + cycle +
                ", price=" + price +
                ", intro=" + intro +
                ", className=" + className +
                '}';
    }
}
