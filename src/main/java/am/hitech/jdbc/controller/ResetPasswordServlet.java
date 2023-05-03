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
import java.util.Random;

public class ResetPasswordServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String forget = req.getParameter("email");
        PrintWriter printWriter = resp.getWriter();
        boolean find = false;
        int index = 0;
        try {
            List<User> users = userService.getAll();
            for(int i = 0; i < users.size(); i++){
                if (users.get(i).getEmail().equals(forget)){
                    index = i;
                    find = true;
                }
            }
            if (find){
                Random random = new Random();
                int num = random.nextInt(6) + 1;
                String randomString = Integer.toString(num);
                
            }
        } catch (NotFoundException | InternalServerError e) {
            e.printStackTrace();
        }
    }
}
