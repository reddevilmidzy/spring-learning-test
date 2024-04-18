package cholog;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QueryingDAO {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };

    public QueryingDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /*
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    public int count() {
        final String query = "select count(*) from customers";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    public String getLastName(final Long id) {
        final String query = "select last_name from customers where id = ?";
        return jdbcTemplate.queryForObject(query, String.class, id);
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public Customer findCustomerById(final Long id) {
        final String query = "select id, first_name, last_name from customers where id = ?";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
            final Customer customer = new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
            return customer;
        }, id);
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    public List<Customer> findAllCustomers() {
        final String query = "select id, first_name, last_name from customers";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            final Customer customer = new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
            return customer;
        });
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public List<Customer> findCustomerByFirstName(final String firstName) {
        final String query = "select id, first_name, last_name from customers where first_name = ?";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            final Customer customer = new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
            return customer;
        }, firstName);
    }
}
