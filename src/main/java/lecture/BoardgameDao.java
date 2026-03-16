package lecture;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BoardgameDao {
    /*
    * DAO- design pattern
    * -------------------
    * Data Access Object (DAO) design pattern is a crucial pattern used to separate data persistence logic from business logic.
    * DAO defines standard CRUD (Create, Read, Update, Delete) methods and encapsulates them in a separate class, making code easier to read and maintain.
    *
     */
    private final Connection conn;
    private static final String GET_BOARDGAME_SQL = "SELECT brettspill_id, navn, type, antall_spillere, spilletid, aldersgrense, bilde FROM Brettspill WHERE brettspill_id=?";
    private static final String ADD_BOARDGAME_NO_ID_SQL = "INSERT INTO Brettspill (navn, type, antall_spillere, spilletid, aldersgrense, bilde) VALUES(?,?,?,?,?,?)";

    public BoardgameDao(Connection conn) {
        this.conn = conn;
    }


    // Optional
    public Optional<BoardGame> getBoardGame(int boardGameId) throws SQLException {
        try (
             PreparedStatement statement = conn.prepareStatement(GET_BOARDGAME_SQL);
        ) {
            statement.setInt(1, boardGameId);
            try (ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    String name = rs.getString("navn");
                    String type = rs.getString("type");
                    int nrOfPlayers = rs.getInt("antall_spillere");
                    int minutes = rs.getInt("spilletid");
                    int ageLimit = rs.getInt("aldersgrense");
                    String imageUrl = rs.getString("bilde");
                    return Optional.of(new BoardGame(name, type, nrOfPlayers, minutes, ageLimit, imageUrl));
                }
            }
            // No board game found...
            return Optional.empty();
        }
    }


    // auto-inkrement
    public int addBoardGame(String name, String type, int nrOfPlayers, int minutes, int ageLimit, String imageUrl) throws SQLException {
        try (
             PreparedStatement statement = conn.prepareStatement(ADD_BOARDGAME_NO_ID_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, name);
            statement.setString(2, type);
            statement.setInt(3, nrOfPlayers);
            statement.setInt(4, minutes);
            statement.setInt(5, ageLimit);
            statement.setString(6, imageUrl);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated==1){
                try(ResultSet keys = statement.getGeneratedKeys()){
                    if(keys.next()){
                        return keys.getInt(1);
                    }
                }
            }
        }
        return 0;
    }

    // transaction
    public void addBoardGames(List<BoardGame> newBoardGames) throws SQLException {
        try (
             PreparedStatement statement = conn.prepareStatement(ADD_BOARDGAME_NO_ID_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            try{
                for (BoardGame newBoardGame : newBoardGames) {
                    //statement.setInt(1, newBoardGame.id());
                    statement.setString(1, newBoardGame.name());
                    statement.setString(2, newBoardGame.type());
                    statement.setInt(3, newBoardGame.nrOfPlayers());
                    statement.setInt(4, newBoardGame.minutes());
                    statement.setInt(5, newBoardGame.ageLimit());
                    statement.setString(6, newBoardGame.imageUrl()); //demonstrate wrong index
                    statement.executeUpdate();
                }
                conn.commit();
            } catch (SQLException sqle){
                System.out.println("Exception caught. Rolling back transaction.");
                conn.rollback();
                throw sqle;
            } finally {
                // Setting the commit mode to what it was originally.
                conn.setAutoCommit(autoCommit);
            }

        }
    }




}
