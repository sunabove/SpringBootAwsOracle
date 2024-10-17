package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired  private CustomerRepository customerRepository;

    @GetMapping("/")
    @Transactional
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name );

        List<Customer> customers = customerRepository.findAll();

        if( customers.isEmpty() ) {
            // 데이터가 없으면, 샘플 데이터를 입력합니다.
            Customer customer = new Customer( "John", "Doe" );
            customerRepository.save( customer );

            customers = customerRepository.findAll();
        }

        for( Customer customer : customers ) {
            System.out.println( "Customer = " + customer );
        }

        return "index";
    }

}