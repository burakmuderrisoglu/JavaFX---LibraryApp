package UserScreen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDetails {

    private final StringProperty user_id;
    private final StringProperty user_name;
    private final StringProperty user_lastname;
    private final StringProperty user_sex;
    private final StringProperty user_password;
    private final StringProperty user_phone;
    private final StringProperty user_email;
    private final StringProperty user_recovery;
    private final StringProperty username;


    //Constructor
    public UserDetails(String user_id, String user_name, String user_lastname, String user_sex, String user_password, String user_phone, String user_email, String user_recovery, String username) {
        this.user_id = new SimpleStringProperty(user_id);
        this.user_name = new SimpleStringProperty(user_name);
        this.user_lastname = new SimpleStringProperty(user_lastname);
        this.user_sex = new SimpleStringProperty(user_sex);
        this.user_password = new SimpleStringProperty(user_password);
        this.user_phone = new SimpleStringProperty(user_phone);
        this.user_email = new SimpleStringProperty(user_email);
        this.user_recovery = new SimpleStringProperty(user_recovery);
        this.username = new SimpleStringProperty(username);
    }

    //getters
    public String getUser_id()
    {
        return user_id.get();
    }
    public String getUser_name()
    {
        return user_name.get();
    }
    public String getUser_lastname()
    {
        return user_lastname.get();
    }
    public String getUser_sex()
    {
        return user_sex.get();
    }
    public String getUser_password()
    {
        return user_password.get();
    }
    public String getUser_phone()
    {
        return user_phone.get();
    }
    public String getUser_email()
    {
        return user_email.get();
    }
    public String getUser_recovery()
    {
        return user_recovery.get();
    }
    public String getUsername()
    {
        return username.get();
    }



    //setters
    public void setUser_id(String value)
    {
        user_id.set(value);
    }
    public void setUser_name(String value)
    {
        user_name.set(value);
    }
    public void setUser_lastname(String value)
    {
        user_lastname.set(value);
    }
    public void setUser_sex(String value)
    {
        user_sex.set(value);
    }
    public void setUser_password(String value)
    {
        user_password.set(value);
    }
    public void setUser_phone(String value)
    {
        user_phone.set(value);
    }
    public void setUser_email(String value)
    {
        user_email.set(value);
    }
    public void setUser_recovery(String value)
    {
        user_recovery.set(value);
    }
    public void setUsername(String value)
    {
        username.set(value);
    }

    // Property Values
    public StringProperty user_idProperty()
    {
        return user_id;
    }
    public StringProperty user_nameProperty()
    {
        return user_name;
    }
    public StringProperty user_lastnameProperty()
    {
        return user_lastname;
    }
    public StringProperty user_sexProperty()
    {
        return user_sex;
    }
    public StringProperty user_passwordProperty()
    {
        return user_password;
    }
    public StringProperty user_phoneProperty()
    {
        return user_phone;
    }
    public StringProperty user_emailProperty()
    {
        return user_email;
    }
    public StringProperty user_recoveryProperty()
    {
        return user_recovery;
    }
    public StringProperty usernameProperty()
    {
        return username;
    }
}