package Login;

import Conn.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    private DBConnection dc;

    public Label isConnected = new Label();

    public TextField txtAdminName = new TextField();
    public TextField txtAdminPassword = new TextField();

    private Stage stage = new Stage();

    public void GoUserLoginPage(ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Login");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void GoAdminLoginPage (ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Login.fxml"));
        Scene scnAdminLogin = new Scene(p1);

        stage.setTitle("Admin Login");
        stage.setScene(scnAdminLogin);
        stage.show();
    }

}
