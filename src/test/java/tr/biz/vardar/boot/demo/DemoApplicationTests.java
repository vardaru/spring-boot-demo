package tr.biz.vardar.boot.demo;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tr.biz.vardar.boot.demo.data.Customer;
import tr.biz.vardar.boot.demo.data.repository.CustomerRepo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {
	
	@Autowired
	CustomerRepo customerRepo;
	
	Customer customer;
	
	@After
	public void cleanRepo(){
		//customerRepo.deleteAll();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void insertCustomer() {
		customer = new Customer("Ümit", "Vardar");
		customer = customerRepo.save(customer);
		
		Iterable<Customer> customerList= customerRepo.findAll();
		for (Customer customer : customerList) {
			System.out.println(customer);
		}
	}

}
