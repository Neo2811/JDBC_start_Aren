package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.User;
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

public class WelcomeServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserService();
        PrintWriter printWriter = resp.getWriter();
        try {
            List<User> users = userService.getAll();
            boolean login = false;
            int index = 0;
            for (int i = 0; i < users.size(); i++){
                if (username.equals(users.get(i).getEmail())){
                    if (password.equals(users.get(i).getPassword())){
                        login = true;
                        index = i;
                    }
                }
            }
            if (login){
                printWriter.write("Welcome " + users.get(index).getFirstName() + " " + users.get(index).getLastName());
            }
            else printWriter.write("Invalid login or password. Please try again");
        } catch (NotFoundException | InternalServerError e) {
            e.printStackTrace();
        }

    }
}
