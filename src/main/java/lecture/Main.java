import lecture.BoardgameDao;
import lecture.DbUtils;
import lecture.Program;

import java.sql.Connection;
import java.sql.SQLException;


void main() {
    try (Connection conn = DbUtils.createDbConnection();
    ){

        BoardgameDao bgDao = new BoardgameDao(conn);
        Program program = new Program(bgDao);
        program.run();




    } catch(SQLException e){
        IO.println("Unable to connect to database:"+e.getMessage());
        e.printStackTrace();
    }
}