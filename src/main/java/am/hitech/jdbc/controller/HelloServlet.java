package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.service.UserAddressService;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HelloServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        UserAddressService userAddressService = new UserAddressService();
//        PrintWriter printWriter = resp.getWriter();
//        try {
//            List<UserAddress> userAddresses = userAddressService.getAll();
//            for (int i = 0; i < userAddresses.size(); i++){
//                printWriter.write(userAddresses.get(i).getFirstName() + " " + userAddresses.get(i).getLastName() + " " + userAddresses.get(i).getCountry() +
//                        " " + userAddresses.get(i).getCity() + " " + userAddresses.get(i).getStreet() + " " + userAddresses.get(i).getHome() + "\n");
//            }
//        } catch (NotFoundException | InternalServerError e) {
//            throw new RuntimeException(e);
//        }
        UserService userService = new UserService();

        String name = req.getParameter("name");
        String s = req.getParameter("surname");
        String id = req.getParameter("id");
        try {
            User user = userService.getById(Integer.parseInt(id));
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("hello " + user);
        } catch (NotFoundException | InternalServerError e) {
            e.printStackTrace();
        }


    }
}
