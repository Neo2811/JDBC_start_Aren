package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.InternalServerError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignInServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserService userService = new UserService();
        String name = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String password = req.getParameter("password");
        String resetPassword = req.getParameter("resetPassword");
        PrintWriter printWriter = resp.getWriter();
        User user = new User();
            user.setId(0);
            user.setFirstName(name);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAge(age);
            user.setPassword(password);
                try {
                    userService.createUserV2(user);
                    printWriter.write("Sign in is ok");
                } catch (InternalServerError e) {
                    e.printStackTrace();
                }
            }
        }

