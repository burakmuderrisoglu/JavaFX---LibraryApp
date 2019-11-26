package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Authors {
    private final StringProperty author_id;
    private final StringProperty author_name;


    public Authors(String author_id, String author_name) {
        this.author_id =new SimpleStringProperty(author_id);
        this.author_name =new SimpleStringProperty(author_name);

    }

    public String getAuthor_id() {
        return author_id.get();
    }

    public StringProperty author_idProperty() {
        return author_id;
    }

    public String getAuthor_name() {
        return author_name.get();
    }

    public StringProperty author_nameProperty() {
        return author_name;
    }

    public void setAuthor_id(String value) {
        author_id.set(value);
    }

    public void setAuthor_iname(String value) {
        author_name.set(value);
    }
}