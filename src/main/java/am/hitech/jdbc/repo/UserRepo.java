package am.hitech.jdbc.repo;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    Connection connection = DataSource.getConnection();
    public int deleteUser(String email){
        String query = "delete from `user` where email = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int updateUser(String firstName, String lastName, int id){
        String query = "update `user` set first_name = ?, last_name = ? where id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public  int createUserV2(User user){
        String query = "insert into `user` values(0, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getPassword());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int createUser(User user){
        String query = "insert into `user` values(" + user.getId() + ", '" + user.getFirstName() + "'" + ", '" + user.getLastName() + "'" + ", '" + user.getEmail() + "'" + ", " + user.getAge() + ")";

        try {
            Statement statement = connection.createStatement();
            return  statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByID(int id){
        User user = new User();
        String query = "select * from `user` where id = " + id;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            user = buildUser(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public  User getByUsername(String email) throws SQLException {
        User user = new User();

        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from `user`");

        user = buildUser(resultSet);
        return user;
    }

    public List<User> older18() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "select * from `user` where `age` > 18";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setAge(resultSet.getInt("age"));
            users.add(user);
        }
        return users;
    }

    public List<User> getByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "select * from `user` where first_name like '" + name + "%'";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setAge(resultSet.getInt("age"));
            users.add(user);
        }
        return users;
    }
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        String query = "select * from `user`";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setId(resultSet.getInt("id"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPasswordToken(resultSet.getString("password_token"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet){
        User user = new User();
        try {
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }
}
