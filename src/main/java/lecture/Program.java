package lecture;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Program {


    /*
     * DAO - business logic
     */
    private final BoardgameDao bgDao;

    public Program(BoardgameDao bgDao) {
        this.bgDao = bgDao;
    }


    void useOptional(BoardgameDao bgDao, int i) throws SQLException {
        Optional<BoardGame> bg = bgDao.getBoardGame(i);
        bg.ifPresent(boardGame -> IO.println(boardGame.name()));
    }

    void useStoredProcedure(BoardgameDao bgDao) throws SQLException {
        IO.println("Trying to add new board game using callable statement");
        int generatedId = bgDao.addBoardGameUsingStoredProcedure("Citadels", "Strategi", 7, 120, 10, "citadels.jpg");
        IO.println("Result:"+generatedId);
        useOptional(bgDao, 5);
    }




    public void run() throws SQLException {
        // Optional
        useOptional(bgDao, 5);

        // CallAble
        useStoredProcedure(bgDao);

    }

}
