package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressRepo {

    Connection connection = DataSource.getConnection();
    public Address addressBuilder(ResultSet resultSet){
        Address address = new Address();
        try{
           while (resultSet.next()){
               address.setId(resultSet.getInt("id"));
               address.setCountry(resultSet.getString("country"));
               address.setCity(resultSet.getString("city"));
               address.setStreet(resultSet.getString("street"));
               address.setHome(resultSet.getInt("home"));
               address.setUserId(resultSet.getInt("user_id"));
           }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return address;
    }

    public int createAddress(Address address){
        String query = "insert into `address` values(0, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getHome());
            statement.setInt(5, address.getUserId());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Address getById(int id){
        String query = "select * from `address` where id = ?";
        Address address = new Address();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            address = addressBuilder(resultSet);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return address;
    }
    public List<Address> getByUserId(int userId){
        String query = "select * from `address` where user_id = ?";
        List<Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));
                addresses.add(address);
            }
            return addresses;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Address> getAll(){
        String query = "select * from `address`";
        List<Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));
                addresses.add(address);
            }
            return addresses;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int updateAddress(String country, int home, int id){
        String query = "update `address` set country = ?, home = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, country);
            statement.setInt(2, home);
            statement.setInt(3, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int deleteAddress(int id){
        String query = "delete from `address` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Map<User,Address> getByUserId2(int userId){
        String query = "SELECT * FROM `address` ad\n" +
                "JOIN `user` u ON ad.user_id = u.id AND ad.user_id = ?";
        try {
            Map<User, Address> map = new HashMap<User, Address>();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("u.id"));
                user.setFirstName(resultSet.getString("u.first_name"));
                user.setLastName(resultSet.getString("u.last_name"));
                user.setEmail(resultSet.getString("u.email"));
                user.setAge(resultSet.getInt("u.age"));
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setCountry(resultSet.getString("country"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHome(resultSet.getInt("home"));
                address.setUserId(resultSet.getInt("user_id"));
                map.put(user, address);
            }
            return map;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
