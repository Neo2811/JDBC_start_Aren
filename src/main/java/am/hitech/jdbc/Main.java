package am.hitech.jdbc;


import am.hitech.jdbc.repo.AddressRepo;
import am.hitech.jdbc.repo.PhoneNumberRepo;
import am.hitech.jdbc.repo.UserAddressRepo;

public class Main {


    public static void main(String[] args)  {
        UserAddressRepo userAddressRepo = new UserAddressRepo();
        System.out.println(userAddressRepo.getAll());

    }
}
