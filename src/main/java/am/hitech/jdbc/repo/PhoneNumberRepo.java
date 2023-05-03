package am.hitech.jdbc.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import am.hitech.jdbc.model.PhoneNumber;
import am.hitech.jdbc.util.DataSource;

public class PhoneNumberRepo {
    Connection connection = DataSource.getConnection();

    public List<PhoneNumber> getAll(){
        List<PhoneNumber> numbers = new ArrayList<>();
        String query = "select * from `phone_numbers`";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setId(resultSet.getInt("id"));
                phoneNumber.setNumber(resultSet.getInt("number"));
                phoneNumber.setPhoneCodeId(resultSet.getInt("phone_code_id"));
                phoneNumber.setUserId(resultSet.getInt("user_id"));
                numbers.add(phoneNumber);
            }
            return numbers;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
