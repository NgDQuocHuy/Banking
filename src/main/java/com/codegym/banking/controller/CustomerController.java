package com.codegym.banking.controller;

import com.codegym.banking.model.Customer;
import com.codegym.banking.model.Deposit;
import com.codegym.banking.model.Withdraw;
import com.codegym.banking.service.customer.ICustomerService;
import com.codegym.banking.service.deposit.IDepositService;
import com.codegym.banking.service.withdraw.IWithDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDepositService depositService;

    @Autowired
    private IWithDrawService withDrawService;

    @GetMapping
    public ModelAndView showListPage() {
        Iterable<Customer> customers = customerService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

//    CREATE CUSTOMER
    @GetMapping("/create")
    public ModelAndView showCreateCustomer() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        try {
            customer.setBalance(new BigDecimal(0L));
            customer.setDeleted(false);

            customerService.save(customer);

            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

//    SUSPENSION CUSTOMER
    @GetMapping("/suspension/{id}")
    public ModelAndView showRemoveCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/suspension");

        Optional<Customer> customer = customerService.findById(id);

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            modelAndView.addObject("message", "No");
            return modelAndView;
        }
    }

    @PostMapping("/suspension/{id}")
    public ModelAndView removeCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/suspension");

        customer.setDeleted(true);
        customerService.save(customer);

        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Deleted Successfully");
        return modelAndView;
    }

//    UPDATE CUSTOMER
    @GetMapping("/update/{id}")
    public ModelAndView showUpdateCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            modelAndView.addObject("message", "No");
            return modelAndView;
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/update");

        try {
            customerService.save(customer);

            modelAndView.addObject("customer", customer);
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

//    DEPOSIT MONNEY
    @GetMapping("/deposit/{id}")
    public ModelAndView showDeposit(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            modelAndView.addObject("deposit", new Deposit());
            return modelAndView;
        } else {
            modelAndView.addObject("message", "No");
            return modelAndView;
        }
    }

    @PostMapping("/deposit/{id}")
    public ModelAndView deposit(@PathVariable("id") Long id, @ModelAttribute Deposit deposit) {
        Optional<Customer> customer = customerService.findById(id);
        Customer newCustomer = customer.get();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        BigDecimal beforeBalance = newCustomer.getBalance();
        BigDecimal transactionAmount  = deposit.getTransactionAmount();
        BigDecimal newBalance = beforeBalance.add(transactionAmount);

        deposit.setCustomer(newCustomer);
        newCustomer.setBalance(newBalance);

        depositService.save(deposit);
        customerService.save(newCustomer);

        modelAndView.addObject( "customer", newCustomer );
        modelAndView.addObject( "deposit", new Deposit() );
        modelAndView.addObject( "message", "Deposit successfully" );

        return modelAndView;
    }

//    WITHDRAW MONNEY
    @GetMapping("/withdraw/{id}")
    public ModelAndView showWithdraw(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");

        if (customer.isPresent()) {
            modelAndView.addObject("customer", customer.get());
            modelAndView.addObject("withdraw", new Withdraw());
            return modelAndView;
        } else {
            modelAndView.addObject("message", "No");
            return modelAndView;
        }
    }

    @PostMapping("/withdraw/{id}")
    public ModelAndView deposit(@PathVariable("id") Long id, @ModelAttribute Withdraw withdraw) {
        Optional<Customer> customer = customerService.findById(id);
        Customer newCustomer = customer.get();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/withdraw");

        BigDecimal beforeBalance = newCustomer.getBalance();
        BigDecimal transactionAmount  = withdraw.getTransactionAmount();
        if (transactionAmount.compareTo(new BigDecimal(1000)) >= 0) {
            if (beforeBalance.compareTo(transactionAmount) >= 0) {
                BigDecimal newBalance = beforeBalance.subtract(transactionAmount);

                withdraw.setCustomer(newCustomer);
                newCustomer.setBalance(newBalance);

                withDrawService.save(withdraw);
                customerService.save(newCustomer);

                modelAndView.addObject("customer", newCustomer);
                modelAndView.addObject("withdraw", new Withdraw());
                modelAndView.addObject("message", "Deposit successfully");
            }
        }
        modelAndView.addObject("customer", newCustomer);
        modelAndView.addObject("withdraw", new Withdraw());
        modelAndView.addObject("message", "No");

        return modelAndView;
    }
}
