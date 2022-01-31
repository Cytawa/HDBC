package jdbc;

import util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Zadanie1 {
    public static void main(String[] args) throws SQLException {

        DatabaseConnection databaseConection=new DatabaseConnection();
        String query="select USR_FIRSTNAME FN, USR_LASTNAME LN from user where USR_ID=?";


        PreparedStatement preparedStatement=databaseConection
                .getConnection()
                .prepareStatement(query);

       preparedStatement.setString(FIRST_NAME_PARAMETER_INDEX, "3");
        //preparedStatement.setString(LAST_NAME_PARAMETER_INDEX, "Kowalski");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.print(resultSet.getString("FN")+" ");
            System.out.println(resultSet.getString("LN"));
        }


    }


    public static final int FIRST_NAME_PARAMETER_INDEX=1;
    public static final int LAST_NAME_PARAMETER_INDEX=2;


}

