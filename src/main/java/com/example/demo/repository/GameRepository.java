package com.example.demo.repository;

import com.example.demo.entity.Game;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class GameRepository implements IRestRepository<Game>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"  " +
            "FROM \"game\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"   " +
            "FROM \"game\" " +
            "WHERE \"id\" = ?";


    private static String selectByPublisherId = "SELECT \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"   " +
            "FROM \"game\" " +
            "WHERE \"publisher_id\" = ? ";

    private static String selectByName = "SELECT \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"   " +
            "FROM \"game\" " +
            "WHERE \"name\" LIKE ? ";

    private static String insertQuery = "INSERT INTO \"game\"(\"name\",\"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"  ) " +
            "VALUES (?, ?, ?, ?, ?, ?) " +
            "RETURNING \"id\",\"name\",\"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\" ";

    private static String updateQuery = "UPDATE \"game\" " +
            "SET \"name\" = ?, \"developer_id\" = ?, \"publisher_id\" = ?, \"genre_id\" = ?, \"price\" = ?, \"date_of_publication\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"  ";

    private static String deleteQuery = "DELETE FROM \"game\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\", \"developer_id\", \"publisher_id\", \"genre_id\" , \"price\", \"date_of_publication\"  ";

    public GameRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Game[] select() {
        ArrayList<Game> values = new ArrayList<Game>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Game(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5),
                    rowSet.getFloat(6),
                    rowSet.getTimestamp(7)
            ));
        }
        Game[] result = new Game[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Game select(Integer id) {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Game(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getFloat(6),
                rowSet.getTimestamp(7)
        );
    }

//    @Override
    public Game insert(Game entity) {
        Object[] params = new Object[] { entity.getName(), entity.getDeveloper_id(), entity.getPublisher_id(),
                entity.getGenre_id(), entity.getPrice(), entity.getDate_of_publication()};
        int[] types = new int[] { Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.FLOAT,Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Game(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getFloat(6),
                rowSet.getTimestamp(7)
        );
    }



    public Game[] selectByPublisherId(Integer publisherId) {
        ArrayList<Game> values = new ArrayList<Game>();
        Object[] params = new Object[]{publisherId};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByPublisherId, params, types);
        while (rowSet.next()) {
            values.add(new Game(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5),
                    rowSet.getFloat(6),
                    rowSet.getTimestamp(7)
            ));
        }
        Game[] result = new Game[values.size()];
        result = values.toArray(result);
        return result;
    }

    public Game[] selectByName(String name) {
        ArrayList<Game> values = new ArrayList<Game>();
        Object[] params = new Object[]{name};
        int[] types = new int[]{Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new Game(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5),
                    rowSet.getFloat(6),
                    rowSet.getTimestamp(7)
            ));
        }
        Game[] result = new Game[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Game update(Integer id, Game entity) {
        Object[] params = new Object[] { entity.getName(), entity.getDeveloper_id(), entity.getPublisher_id(),
                entity.getGenre_id(), entity.getPrice(), entity.getDate_of_publication(), id };
        int[] types = new int[] { Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.FLOAT,Types.TIMESTAMP,Types.INTEGER  };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Game(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getFloat(6),
                rowSet.getTimestamp(7)
        );
    }

    @Override
    public Game delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Game(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getFloat(6),
                rowSet.getTimestamp(7)
        );
    }
}
