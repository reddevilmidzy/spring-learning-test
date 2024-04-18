package cholog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class QueryingDaoTest {
    private QueryingDAO queryingDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        queryingDAO = new QueryingDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        findAllCustomers();
        final List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    @Test
    void count() {
        final int count = queryingDAO.count();

        assertThat(count).isEqualTo(4);
    }

    @Test
    void getLastName() {
        final String lastName = queryingDAO.getLastName(1L);

        assertThat(lastName).isEqualTo("Woo");
    }

    @Test
    void findCustomerById() {
        final Customer customer = queryingDAO.findCustomerById(1L);

        assertThat(customer).isNotNull();
        assertThat(customer.getLastName()).isEqualTo("Woo");
    }

    @Test
    void findAllCustomers() {
        final List<Customer> customers = queryingDAO.findAllCustomers();

        assertThat(customers).hasSize(4);
    }

    @Test
    void findCustomerByFirstName() {
        final List<Customer> customers = queryingDAO.findCustomerByFirstName("Josh");

        assertThat(customers).hasSize(2);
    }
}
