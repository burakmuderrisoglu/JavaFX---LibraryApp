package Admin;

import Conn.DBConnection;
import UserScreen.BookDetails;
import UserScreen.RentedBooksModel;
import UserScreen.ShowAllBooks;
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

import java.awt.print.Book;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class Admin_Book_Details_Controller {
    @FXML
    private TableColumn<BookDetails,String> book_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_title;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> author_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> category_id;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_language;
    @FXML
    private TableColumn<UserScreen.BookDetails,String> book_quantity;
    @FXML
    private TableView<BookDetails> BookDetailsTable;

    @FXML
    public Label lblBookTitle;
    @FXML
    public Label lbl_Authorid;
    @FXML
    public Label lbl_Categoryid;
    @FXML
    private TableColumn<Authors,String> author_id_author;
    @FXML
    private TableColumn<Admin.Authors,String> author_name_author;
    @FXML
    private TableView<Authors> Authors_Table;
    @FXML
    private TableColumn<Categories,String> category_id_categories;
    @FXML
    private TableColumn<Admin.Categories,String> category_name_categories;
    @FXML
    private TableView<Categories> Categories_Table;

    public TextField bookid;
    public TextField booktitle;
    public TextField authorid;
    public TextField categoryid;
    public TextField language;
    public TextField quantity;

    public TextField txtauthor_id;
    public TextField txtauthor_name;
    public TextField txtcategory_id;
    public TextField txtcategory_name;
    public TextField txtSearchBook;
    public TextField txtSearchAuthors;
    public TextField txtSearchCategory;

    Stage stage = new Stage();

    private DBConnection dc;
    public ObservableList<BookDetails> data;
    public ObservableList<Authors> data_authors;
    public ObservableList<Categories> data_categories;

    public void GoToAdminScreen(ActionEvent event) throws IOException
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent p1 = FXMLLoader.load(getClass().getResource("../Admin/Admin_Screen.fxml"));
        Scene scnUserLogin = new Scene(p1);

        stage.setTitle("Admin Screen");
        stage.setScene(scnUserLogin);
        stage.show();
    }
    public void ShowBookDetails(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from book_details ");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.BookDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_id.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        category_id .setCellValueFactory(new PropertyValueFactory<>("category_id"));
        book_language.setCellValueFactory(new PropertyValueFactory<>("book_language"));



        BookDetailsTable.setItems(null);
        BookDetailsTable.setItems(data);

    }
    public void displaySelected(MouseEvent event)
    {
        lblBookTitle.setVisible(false);

        BookDetails getRow = BookDetailsTable.getSelectionModel().getSelectedItem();
        String book_title = getRow.getbook_title();
        lblBookTitle.setText(book_title);
    }
    public void displaySelected2(MouseEvent event)
    {
        lbl_Authorid.setVisible(false);

        Authors getRow = Authors_Table.getSelectionModel().getSelectedItem();
        String author_id = getRow.getAuthor_id();
        lbl_Authorid.setText(author_id);
    }
    public void displaySelected3(MouseEvent event)
    {
        lbl_Categoryid.setVisible(false);

        Categories getRow = Categories_Table.getSelectionModel().getSelectedItem();
        String category_id = getRow.getcategory_id();
        lbl_Categoryid.setText(category_id);
    }
    public void DeleteSelected(ActionEvent event)throws SQLException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String sql = "DELETE FROM book_details WHERE book_title='"+lblBookTitle.getText()+"'";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("You have successfully deleted a book!");

        alert.showAndWait();

    }
    public void DeleteSelectedAuthor(ActionEvent event)throws SQLException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String sql = "DELETE FROM authors WHERE author_id='"+lbl_Authorid.getText()+"'";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("You have successfully deleted an author!");

        alert.showAndWait();

    }
    public void DeleteSelectedCategory(ActionEvent event)throws SQLException
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String sql = "DELETE FROM categories WHERE category_id='"+lbl_Categoryid.getText()+"'";
        Statement statement = connection.createStatement();
        statement.execute(sql);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("You have successfully deleted a category!");

        alert.showAndWait();

    }
    public void InsertBook(ActionEvent event)throws SQLException
    {
        Connection conn = dc.Connect();
        Random rand = new Random();
        int id = rand.nextInt(999);


        if(booktitle.getText().isEmpty() || authorid.getText().isEmpty() || categoryid.getText().isEmpty() || language.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter all necessary information!");

            alert.showAndWait();
        }
        else
        {
            String sql = "INSERT INTO book_details VALUES('"+id+"','"+booktitle.getText()+"','"+authorid.getText()+"','"+categoryid.getText()+"','"+language.getText()+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You have successfully added a book!");

            alert.showAndWait();
        }
    }

    public void InsertAuthor(ActionEvent event)throws SQLException
    {
        Connection conn = dc.Connect();
        String autID="AUT";

        Random rand = new Random();
        int id = rand.nextInt(999);

        String id2=String.valueOf(id);
        autID=autID+id2;


        if(txtauthor_name.getText().isEmpty() )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter author name!");

            alert.showAndWait();
        }
        else
        {
            String sql = "INSERT INTO authors VALUES('"+autID+"','"+txtauthor_name.getText()+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You have successfully added an author!");
            alert.showAndWait();
        }
    }

    public void InsertCategory(ActionEvent event)throws SQLException
    {
        Connection conn = dc.Connect();

        String catID="CAT";

        Random rand = new Random();
        int id = rand.nextInt(999);

        String id2=String.valueOf(id);
        catID=catID+id2;

        if(txtcategory_name.getText().isEmpty() )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Error occurred!");
            alert.setContentText("Please enter a category name!");

            alert.showAndWait();
        }
        else
        {
            String sql = "INSERT INTO categories VALUES('"+catID+"','"+txtcategory_name.getText()+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("You have successfully added a category!");
            alert.showAndWait();
        }
    }


    public void ShowAuthors(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data_authors = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from ShowAllAuthors ");

            while(resultSet.next())
            {
                //get string from db
                data_authors.add(new Admin.Authors(resultSet.getString(1),resultSet.getString(2)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        author_id_author.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        author_name_author.setCellValueFactory(new PropertyValueFactory<>("author_name"));



        Authors_Table.setItems(null);
        Authors_Table.setItems(data_authors);

    }
    public void ShowCategories(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();


        try {
            data_categories = FXCollections.observableArrayList();
            // Execute query
            ResultSet resultSet = connection.createStatement().executeQuery("select * from ShowAllCategories ");

            while(resultSet.next())
            {
                //get string from db
                data_categories.add(new Admin.Categories(resultSet.getString(1),resultSet.getString(2)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        category_id_categories.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        category_name_categories.setCellValueFactory(new PropertyValueFactory<>("category_name"));



        Categories_Table.setItems(null);
        Categories_Table.setItems(data_categories);

    }


    public void SearchBook(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String query = "{call GetBookByName(?)}";
        ResultSet resultSet;

        try {
            data = FXCollections.observableArrayList();

            CallableStatement stmt=connection.prepareCall(query);
            stmt.setString(1,txtSearchBook.getText());
            resultSet = stmt.executeQuery();

            // Execute query (Standard select query)
            //ResultSet resultSet = connection.createStatement().executeQuery("select * from book_details where book_title='"+txtSearchBook.getText()+"'");

            while(resultSet.next())
            {
                //get string from db
                data.add(new UserScreen.BookDetails(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        book_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_title.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_id.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        category_id .setCellValueFactory(new PropertyValueFactory<>("category_id"));
        book_language.setCellValueFactory(new PropertyValueFactory<>("book_language"));



        BookDetailsTable.setItems(null);
        BookDetailsTable.setItems(data);
    }
    public void SearchAuthors(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String query = "{call GetAuthorByName(?)}";
        ResultSet resultSet;

        try {
            data_authors = FXCollections.observableArrayList();

            CallableStatement stmt=connection.prepareCall(query);
            stmt.setString(1,txtSearchAuthors.getText());
            resultSet = stmt.executeQuery();

            // Execute query (Standard select query)
            //ResultSet resultSet = connection.createStatement().executeQuery("select * from authors where author_name='"+txtSearchAuthors.getText()+"'");

            while(resultSet.next())
            {
                //get string from db
                data_authors.add(new Admin.Authors(resultSet.getString(1),resultSet.getString(2)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        author_id_author.setCellValueFactory(new PropertyValueFactory<>("author_id"));
        author_name_author.setCellValueFactory(new PropertyValueFactory<>("author_name"));



        Authors_Table.setItems(null);
        Authors_Table.setItems(data_authors);

    }

    public void SearchCategories(ActionEvent event)
    {
        Conn.DBConnection connectionClass = new Conn.DBConnection();
        Connection connection = connectionClass.Connect();

        String query = "{call GetCategoryByName(?)}";
        ResultSet resultSet;

        try {
            data_categories = FXCollections.observableArrayList();

            CallableStatement stmt=connection.prepareCall(query);
            stmt.setString(1,txtSearchCategory.getText());
            resultSet = stmt.executeQuery();

            // Execute query(Standard select query)
            //ResultSet resultSet = connection.createStatement().executeQuery("select * from categories where category_name='"+txtSearchCategory.getText()+"'");

            while(resultSet.next())
            {
                //get string from db
                data_categories.add(new Admin.Categories(resultSet.getString(1),resultSet.getString(2)));

            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        // Set cell value factory to Table view
        // NB.PropertyValue Factory must be same with the one set in model class.
        category_id_categories.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        category_name_categories.setCellValueFactory(new PropertyValueFactory<>("category_name"));



        Categories_Table.setItems(null);
        Categories_Table.setItems(data_categories);

    }


}