package Admin;

import Conn.DBConnection;
import UserScreen.BookDetails;
import UserScreen.UserDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_User_Backup_Controller {
    @FXML
    private TableColumn<UserBackup, String> backup_username;
    @FXML
    private TableColumn<Admin.UserBackup, String> backup_user_name;
    @FXML
    private TableColumn<Admin.UserBackup, String> backup_user_lastname;
    @FXML
    private TableColumn<Admin.UserBackup, String> backup_user_password;
    @FXML
    private TableColumn<Admin.UserBackup, String> backup_user_phone;
    @FXML
    private TableColumn<Admin.UserBackup, String> backup_user_email;

    @FXML
    private TableView<Admin.UserBackup> UserDetailsBackupTable;
    @FXML
    public Label lbl_username;

    Stage stage = new Stage();
    private DBConnection dc;
    public ObservableList<UserBackup> data;

    public void GoToAdminScreen(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("Admin Screen");
        stage.setScene(scnUserLogin);
        stage.show();
    }

    public void ShowUserDetails(ActionEvent event) {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from user_info_backup ");

            while (resultSet.next()) {
                //get string from db
                data.add(new Admin.UserBackup(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        backup_username.setCellValueFactory(new PropertyValueFactory<>("backup_username"));
        backup_user_name.setCellValueFactory(new PropertyValueFactory<>("backup_user_name"));
        backup_user_lastname.setCellValueFactory(new PropertyValueFactory<>("backup_user_lastname"));
        backup_user_password.setCellValueFactory(new PropertyValueFactory<>("backup_user_password"));
        backup_user_phone.setCellValueFactory(new PropertyValueFactory<>("backup_user_phone"));
        backup_user_email.setCellValueFactory(new PropertyValueFactory<>("backup_user_email"));


        UserDetailsBackupTable.setItems(null);
        UserDetailsBackupTable.setItems(data);

    }

    public void displaySelected(MouseEvent event) {
        lbl_username.setVisible(false);

        UserBackup getRow = UserDetailsBackupTable.getSelectionModel().getSelectedItem();
        String username = getRow.getbackup_username();
        lbl_username.setText(username);
    }

    public void DeleteSelectedUser(ActionEvent event) throws SQLException {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String sql = "DELETE FROM user_info_backup WHERE backup_username='" + lbl_username.getText() + "'";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("You have successfully delete a user!");

        alert.showAndWait();

    }
}