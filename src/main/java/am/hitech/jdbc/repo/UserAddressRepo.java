package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAddressRepo {
    Connection connection = DataSource.getConnection();

    public List<UserAddress> getAll(){
        List<UserAddress> userAddresses = new ArrayList<>();
        String query = "SELECT * FROM `user` u JOIN `address` ad ON u.id = ad.user_id";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                UserAddress userAddress = new UserAddress();
                userAddress.setFirstName(resultSet.getString("u.first_name"));
                userAddress.setLastName(resultSet.getString("u.last_name"));
                userAddress.setCountry(resultSet.getString("ad.country"));
                userAddress.setCity(resultSet.getString("ad.city"));
                userAddress.setStreet(resultSet.getString("ad.street"));
                userAddress.setHome(resultSet.getInt("home"));
                userAddresses.add(userAddress);
            }
            return userAddresses;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
