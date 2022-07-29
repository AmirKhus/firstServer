package com.example.demo.repository;

import com.example.demo.entity.Buyer;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class BuyerRepository implements IRestRepository<Buyer>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\" " +
            "FROM \"buyer\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\" " +
            "FROM \"buyer\" " +
            "WHERE \"id\" = ?";

    private static String selectByRegionIdQuery = "SELECT \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\" " +
            "FROM \"buyer\" " +
            "WHERE \"region_id\" = ?";

    private static String insertQuery = "INSERT INTO \"buyer\"( \"first_name\", \"surname\", \"mail\", \"region_id\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\"";

    private static String updateQuery = "UPDATE \"buyer\" " +
            "SET \"first_name\" = ?, \"surname\" = ?, \"mail\" = ?, \"region_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\"";

    private static String deleteQuery = "DELETE FROM \"buyer\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"first_name\", \"surname\", \"mail\", \"region_id\"";
    public BuyerRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Buyer[] select() {
        ArrayList<Buyer> values = new ArrayList<Buyer>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Buyer(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Buyer[] result = new Buyer[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Buyer select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Buyer(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    public Buyer[] selectByRegionId(Integer region_id) {
        ArrayList<Buyer> values = new ArrayList<Buyer>();
        Object[] params = new Object[]{region_id};
        int[] types = new int[]{Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByRegionIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Buyer(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getInt(5)
            ));
        }
        Buyer[] result = new Buyer[values.size()];
        result = values.toArray(result);
        return result;
    }
    
//    @Override
    public Buyer insert(Buyer entity) {
        Object[] params = new Object[] { entity.getFirst_name(), entity.getSurname(), entity.getMail(),entity.getRegion_id() };
        int[] types = new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Buyer(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Buyer update(Integer id, Buyer entity) {
        Object[] params = new Object[] { entity.getFirst_name(), entity.getSurname(), entity.getMail(),entity.getRegion_id(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Buyer(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public Buyer delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Buyer(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5)
        );
    }

}
