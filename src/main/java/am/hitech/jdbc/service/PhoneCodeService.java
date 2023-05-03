package am.hitech.jdbc.service;

import am.hitech.jdbc.model.PhoneCode;
import am.hitech.jdbc.repo.PhoneCodeRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;

public class PhoneCodeService {
    PhoneCodeRepo phoneCodeRepo = new PhoneCodeRepo();

    public PhoneCode getById(int id) throws NotFoundException, InternalServerError {
        PhoneCode phoneCode = phoneCodeRepo.getById(id);
        try {
            if (phoneCode.getId() == 0){
                throw new NotFoundException("There is no ant phone code");
            }else return phoneCode;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }

    public List<PhoneCode> getAll() throws NotFoundException, InternalServerError {
        List<PhoneCode> phoneCodes = phoneCodeRepo.getAll();
        try {
            if (phoneCodes.isEmpty()){
                throw new NotFoundException("There is empty");
            }else return phoneCodes;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void createPhoneCode(PhoneCode phoneCode) throws InternalServerError {
        int row = phoneCodeRepo.createPhoneCode(phoneCode);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void updatePhoneCode(int id, int code, int operatorId) throws InternalServerError {
        int row = phoneCodeRepo.updatePhoneCode(id, code, operatorId);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void deletePhoneCode(int id) throws InternalServerError {
        int row = phoneCodeRepo.deletePhoneCode(id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
}
