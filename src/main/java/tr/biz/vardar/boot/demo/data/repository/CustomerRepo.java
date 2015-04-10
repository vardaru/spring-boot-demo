/**
 * Copyright 2015 Vardar Ltd
 * 
 */
package tr.biz.vardar.boot.demo.data.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import tr.biz.vardar.boot.demo.data.Customer;

/**
 * @author umitvardar
 *
 */
public interface CustomerRepo extends CrudRepository<Customer, Long> {

	Set<Customer> findByLastName(String lastName);
	Set<Customer> findByFirstNameAndLastName(String firstname, String lastName);
}
