package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

    static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/webmobile2");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //SELECT
    public static ResultSet query(String query, String... args) throws SQLException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DBUtils.dataSource.getConnection();
//            System.out.println(connection);
            statement = connection.prepareStatement(query);

            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }

            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //INSERT, UPDATE, DELETE
   public static int intqueries(String query, String... args) throws SQLException {
        int result;

        try (Connection connection = DBUtils.dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }
            result = statement.executeUpdate();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}


