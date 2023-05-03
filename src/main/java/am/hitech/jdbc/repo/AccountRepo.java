package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.util.DataSource;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepo {
    Connection connection = DataSource.getConnection();


    public void transfer(int from, int to, int amount){
        String addBalance = "update `account` set balance = balance + ? where user_id = ?";
        String deductBalance = "update `account` set balance = balance - ? where user_id = ?";
        try{
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(addBalance);
            statement.setInt(1, amount);
            statement.setInt(2, to);
            statement.executeUpdate();

            statement = connection.prepareStatement(deductBalance);
            statement.setInt(1, amount);
            statement.setInt(2, from);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Account buildAccount(ResultSet resultSet){
        Account account = new Account();
        try {
            while (resultSet.next()){
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setBalance(resultSet.getInt("balance"));
                account.setUserId(resultSet.getInt("user_id"));
            }
            return account;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public int createAccount(Account account){
        String query = "insert into `account` values(0, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setInt(3, account.getBalance());
            statement.setInt(4, account.getUserId());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Account getById(int id){
        String query = "select * from `account` where id = ?";
        Account account = new Account();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            account = buildAccount(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return account;
    }
    public List<Account> getAll(){
        String query = "select * from `account`";
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setBalance(resultSet.getInt("balance"));
                account.setUserId(resultSet.getInt("user_id"));
                accounts.add(account);
            }
            return accounts;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int updateAccount(String password, int balance, int userId){
        String query = "update `account` set password = ?, balance = ? where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, password);
            statement.setInt(2, balance);
            statement.setInt(3, userId);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int deleteAccount(int id){
        String query = "delete from `account` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void transferWithPhoneNumber(int phoneNumber, int from, int amount) throws NotFoundException {
        String deductBalance = "update `account` set `balance` = balance - ? where user_id = ?";
        String addBalance = "update `account` set `balance` = balance + ? where user_id =" +
                " (select `user_id` from `phone_numbers` where `number` = ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(deductBalance);
            statement.setInt(1, amount);
            statement.setInt(2, from);
            statement.executeUpdate();
            statement = connection.prepareStatement(addBalance);
            statement.setInt(1, amount);
            statement.setInt(2, phoneNumber);
            statement.executeUpdate();

            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public ResultSet checkFrom(int from){
        String query = "select * from `account` where user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, from);
            return statement.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public ResultSet checkTo(int number){
        String query = "select * from `account` where user_id =" +
                " (select `user_id` from `phone_numbers` where `number` = ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, number);
            return statement.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
