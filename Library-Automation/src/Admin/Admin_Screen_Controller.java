package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_Screen_Controller {
    Stage stage = new Stage();

    public void GoToAdminUserDetails(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_User_Details.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Details");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void GoToAdminBookDetails(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Book_Details.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("Book Details");
        stage.setScene(scnUserLogin);
        stage.show();
    }

    public void GoToAdminLogin(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Login.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Details");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void GoToUserBackup(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_User_Backup.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Backup");
        stage.setScene(scnUserLogin);
        stage.show();
    }


}