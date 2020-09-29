package ar.com.gl.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.customer.model.CustomerDTO;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer>{

}
