package com.vois.e_wallet.services;

import com.vois.e_wallet.dto.*;
import com.vois.e_wallet.entities.Transaction;
import com.vois.e_wallet.entities.User;
import com.vois.e_wallet.entities.Wallet;

import com.vois.e_wallet.enums.TransactionType;
import com.vois.e_wallet.repositories.TransactionRepository;


import com.vois.e_wallet.repositories.UserRepository;
import com.vois.e_wallet.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TransactionService extends GenericServiceImpl<TransactionDTO,String,Transaction> {

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    TransactionService(TransactionRepository tr, WalletService ws)
    {
        super(tr);
        this.transactionRepository = tr;
        this.walletService = ws;
    }


    public TransactionDTO makeTransaction(TransactionAction t)
    {

        Wallet toWallet = null;
        Wallet  fromWallet = null;

        switch(t.getType())
        {
            case DEPOSIT:
            {
               toWallet = walletService.deposit(t);
                break;
            }
            case WITHDRAWAL:
            {
             fromWallet = walletService.withdraw(t);
                break;

            }
            case TRANSFER:{
               toWallet =walletService. deposit(t);
               fromWallet =walletService. withdraw(t);

            }
        }

        Transaction transaction = Transaction.builder()
                .toWallet(toWallet)
                .fromWallet(fromWallet)
                .type(t.getType())
                .amount(t.getAmount())
                .timestamp(LocalDateTime.now())
                .build();


        Transaction savedT = transactionRepository.save(transaction);

        return new TransactionDTO(savedT);

    }

    @Override
    protected TransactionDTO convertToDTO(Transaction entity) {
        return new TransactionDTO(entity);
    }

}





