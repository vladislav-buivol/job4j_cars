package ru.job4j.servlet.auth;

import ru.job4j.model.user.Account;
import ru.job4j.repository.store.account.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            if (((AccountRepository) AccountRepository.instOf())
                    .findAccountByEmail(email).size() == 0) {
                AccountRepository.instOf().add(new Account(name, email, password));
                req.setAttribute("notific", "Successfully registered");
            } else {
                req.setAttribute("errMsg", String.format("This email %s already in use", email));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }
}
