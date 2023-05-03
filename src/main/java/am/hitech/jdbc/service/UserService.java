package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserRepo userRepo = new UserRepo();
    public void deleteUser(String email) throws InternalServerError {
        int row = userRepo.deleteUser(email);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }

    public void updateUser(String firstName, String lastName, int id) throws InternalServerError {
        int row = userRepo.updateUser(firstName,lastName,id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<User> getAll() throws NotFoundException, InternalServerError {
        List<User> users = userRepo.getAll();
        try {
            if (users.isEmpty()){
                throw new NotFoundException("There is no any user");
            }else return users;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void createUserV2(User user) throws InternalServerError {
        int row = userRepo.createUserV2(user);

        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }

    public void createUser(User user) throws InternalServerError {
        int row = userRepo.createUser(user);

        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }

    public User getById(int id) throws NotFoundException, InternalServerError {
        try {
            User user = userRepo.getByID(id);
            if (user == null){
                throw new NotFoundException("There is no any user");
            }
            else return user;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }

    public List<User> older18() throws SQLException, NotFoundException, InternalServerError {
        try {
            List<User> users = userRepo.older18();
            if (users.isEmpty()){
                throw new NotFoundException("There is no any user");
            }
            else return users;

        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<User> getByName(String name) throws SQLException, NotFoundException, InternalServerError {
        try {
            List<User> users = userRepo.getByName(name);
            if (users.isEmpty()){
                throw new NotFoundException("There is not any user with that name");
            }
            else return users;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong, please try again");
        }
    }

}
