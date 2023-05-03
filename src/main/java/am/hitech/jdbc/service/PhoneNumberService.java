package am.hitech.jdbc.service;

import am.hitech.jdbc.model.PhoneNumber;
import am.hitech.jdbc.repo.PhoneNumberRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public class PhoneNumberService {
    PhoneNumberRepo phoneNumberRepo = new PhoneNumberRepo();

    public List<PhoneNumber> getAll()  {
        return phoneNumberRepo.getAll();
        }
    }

