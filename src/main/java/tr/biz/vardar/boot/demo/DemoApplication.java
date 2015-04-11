package tr.biz.vardar.boot.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.biz.vardar.boot.demo.data.Customer;
import tr.biz.vardar.boot.demo.data.repository.CustomerRepo;

@Controller
@RequestMapping("/api")
@EnableAutoConfiguration
@ComponentScan(basePackages= {"tr.biz.vardar.boot.demo"})
public class DemoApplication {
	
	@Autowired
	CustomerRepo customerRepo;

    public static void main(String[] args) {
    	SpringApplication application = new SpringApplication(DemoApplication.class);
    	application.setShowBanner(true);
    	application.run(args);
    }
    
    @RequestMapping("/msg")
    @ResponseBody
    public String hello() {
    	return "Hello World";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/customer")
    @ResponseBody 
    public List<Customer> allCustomers(){
    	return (List<Customer>) customerRepo.findAll();
    }
    
    @RequestMapping(method=RequestMethod.PUT, value="/customer/{name}/{surname}")
    @ResponseBody
    public HttpStatus createCustomer(@PathVariable final String name, @PathVariable final String surname) {
    	Customer aCustomer = new Customer(name, surname);
    	aCustomer = customerRepo.save(aCustomer);
    	return HttpStatus.ACCEPTED;
    }
}
