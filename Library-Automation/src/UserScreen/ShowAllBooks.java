package UserScreen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShowAllBooks {

    private final StringProperty book_id;
    private final StringProperty book_title;
    private final StringProperty author_name;
    private final StringProperty category_name;
    private final StringProperty book_language;

    public ShowAllBooks(String book_id, String book_title, String author_name, String category_name, String book_language) {
        this.book_id = new SimpleStringProperty(book_id);
        this.book_title = new SimpleStringProperty(book_title);
        this.author_name = new SimpleStringProperty(author_name);
        this.category_name = new SimpleStringProperty(category_name);
        this.book_language = new SimpleStringProperty(book_language);
    }

    public String getBook_id() {
        return book_id.get();
    }

    public StringProperty book_idProperty() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id.set(book_id);
    }

    public String getBook_title() {
        return book_title.get();
    }

    public StringProperty book_titleProperty() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title.set(book_title);
    }

    public String getAuthor_name() {
        return author_name.get();
    }

    public StringProperty author_nameProperty() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name.set(author_name);
    }

    public String getCategory_name() {
        return category_name.get();
    }

    public StringProperty category_nameProperty() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name.set(category_name);
    }

    public String getBook_language() {
        return book_language.get();
    }

    public StringProperty book_languageProperty() {
        return book_language;
    }

    public void setBook_language(String book_language) {
        this.book_language.set(book_language);
    }




}