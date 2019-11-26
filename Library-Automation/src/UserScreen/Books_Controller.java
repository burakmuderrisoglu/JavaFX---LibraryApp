package UserScreen;

import Conn.DBConnection;
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

import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.sql.*;

public class Books_Controller {
    private DBConnection dc;
    public ObservableList<ShowAllBooks> data;


    @FXML
    private TableColumn<UserScreen.ShowAllBooks,String> book_id;
    @FXML
    private TableColumn<UserScreen.ShowAllBooks,String> book_title;
    @FXML
    private TableColumn<UserScreen.ShowAllBooks,String> author_name;
    @FXML
    private TableColumn<UserScreen.ShowAllBooks,String> category_name;
    @FXML
    private TableColumn<UserScreen.ShowAllBooks,String> book_language;
    @FXML
    private TableView<ShowAllBooks> AllBooks;
    @FXML
    public Label lblUsernameFromUserScreen;
    @FXML
    public Label lblBookTitle;
    @FXML
    public Label lblBookID;
    @FXML
    public TextField txtBookTitle;
    public TextField txtUserid;
    public TextField txtBookid;
    public ObservableList<UserScreen.ShowAllBooks> data2;
    private Stage stage = new Stage();
    private Stage stage2 = new Stage();

    public TextField txtSearchBook;

    public void setlblFromUserScreen(String text){

        lblUsernameFromUserScreen.setText(text);
    }
    public void displaySelected(MouseEvent event)
    {

        lblBookID.setVisible(false);
        lblBookTitle.setVisible(false);
        ShowAllBooks getRow = AllBooks.getSelectionModel().getSelectedItem();

        String book_id = getRow.getBook_id();
        String book_title = getRow.getBook_title();
        lblBookID.setText(book_id);
        lblBookTitle.setText(book_title);
    }

    public void backtomain (ActionEvent event)throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader2=new FXMLLoader(getClass().getResource("UserMainScreen.fxml"));
        Parent root2 = loader2.load();
        User_Screen_Controller thirdController = loader2.getController();
        thirdController.setlblusernamebacktomain(lblUsernameFromUserScreen.getText());


        stage2.setTitle("LibraryApp");
        Scene scene2 = new Scene(root2);
        stage2.setScene(scene2);
        stage2.show();

    }
    public void ShowAllBooks(ActionEvent event)throws SQLException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data2 = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from ShowAllBooks");

            while(resultSet.next())
            {
                //get string from db
                data2.add(new UserScreen.ShowAllBooks(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_name.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        category_name.setCellValueFactory(new PropertyValueFactory<>("category_name"));
        book_language.setCellValueFactory(new PropertyValueFactory<>("book_language"));


        AllBooks.setItems(null);
        AllBooks.setItems(data2);
    }

    public void RentBook(ActionEvent event)throws SQLException
    {
        Connection conn = dc.Connect();

        // Standart SQL Query
        //String sqlCount = "SELECT COUNT(book_title) AS NumOfBooks FROM rented_books WHERE book_title = '"+txtBookTitle.getText()+"'";
        //Statement statementCount = conn.createStatement();
        //ResultSet rsCount = statementCount.executeQuery(sqlCount);

        // Stored Procedure for counting rented books
        String queryCount="{call CountRentedBooks(?,?)}";

        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        CallableStatement stmt=connection.prepareCall(queryCount);
        stmt.setString(1,lblBookTitle.getText());
        stmt.setString(2,txtUserid.getText());

        ResultSet rsCount=stmt.executeQuery();


        while(rsCount.next())
        {
            int counter = rsCount.getInt(1);
            int counter2 = rsCount.getInt(1);

            if(counter < 1)
            {
                String sql = "INSERT INTO rented_books VALUES('"+txtUserid.getText()+"','"+lblUsernameFromUserScreen.getText()+"','"+lblBookID.getText()+"','"+lblBookTitle.getText()+"')";
                Statement statement = conn.createStatement();
                statement.execute(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("You have successfully rented book!");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText("Error occurred!");
                alert.setContentText("You already have that book!");

                alert.showAndWait();
            }
        }
    }

    public void SearchBook(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from book_details where book_title like concat('%', + '"+txtSearchBook.getText()+ "', + '%')");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.ShowAllBooks(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_name.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        category_name.setCellValueFactory(new PropertyValueFactory<>("category_name"));
        book_language.setCellValueFactory(new PropertyValueFactory<>("book_language"));



        AllBooks.setItems(null);
        AllBooks.setItems(data);
    }

}