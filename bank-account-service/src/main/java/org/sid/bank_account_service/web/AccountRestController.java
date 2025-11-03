package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountService accountService;

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec l'id : " + id));

        if (bankAccount.getBalance() != 0) {
            account.setBalance(bankAccount.getBalance());
        }

        if (bankAccount.getCurrency() != null) {
            account.setCurrency(bankAccount.getCurrency());
        }

        if (bankAccount.getType() != null) {
            account.setType(bankAccount.getType());
        }

        account.setCreatedAt(new Date());

        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id) {
         bankAccountRepository.deleteById(id);
    }


}

