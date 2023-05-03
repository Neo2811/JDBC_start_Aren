package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.AddressRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;
import java.util.List;
import java.util.Map;

public class AddressService {
    AddressRepo addressRepo = new AddressRepo();

    public void createAddress(Address address) throws InternalServerError {
        int row = addressRepo.createAddress(address);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public Address getById(int id) throws NotFoundException, InternalServerError {
        try {
            Address address = addressRepo.getById(id);
            if (address.getId() == 0){
                throw new NotFoundException("There is not any address");
            }
            else return address;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<Address> getByUserId(int userId) throws NotFoundException, InternalServerError {
        List<Address> addresses = addressRepo.getByUserId(userId);
        try {
            if (addresses.isEmpty()){
                throw new NotFoundException("There is no any address");
            }
            else return addresses;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<Address> getAll() throws NotFoundException, InternalServerError {
        List<Address> addresses = addressRepo.getAll();
        try {
            if (addresses.isEmpty()){
                throw new NotFoundException("There is no any addresses");
            }
            else return addresses;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void updateAddress(String country, int home, int id) throws InternalServerError {
        int row = addressRepo.updateAddress(country,home,id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void deleteAddress(int id) throws InternalServerError {
        int row = addressRepo.deleteAddress(id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public Map<User, Address> getByUserId2(int userId) throws NotFoundException, InternalServerError {
        Map<User,Address> map = addressRepo.getByUserId2(userId);
        try {
            if (map.isEmpty()){
                throw new NotFoundException("There is no any address with that user id");
            }
            else return map;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }

}
