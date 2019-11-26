package UserScreen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RentedBooksModel {

    private final StringProperty user_id;
    private final StringProperty username;
    private final StringProperty book_id;
    private final StringProperty book_title;


    //Constructor
    public RentedBooksModel(String user_id,String username,String book_id, String book_title) {

        this.user_id = new SimpleStringProperty(user_id);
        this.username = new SimpleStringProperty(username);
        this.book_id = new SimpleStringProperty(book_id);
        this.book_title = new SimpleStringProperty(book_title);

    }
    //getters
    public String getuser_id ()
    {
        return user_id.get();
    }
    public String getusername ()
    {
        return username.get();
    }
    public String getbook_id ()
    {
        return book_id.get();
    }
    public String getbook_title ()
    {
        return book_title.get();
    }

    //setters
    public void setuser_id (String value)
    {
        user_id.set(value);
    }
    public void setusername (String value)
    {
        username.set(value);
    }
    public void setbook_id (String value)
    {
        book_id.set(value);
    }
    public void setbook_title (String value)
    {
        book_title.set(value);
    }

    // Property Values
    public StringProperty user_idProperty ()
    {
        return user_id;
    }
    public StringProperty usernameProperty ()
    {
        return username;
    }
    public StringProperty book_idProperty ()
    {
        return book_id;
    }
    public StringProperty book_titleProperty ()
    {
        return book_title;
    }
}
