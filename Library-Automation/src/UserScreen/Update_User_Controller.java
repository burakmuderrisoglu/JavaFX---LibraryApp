package UserScreen;

import Conn.DBConnection;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Update_User_Controller implements Initializable {

    private DBConnection dc;

    Stage stage = new Stage();
    @FXML
    public Button Back_Button;
    @FXML
    public Label lbluserupdate;

    public TextField First_Name;
    public TextField Last_Name;
    public TextField txtusername;
    public TextField txtpassword;
    public TextField txtZero;
    public TextField txtphone;
    public TextField txtemail;


    Connection conn = dc.Connect();
    public void UpdateProfile(ActionEvent event)throws SQLException
    {


        if(First_Name.getText().isEmpty() || Last_Name.getText().isEmpty() || txtpassword.getText().isEmpty() || txtemail.getText().isEmpty() || txtpassword.getText().isEmpty() )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter all necessary information!");

            alert.showAndWait();
        }
        else
        {
            Statement statement = conn.createStatement();
            String sql = "UPDATE user_info SET user_name='"+First_Name.getText()+"', user_lastname='"+Last_Name.getText()+"', user_password='"+txtpassword.getText()+"', user_phone='"+txtphone.getText()+"', user_email='"+txtemail.getText()+"' WHERE username='"+lbluserupdate.getText()+"'";
            statement.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You have successfully updated your information!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Back_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FXMLLoader loader=new FXMLLoader(getClass().getResource("MyProfile.fxml"));

                try {
                    Parent root=loader.load();
                    MyProfile_Controller secondController=loader.getController();
                    secondController.setlbluserupdateback(lbluserupdate.getText());


                    ((Node) (event.getSource())).getScene().getWindow().hide();

                    stage.setTitle("My Profile ");
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setLblusernameupdatescreen(String text){

        lbluserupdate.setText(text);
    }

    PreparedStatement pst=null;
    ResultSet rs=null;
    public void btnLoadInfo(ActionEvent event) {

        try {
            String sql = "SELECT user_name,user_lastname,user_password,user_phone,user_email FROM user_info WHERE username ='"+lbluserupdate.getText()+"'";
            pst=conn.prepareStatement(sql);

            rs = pst.executeQuery();
            if(rs.next()) {
                String user_name = rs.getString("user_name");
                First_Name.setText(user_name);

                String user_lastname = rs.getString("user_lastname");
                Last_Name.setText(user_lastname);

                String user_password = rs.getString("user_password");
                txtpassword.setText(user_password);

                String user_phone = rs.getString("user_phone");
                txtphone.setText(user_phone);

                String user_email = rs.getString("user_email");
                txtemail.setText(user_email);

            }
        } catch (SQLException e ) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
}