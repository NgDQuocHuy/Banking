package com.codegym.banking.service.withdraw;

import com.codegym.banking.model.Withdraw;
import com.codegym.banking.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class WithdrawServiceImpl implements IWithDrawService{
    @Autowired
    private WithdrawRepository withdrawRepository;
    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Withdraw getById(Long id) {
        return withdrawRepository.getById(id);
    }

    @Override
    public Optional<Withdraw> findById(Long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return withdrawRepository.save(withdraw);
    }

    @Override
    public void remove(Long id) {
        withdrawRepository.deleteById(id);
    }
}
