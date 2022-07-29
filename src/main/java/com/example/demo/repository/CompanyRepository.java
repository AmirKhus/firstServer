package com.example.demo.repository;

import com.example.demo.entity.Company;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class CompanyRepository implements IRestRepository<Company> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\" " +
            "FROM \"company\" " +
            "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\" " +
            "FROM \"company\" " +
            "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"company\"(\"name\") " +
            "VALUES (?) " +
            "RETURNING \"id\", \"name\" ";

    private static String updateQuery = "UPDATE \"company\" " +
            "SET \"name\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"company\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"name\" ";

    public CompanyRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public Company[] select() {
        ArrayList<Company> values = new ArrayList<Company>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Company(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Company[] result = new Company[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Company select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Company(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

//    @Override
    public Company insert(Company entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Company(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Company update(Integer id, Company entity) {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Company(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Company delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Company(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
