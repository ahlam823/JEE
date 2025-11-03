package org.sid.bank_account_service.mappers;


import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {

    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {

        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;

    }
    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        BankAccount account = new BankAccount();
        BeanUtils.copyProperties(requestDTO, account);
        account.setId(UUID.randomUUID().toString());
        account.setCreatedAt(new Date());
        return account;
    }
}
