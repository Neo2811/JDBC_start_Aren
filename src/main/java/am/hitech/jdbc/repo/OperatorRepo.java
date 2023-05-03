package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.Operator;
import am.hitech.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorRepo {

    Connection connection = DataSource.getConnection();

    public Operator operatorBuilder(ResultSet resultSet){
        Operator operator = new Operator();
        try {
            while (resultSet.next()){
                operator.setId(resultSet.getInt("id"));
                operator.setName(resultSet.getString("name"));
            }
            return operator;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public Operator getById(int id){
        String query = "select * from `operator` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Operator operator = operatorBuilder(resultSet);
            return operator;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Operator> getAll(){
        String query = "select * from `operator`";
        List<Operator> operators = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Operator operator = new Operator();
                operator.setId(resultSet.getInt("id"));
                operator.setName(resultSet.getString("name"));
                operators.add(operator);
            }
            return operators;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int createOperator(Operator operator){
        String query = "insert into `operator` values(0, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, operator.getName());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int updateOperator(String name, int id){
        String query = "update `operator` set `name` = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int deleteOperator(int id){
        String query = "delete from `operator` where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
