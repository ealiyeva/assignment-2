package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/testdb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet select(String query, String... args) throws SQLException {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DB.dataSource.getConnection();
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

    public static int alter(String sql, String... args) throws SQLException {
        int result;

        try (Connection connection = DB.dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }
            System.out.println(statement);
            result = statement.executeUpdate();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
