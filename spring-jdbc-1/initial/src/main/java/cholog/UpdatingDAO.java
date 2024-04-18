package cholog;

import java.sql.PreparedStatement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UpdatingDAO {
    private final JdbcTemplate jdbcTemplate;

    public UpdatingDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public void insert(final Customer customer) {
        final String query = "insert into customers (first_name, last_name) values (?, ?)";
        jdbcTemplate.update(query, customer.getFirstName(), customer.getLastName());
    }

    /**
     * public int update(String sql, @Nullable Object... args)
     */
    public int delete(final Long id) {
        final String query = "delete from customers where id = ?";
        return jdbcTemplate.update(query, id);
    }

    /**
     * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
     */
    public Long insertWithKeyHolder(final Customer customer) {
        final String query = "insert into customers (first_name, last_name) values (?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    query, new String[]{"id"});
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
