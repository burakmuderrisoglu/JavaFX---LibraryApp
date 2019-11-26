package Admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserBackup {

    private final StringProperty backup_username;
    private final StringProperty backup_user_name;
    private final StringProperty backup_user_lastname;
    private final StringProperty backup_user_password;
    private final StringProperty backup_user_phone;
    private final StringProperty backup_user_email;


    //Constructor
    public UserBackup(String backup_username, String backup_user_name, String backup_user_lastname, String backup_user_password, String backup_user_phone, String backup_user_email) {
        this.backup_username = new SimpleStringProperty(backup_username);
        this.backup_user_name = new SimpleStringProperty(backup_user_name);
        this.backup_user_lastname = new SimpleStringProperty(backup_user_lastname);
        this.backup_user_password = new SimpleStringProperty(backup_user_password);
        this.backup_user_phone = new SimpleStringProperty(backup_user_phone);
        this.backup_user_email = new SimpleStringProperty(backup_user_email);

    }

    //getters
    public String getbackup_username() {
        return backup_username.get();
    }

    public String getbackup_user_name() {
        return backup_user_name.get();
    }

    public String getbackup_user_lastname() {
        return backup_user_lastname.get();
    }

    public String getbackup_user_password() {
        return backup_user_password.get();
    }

    public String getbackup_user_phone() {
        return backup_user_phone.get();
    }

    public String getbackup_user_email() {
        return backup_user_email.get();
    }


    //setters
    public void setbackup_username(String value) {
        backup_username.set(value);
    }

    public void setbackup_user_name(String value) {
        backup_user_name.set(value);
    }

    public void setbackup_user_lastname(String value) {
        backup_user_lastname.set(value);
    }

    public void setbackup_user_password(String value) {
        backup_user_password.set(value);
    }

    public void setbackup_user_phone(String value) {
        backup_user_phone.set(value);
    }

    public void setbackup_user_email(String value) {
        backup_user_email.set(value);
    }

    // Property Values
    public StringProperty backup_usernameProperty() {
        return backup_username;
    }

    public StringProperty backup_user_nameProperty() {
        return backup_user_name;
    }

    public StringProperty backup_user_lastnameProperty() {
        return backup_user_lastname;
    }

    public StringProperty backup_user_passwordProperty() {
        return backup_user_password;
    }

    public StringProperty backup_user_phoneProperty() {
        return backup_user_phone;
    }

    public StringProperty backup_user_emailProperty() {
        return backup_user_email;
    }
}