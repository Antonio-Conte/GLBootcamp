package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.CustomerDTO;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDTO, Integer>{

}
