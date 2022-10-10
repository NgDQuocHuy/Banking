package com.codegym.banking.service.deposit;

import com.codegym.banking.model.Deposit;
import com.codegym.banking.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DepositServiceImpl implements IDepositService{
    @Autowired
    private DepositRepository depositRepository;
    @Override
    public Iterable<Deposit> findAll() {
        return depositRepository.findAll();
    }

    @Override
    public Deposit getById(Long id) {
        return depositRepository.getById(id);
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void remove(Long id) {
        depositRepository.deleteById(id);
    }
}
