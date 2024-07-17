package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Division division1 = divisionRepository.findById(2L).orElse(null);

        Customer customer1 = new Customer("Joey", "Dixon", "1234 Back Street", "123456", "123-456-7890", division1);
        Customer customer2 = new Customer("Jimmy", "Davis", "2345 Back Street", "234567", "123-456-7891", division1);
        Customer customer3 = new Customer("Jenny", "Dunne", "3456 Back Street", "345678", "123-456-7892", division1);
        Customer customer4 = new Customer("Josh", "Doyle", "4567 Back Street", "456789", "123-456-7893", division1);
        Customer customer5 = new Customer("Jax", "Douglas", "5678 Back Street", "567890", "123-456-7894", division1);

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        System.out.println("Started in bootstrap");
        System.out.println("Number of customers: " + customerRepository.count());

    }
}
