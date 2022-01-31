package jdbc;

import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Przyklad2 {
    public static void main(String[] args) throws SQLException {
        String query="Select * from Address where add_id = ?";

        DatabaseConnection databaseConection=new DatabaseConnection();
        PreparedStatement preparedStatement=databaseConection
                .getConnection()
                .prepareStatement(query);

        preparedStatement.setString(1, "3");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(("ADD_Street")));
        }


    }





}
