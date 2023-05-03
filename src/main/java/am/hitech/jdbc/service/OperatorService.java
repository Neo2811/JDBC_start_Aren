package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Operator;
import am.hitech.jdbc.repo.OperatorRepo;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;

public class OperatorService {

    OperatorRepo operatorRepo = new OperatorRepo();

    public Operator getById(int id) throws NotFoundException, InternalServerError {
        try {
            Operator operator = operatorRepo.getById(id);
            if (operator.getId() == 0){
                throw new NotFoundException("There is no any operator");
            }else return operator;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public List<Operator> getAll() throws NotFoundException, InternalServerError {
        List<Operator> operators = operatorRepo.getAll();
        try {
            if (operators.isEmpty()){
                throw new NotFoundException("There is empty");
            }else return operators;
        }catch (RuntimeException e){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void createOperator(Operator operator) throws InternalServerError {
            int row = operatorRepo.createOperator(operator);
            if (row == 0){
                throw new InternalServerError("Something went wrong");
            }
    }
    public void updateOperator(String name, int id) throws InternalServerError {
        int row = operatorRepo.updateOperator(name,id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
    public void deleteOperator(int id) throws InternalServerError {
        int row = operatorRepo.deleteOperator(id);
        if (row == 0){
            throw new InternalServerError("Something went wrong");
        }
    }
}
