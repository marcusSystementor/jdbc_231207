import Model.Category;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DBConnection dbConnection = new DBConnection();
        dbConnection.open();

        System.out.println(dbConnection.queryCategories().size());

        ArrayList<Category> categories = dbConnection.queryCategories();

        for (Category katt: categories) {
            System.out.println(katt.toString());
        }

    dbConnection.createTable();
    }

}
