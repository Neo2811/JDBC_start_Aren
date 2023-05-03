package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.repo.AccountRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotEnoughMoneyException;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountRepo accountRepo = new AccountRepo();

    public void transfer(int from, int to, int amount){
        accountRepo.transfer(from,to,amount);
    }
    public void transferWithPhoneNumber(int phoneNumber, int from, int amount) throws NotFoundException, InternalServerError, NotEnoughMoneyException {
       try {
           ResultSet resultSetFrom = accountRepo.checkFrom(from);
           ResultSet resultSetTo = accountRepo.checkTo(phoneNumber);
           if (!resultSetFrom.next()){
               throw new NotFoundException("There is no any user with that user_id");
           }else if (resultSetFrom.getInt("balance") < amount || resultSetFrom.getInt("balance") <= 0){
               throw new NotEnoughMoneyException("You don't have enough money");
           }else if (!resultSetTo.next()){
               throw new NotFoundException("There is no any user with that user_id");
           }else if (amount <= 0){
               throw new NotEnoughMoneyException("The amount can't be less than 1");
           }else accountRepo.transferWithPhoneNumber(phoneNumber, from, amount);
       }catch (RuntimeException | SQLException e){
           throw new InternalServerError("Something went wrong");
       }
    }
    public void createAccount(Account account) throws InternalServerError {
        int row = accountRepo.createAccount(account);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public Account getById(int id) throws NotFoundException, InternalServerError {
        try {
        Account account = accountRepo.getById(id);
        if (account.getId() == 0){
            throw new NotFoundException("There is no any account with that id");
        }
        else return account;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<Account> getAll() throws NotFoundException, InternalServerError {
        try {
            List<Account> accounts = accountRepo.getAll();
            if (accounts.isEmpty()){
                throw new NotFoundException("There is empty");
            }
            else return accounts;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void updateAccount(String password, int balance, int userId) throws InternalServerError {
            int row = accountRepo.updateAccount(password, balance, userId);
            if (row == 0){
                throw new InternalServerError("Something went wrong");
            }
    }
    public void deleteAccount(int id) throws InternalServerError {
        int row = accountRepo.deleteAccount(id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
}
