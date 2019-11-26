package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Login_Controller {
    Stage stage = new Stage();

    public TextField txtAdminName;
    public PasswordField txtAdminPassword;

    public void GoBack(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Scene scnAdminLogin = new Scene(p1);

        stage.setTitle("LibraryApp");
        stage.setScene(scnAdminLogin);
        stage.show();
    }

    public void LoginAdmin(ActionEvent event) throws IOException
    {
        if (txtAdminName.getText().isEmpty() || txtAdminPassword.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter all necessary information!");

            alert.showAndWait();
        }

        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM admin_detail WHERE admin_name = '" + txtAdminName.getText() + "' AND admin_password = '" + txtAdminPassword.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
                Scene scnAdminLogin = new Scene(p1);

                stage.setTitle("Admin Screen");
                stage.setScene(scnAdminLogin);
                stage.show();



            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Login Failed!");
                alert.setContentText("Please check your username and password!");

                alert.showAndWait();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}