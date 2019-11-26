package Login;

import UserScreen.MyProfile_Controller;
import UserScreen.User_Screen_Controller;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class User_Login_Controller implements Initializable{

    public TextField txtUserName;
    public PasswordField txtUserPassword;
    public Label lblUsername;
    @FXML
    public Button User1_Login;
    Stage stage = new Stage();


    public void GoBack(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scnBack = new Scene(p1);

        stage.setTitle("LibraryApp");
        stage.setScene(scnBack);
        stage.show();
    }
    public void GoForgot(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Forgot_Password.fxml"));
        Scene scnForgot = new Scene(p1);

        stage.setTitle("Password Recovery");
        stage.setScene(scnForgot);
        stage.show();
    }

    public void GoSignin(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Signin.fxml"));
        Scene scnSignin = new Scene(p1);

        stage.setTitle("User Registration");
        stage.setScene(scnSignin);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        User1_Login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader=new FXMLLoader(getClass().getResource("../UserScreen/UserMainScreen.fxml"));

                try {
                    Parent root=loader.load();
                    User_Screen_Controller secondController=loader.getController();
                    secondController.setLblusername(txtUserName.getText());

                    Conn.DBConnection connectionClass = new Conn.DBConnection();
                    Connection connection = connectionClass.Connect();


                    if (txtUserName.getText().isEmpty() || txtUserPassword.getText().isEmpty()) {
                        lblUsername.setText(txtUserName.getText());
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("");
                        alert.setHeaderText("Error occurred!");
                        alert.setContentText("Please enter all necessary information!");

                        alert.showAndWait();
                    }

                    try {
                        Statement statement = connection.createStatement();
                        String sql = "SELECT * FROM user_info WHERE username = '" + txtUserName.getText() + "' AND user_password = '" + txtUserPassword.getText() + "'";
                        ResultSet resultSet = statement.executeQuery(sql);

                        if (resultSet.next()) {
                            ((Node) (event.getSource())).getScene().getWindow().hide();


                            stage.setTitle("LibraryApp");
                            Scene scene=new Scene(root);
                            stage.setScene(scene);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}