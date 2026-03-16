package lecture;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static lecture.PropertiesProvider.PROPS;

public class DbUtils {

    public static Connection createDbConnection() throws SQLException {

        MysqlDataSource boardGameDS = new MysqlDataSource();
        boardGameDS.setServerName(PROPS.getProperty("host"));
        boardGameDS.setPortNumber(Integer.parseInt(PROPS.getProperty("port")));
        boardGameDS.setDatabaseName(PROPS.getProperty("db_name"));
        boardGameDS.setUser(PROPS.getProperty("uname"));
        boardGameDS.setPassword(PROPS.getProperty("pwd"));
        return boardGameDS.getConnection();
    }
}
