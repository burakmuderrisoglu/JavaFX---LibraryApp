package UserScreen;

import UserScreen.MyProfile_Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class User_Screen_Controller implements Initializable{
    @FXML
    public Label lblUsername;
    @FXML
    public Button myprofile;
    public Label lblUseridFromMyProfile;


    Stage stage = new Stage();
    Stage stage2 = new Stage();

    /* public void GoToMyProfile(ActionEvent event) throws IOException
     {
         ((Node) (event.getSource())).getScene().getWindow().hide();
         Parent p1 = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
         Scene scnMyProfile = new Scene(p1);

         stage.setTitle("My Profile");
         stage.setScene(scnMyProfile);
         stage.show();
     }
 */
/*

    public void GoToBooks(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("Books.fxml"));
        Scene scnBooks = new Scene(p1);

        stage.setTitle("Books");
        stage.setScene(scnBooks);
        stage.show();
    }*/

    public void Logout(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Login/User_Login.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("User Login");
        stage.setScene(scnUserLogin);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myprofile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader=new FXMLLoader(getClass().getResource("../UserScreen/MyProfile.fxml"));


                try {
                    Parent root=loader.load();
                    MyProfile_Controller secondController=loader.getController();
                    secondController.setLblusernamemyprofile(lblUsername.getText());

                    ((Node) (event.getSource())).getScene().getWindow().hide();


                    stage.setTitle("My Profile");
                    Scene scene=new Scene(root);
                    stage.setScene(scene);

                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void setLblusername(String text){

        lblUsername.setText(text);
    }

    public void setlblusernamebacktomain(String text){

        lblUsername.setText(text);
    }
    public void GoToBooks (ActionEvent event)throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader2=new FXMLLoader(getClass().getResource("Books.fxml"));
        Parent root2 = loader2.load();
        Books_Controller fourthController = loader2.getController();
        fourthController.setlblFromUserScreen(lblUsername.getText());


        stage2.setTitle("Books");
        Scene scene2 = new Scene(root2);
        stage2.setScene(scene2);
        stage2.show();
    }



}