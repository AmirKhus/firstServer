package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class OrderRepository implements IRestRepository<Order>{
    protected final JdbcOperations jdbcOperations;
//    Object.has

    private static String selectQuery = "SELECT \"id\", \"buyer_id\", \"game_id\", \"date_order\" " +
            "FROM \"order\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"buyer_id\", \"game_id\", \"date_order\" " +
            "FROM \"order\" " +
            "WHERE \"id\" = ?";

    private static String selectByBuyerIdQuery = "SELECT \"id\", \"buyer_id\", \"game_id\", \"date_order\" " +
            "FROM \"order\" " +
            "WHERE \"buyer_id\" = ?";

    private static String insertQuery = "INSERT INTO \"order\"(\"buyer_id\", \"game_id\", \"date_order\") " +
            "VALUES (?, ?, ?) " +
            "RETURNING \"id\", \"buyer_id\", \"game_id\", \"date_order\"";

    private static String updateQuery = "UPDATE \"order\" " +
            "SET \"buyer_id\" = ?, \"game_id\" = ?, \"date_order\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"buyer_id\", \"game_id\", \"date_order\"";

    private static String deleteQuery = "DELETE FROM \"order\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"buyer_id\", \"game_id\", \"date_order\"";

    public OrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Order[] select() {
        Object o = new Object();
        o.hashCode();
        ArrayList<Order> values = new ArrayList<Order>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Order(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getTimestamp(4)
            ));
        }
        Order[] result = new Order[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Order select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getTimestamp(4)
        );
    }




//    @Override
    public Order insert(Order entity) {
        Object[] params = new Object[] { entity.getBuyer_id(), entity.getGame_id(), entity.getDate_order() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getTimestamp(4)
        );
    }

    @Override
    public Order update(Integer id, Order entity) {
        Object[] params = new Object[] { entity.getBuyer_id(), entity.getGame_id(), entity.getDate_order(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getTimestamp(4)
        );
    }

    @Override
    public Order delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getTimestamp(4)
        );
    }
}
