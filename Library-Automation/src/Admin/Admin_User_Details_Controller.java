package Admin;

import Conn.DBConnection;
import Login.AESCrypt;
import UserScreen.RentedBooksModel;
import UserScreen.UserDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Admin_User_Details_Controller {
    @FXML
    private TableColumn<UserDetails,String> user_id;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_name;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_lastname;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_sex;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_password;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_phone;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_email;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> user_recovery;
    @FXML
    private TableColumn<UserScreen.UserDetails,String> username;
    @FXML
    private TableView<UserDetails> UserDetailsTable;
    @FXML
    public Label lblUserid;

    @FXML
    private TableColumn<UserScreen.RentedBooksModel,String> user_id_rented;
    @FXML
    private TableColumn<UserScreen.RentedBooksModel,String> username_rented;
    @FXML
    private TableColumn<UserScreen.RentedBooksModel,String> book_id_rented;
    @FXML
    private TableColumn<UserScreen.RentedBooksModel,String> book_title_rented;
    @FXML
    private TableView<RentedBooksModel> RentedBooksTable;

    public TextField txtSearchName;

    Stage stage = new Stage();
    private DBConnection dc;
    public ObservableList<UserDetails> data;
    public ObservableList<RentedBooksModel> data_rented;

    public void GoToAdminScreen(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("Admin Screen");
        stage.setScene(scnUserLogin);
        stage.show();
    }

    public void ShowUserDetails(ActionEvent event) throws Exception
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from ShowAllUsers ");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.UserDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), AESCrypt.encrypt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        user_lastname.setCellValueFactory(new PropertyValueFactory<>("user_lastname"));
        user_sex .setCellValueFactory(new PropertyValueFactory<>("user_sex"));
        user_password.setCellValueFactory(new PropertyValueFactory<>("user_password"));
        user_phone.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        user_email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        user_recovery.setCellValueFactory(new PropertyValueFactory<>("user_recovery"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));



        UserDetailsTable.setItems(null);
        UserDetailsTable.setItems(data);

    }

    public void displaySelected(MouseEvent event)
    {
        lblUserid.setVisible(false);

        UserDetails getRow = UserDetailsTable.getSelectionModel().getSelectedItem();
        String book_title = getRow.getUser_id();
        lblUserid.setText(book_title);
    }

    public void DeleteUser(ActionEvent event) throws SQLException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String sql = "DELETE FROM user_info WHERE user_id='"+lblUserid.getText()+"'";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        String sql2 = "DELETE FROM rented_books WHERE user_id='"+lblUserid.getText()+"'";
        Statement statement2 = connection.createStatement();
        statement2.execute(sql2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("You have successfully deleted a user record!");

        alert.showAndWait();
    }

    public void ShowRentedBooks(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data_rented = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from ShowRentedBooks");

            while(resultSet.next())
            {
                //get string from db
                data_rented.add(new UserScreen.RentedBooksModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        user_id_rented.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        username_rented.setCellValueFactory(new PropertyValueFactory<>("username"));
        book_id_rented.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title_rented .setCellValueFactory(new PropertyValueFactory<>("book_title"));


        RentedBooksTable.setItems(null);
        RentedBooksTable.setItems(data_rented);
    }

    public void SearchUser(ActionEvent event) throws Exception
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String query = "{call GetUserByName(?)}";
        ResultSet resultSet;


        try {
            data = FXCollections.observableArrayList();

            CallableStatement stmt=connection.prepareCall(query);
            stmt.setString(1,txtSearchName.getText());
            resultSet = stmt.executeQuery();

            // Execute query (Standard select query)
            //ResultSet resultSet = connection.createStatement().executeQuery("select * from user_info where user_name='"+txtSearchName.getText()+"' ");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.UserDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),AESCrypt.encrypt(resultSet.getString(5)),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        user_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        user_name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        user_lastname.setCellValueFactory(new PropertyValueFactory<>("user_lastname"));
        user_sex .setCellValueFactory(new PropertyValueFactory<>("user_sex"));
        user_password.setCellValueFactory(new PropertyValueFactory<>("user_password"));
        user_phone.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        user_email.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        user_recovery.setCellValueFactory(new PropertyValueFactory<>("user_recovery"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));


        UserDetailsTable.setItems(null);
        UserDetailsTable.setItems(data);
    }
}