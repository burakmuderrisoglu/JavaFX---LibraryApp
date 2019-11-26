package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Categories {
    private final StringProperty category_id;
    private final StringProperty category_name;


    public Categories(String category_id, String category_name) {
        this.category_id =new SimpleStringProperty(category_id);
        this.category_name =new SimpleStringProperty(category_name);

    }

    public String getcategory_id() {
        return category_id.get();
    }

    public StringProperty category_idProperty() {
        return category_id;
    }

    public String getcategory_name() {
        return category_name.get();
    }

    public StringProperty category_nameProperty() {
        return category_name;
    }

    public void setcategory_id(String value) {
        category_id.set(value);
    }

    public void setcategory_name(String value) {
        category_name.set(value);
    }
}