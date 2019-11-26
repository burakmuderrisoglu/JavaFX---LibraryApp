package Login;

import Conn.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Forgot_Password_Controller {

    private DBConnection dc;
    private Stage stage = new Stage();

    public TextField txtemail;
    public TextField color;

    public Label lblPassword;

    public void GoBack (ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        Scene scnLogin = new Scene(p1);

        stage.setTitle("Library Automation");
        stage.setScene(scnLogin);
        stage.show();
    }
    public void CheckRecovery(ActionEvent event) throws IOException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user_info WHERE user_email = '" + txtemail.getText() + "' AND user_recovery = '" + color.getText() + "'";
            ResultSet resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {

                Statement statementPassword = connection.createStatement();
                String sqlPassword = "SELECT user_password FROM user_info WHERE user_email = '" + txtemail.getText() + "'";
                ResultSet resultSetPassword = statementPassword.executeQuery(sqlPassword);

                if(resultSetPassword.next())
                {
                    String sqlPass = resultSetPassword.getString("user_password");
                    lblPassword.setText(sqlPass);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Recovery password is correct!");
                    alert.setContentText("Your password is:" + lblPassword.getText());

                    alert.showAndWait();

                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
                    Scene scnUserLogin = new Scene(p1);

                    stage.setTitle("User Login");
                    stage.setScene(scnUserLogin);
                    stage.show();
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Error occurred!");
                alert.setContentText("E-Mail or Recovery Answer is not correct!");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
