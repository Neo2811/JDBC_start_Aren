package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.repo.UserAddressRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;

public class UserAddressService {
    UserAddressRepo userAddressRepo = new UserAddressRepo();

    public List<UserAddress> getAll() throws NotFoundException, InternalServerError {
        try {
            List<UserAddress> userAddresses = userAddressRepo.getAll();
            if (userAddresses.isEmpty()){
                throw new NotFoundException("There is no any data");
            }else return userAddresses;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
}
