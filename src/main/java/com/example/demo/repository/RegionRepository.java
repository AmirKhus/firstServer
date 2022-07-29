package com.example.demo.repository;

import com.example.demo.entity.Region;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RegionRepository implements IRestRepository<Region> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\" " +
            "FROM \"region\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\" " +
            "FROM \"region\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"region\"(\"name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"name\" ";

    private static String updateQuery = "UPDATE \"region\" " +
            "SET \"name\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"region\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\" ";

    public RegionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Region[] select() {
        ArrayList<Region> values = new ArrayList<Region>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Region(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Region[] result = new Region[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Region select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Region(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

//    @Override
    public Region insert(Region entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Region(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Region update(Integer id, Region entity) {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Region(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Region delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Region(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
