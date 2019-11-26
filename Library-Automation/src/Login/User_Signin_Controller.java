package Login;

import Conn.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class User_Signin_Controller {

    private DBConnection dc;
    private Stage stage = new Stage();

    public TextField First_Name;
    public TextField Last_Name;
    public TextField Username;
    public RadioButton Male = new RadioButton("Male");
    public RadioButton Female = new RadioButton("Female");
    public PasswordField Password;
    public TextField txtZero;
    public TextField Phone;
    public TextField E_Mail;
    public TextField Secret_Question;

    public void GoBack (ActionEvent event) throws IOException
    {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
        Scene scnLogin = new Scene(p1);

        stage.setTitle("Library Automation");
        stage.setScene(scnLogin);
        stage.show();
    }
    public void InsertUser(ActionEvent event) throws SQLException,IOException
    {
        Connection conn = dc.Connect();

        boolean isSelectedMale = Male.isSelected();
        boolean isSelectedFemale = Female.isSelected();

        Random rand = new Random();

        int id = rand.nextInt(10000);

        if(Username.getText().isEmpty() || First_Name.getText().isEmpty() || Last_Name.getText().isEmpty() || Password.getText().isEmpty() || Secret_Question.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter all necessary information!");

            alert.showAndWait();
        }
        else
        {

            if(Female.isSelected() == true && Male.isSelected() == true)
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please choose only one gender!");

            alert.showAndWait();
            }

            else if(Male.isSelected() == true)
            {

                String sql = "INSERT INTO user_info VALUES('"+id+"','"+First_Name.getText()+"','"+Last_Name.getText()+"'," +
                        "'"+Male.getText()+"','"+Password.getText()+"','"+Phone.getText()+"','"+E_Mail.getText()+"',"+
                        "'"+Secret_Question.getText()+"','"+Username.getText()+"')";
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("You have successfully signed in!");
                alert.setContentText("You will be redirected to Login screen.");

                alert.showAndWait();

                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
                Scene scnUserLogin = new Scene(p1);

                stage.setTitle("User Login");
                stage.setScene(scnUserLogin);
                stage.show();
            }
            else if(Female.isSelected() == true)
            {
                String sql = "INSERT INTO user_info VALUES('"+id+"','"+First_Name.getText()+"','"+Last_Name.getText()+"'," +
                        "'"+Female.getText()+"','"+Password.getText()+"','"+Phone.getText()+"','"+E_Mail.getText()+"',"+
                        "'"+Secret_Question.getText()+"','"+Username.getText()+"')";
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("You have successfully signed in!");
                alert.setContentText("You will be redirected to Login screen.");

                alert.showAndWait();

                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent p1 = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
                Scene scnUserLogin = new Scene(p1);

                stage.setTitle("User Login");
                stage.setScene(scnUserLogin);
                stage.show();
            }
            else if(Female.isSelected() == false && Male.isSelected() == false)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Error occurred!");
                alert.setContentText("Please specify your gender!");

                alert.showAndWait();
            }

        }

    }
}
