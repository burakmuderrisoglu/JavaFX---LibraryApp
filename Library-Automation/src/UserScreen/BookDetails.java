package UserScreen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookDetails {

    private final StringProperty book_id;
    private final StringProperty book_title;
    private final StringProperty author_id;
    private final StringProperty category_id;
    private final StringProperty book_language;


    //Constructor
    public BookDetails(String book_id, String book_title, String author_id, String category_id, String book_language) {
        this.book_id = new SimpleStringProperty(book_id);
        this.book_title = new SimpleStringProperty(book_title);
        this.author_id = new SimpleStringProperty(author_id);
        this.category_id = new SimpleStringProperty(category_id);
        this.book_language = new SimpleStringProperty(book_language);


    }
    //getters
    public String getbook_id ()
    {
        return book_id.get();
    }
    public String getbook_title ()
    {
        return book_title.get();
    }
    public String getauthor_id ()
    {
        return author_id.get();
    }
    public String getcategory_id ()
    {
        return category_id.get();
    }
    public String getbook_language ()
    {
        return book_language.get();
    }



    //setters
    public void setbook_id (String value)
    {
        book_id.set(value);
    }
    public void setbook_title (String value)
    {
        book_title.set(value);
    }
    public void setauthor_id (String value)
    {
        author_id.set(value);
    }
    public void setcategory_id (String value)
    {
        category_id.set(value);
    }
    public void setbook_language (String value)
    {
        book_language.set(value);
    }



    // Property Values
    public StringProperty book_idProperty ()
    {
        return book_id;
    }
    public StringProperty book_titleProperty ()
    {
        return book_title;
    }
    public StringProperty author_idProperty ()
    {
        return author_id;
    }
    public StringProperty category_idProperty ()
    {
        return category_id;
    }
    public StringProperty book_languageProperty ()
    {
        return book_language;
    }

}