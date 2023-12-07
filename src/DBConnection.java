import Model.Category;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private String URL = "jdbc:mariadb://localhost:3306/northwind";

    private String USER = "root";
    private String password = "Passw0rd";

    Connection connection;

    public void open() {
        try {
            connection = DriverManager.getConnection(URL, USER, password);
            System.out.println("Database connected");

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String createTable() {
        String sql = "CREATE TABLE testtable (TestID INT NOT NULL AUTO_INCREMENT, TestName VARCHAR(100), TestAge VARCHAR(20), primary KEY(TestID))";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {

            System.out.println(e);

            return "Something went wrong";

        }
        return "Table created";
    }


    public ArrayList<Category> queryCategories() {

        String query = "SELECT * FROM categories";


        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);


            ArrayList<Category> categories = new ArrayList<>();

            while (results.next()) {
                Category category = new Category();
                category.setCategoryID(results.getInt("CategoryID"));
                category.setCategoryName(results.getString("CategoryName"));
                category.setDescription(results.getString("Description"));
                categories.add(category);
            }
            results.close();
            return categories;


        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            return null;

        }




    }

    public int updateCategory(String name, int id) {

        String sql = "UPDATE categories set CategoryName = ?  where CategoryID = ?";
        int affectedRows = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }


}
