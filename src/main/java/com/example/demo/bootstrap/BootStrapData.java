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
        if (customerRepository.count() > 1) {
            return;
        }

        Division division1 = divisionRepository.findById(12L).orElse(null);
        if (division1 != null) {
            Customer customer1 = new Customer(division1, "Joey", "Dixon", "1234 Back Street", "123456", "123-456-7890");
            Customer customer2 = new Customer(division1, "Jimmy", "Davis", "2345 Back Street", "234567", "123-456-7891");
            Customer customer3 = new Customer(division1, "Jenny", "Dunne", "3456 Back Street", "345678", "123-456-7892");
            Customer customer4 = new Customer(division1, "Josh", "Doyle", "4567 Back Street", "456789", "123-456-7893");
            Customer customer5 = new Customer(division1, "Jax", "Douglas", "5678 Back Street", "567890", "123-456-7894");

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);
        }
    }

}
