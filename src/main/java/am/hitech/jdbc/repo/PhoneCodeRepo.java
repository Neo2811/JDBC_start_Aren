package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.PhoneCode;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneCodeRepo {
    Connection connection = DataSource.getConnection();

    public PhoneCode phoneCodeBuilder(ResultSet resultSet){
        PhoneCode phoneCode = new PhoneCode();
        try {
            while (resultSet.next()){
                phoneCode.setId(resultSet.getInt("id"));
                phoneCode.setCode(resultSet.getInt("code"));
                phoneCode.setOperatorId(resultSet.getInt("operator_id"));
            }
            return phoneCode;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public PhoneCode getById(int id){
        String query = "select * from `phone_codes` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return phoneCodeBuilder(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<PhoneCode> getAll(){
        List<PhoneCode> phoneCodes = new ArrayList<>();
        String query = "select * from `phone_codes`";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                PhoneCode phoneCode = new PhoneCode();
                phoneCode.setId(resultSet.getInt("id"));
                phoneCode.setCode(resultSet.getInt("code"));
                phoneCode.setOperatorId(resultSet.getInt("operator_id"));
                phoneCodes.add(phoneCode);
            }
            return phoneCodes;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int createPhoneCode(PhoneCode phoneCode){
        String query = "insert into `phone_codes` values(0, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, phoneCode.getCode());
            statement.setInt(2,phoneCode.getOperatorId());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int updatePhoneCode(int id, int code, int operatorId){
        String query = "update `phone_codes` set code = ?, operator_id = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, code);
            statement.setInt(2, operatorId);
            statement.setInt(3, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int deletePhoneCode(int id){
        String query = "delete from `phone_codes` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
