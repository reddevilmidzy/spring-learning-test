package cholog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    List<Customer> findByLastNameOrderByFirstNameDesc(String lastName);
}
