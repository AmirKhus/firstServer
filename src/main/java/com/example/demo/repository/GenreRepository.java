package com.example.demo.repository;

import com.example.demo.entity.Genre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class GenreRepository  implements IRestRepository<Genre> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\" " +
            "FROM \"genre\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\" " +
            "FROM \"genre\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"genre\"(\"name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"name\" ";

    private static String updateQuery = "UPDATE \"genre\" " +
            "SET \"name\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"genre\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\" ";

    public GenreRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Genre[] select() {
        ArrayList<Genre> values = new ArrayList<Genre>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Genre(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Genre[] result = new Genre[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Genre select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Genre(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

//    @Override
    public Genre insert(Genre entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Genre(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Genre update(Integer id, Genre entity) {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Genre(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Genre delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Genre(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
