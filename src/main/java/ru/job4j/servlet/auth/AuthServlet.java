package ru.job4j.servlet.auth;

import ru.job4j.model.user.Account;
import ru.job4j.repository.hql.account.AccountRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HashMap<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<Account> accounts =
                (List<Account>) AccountRepository.instOf()
                        .executeSelect("from Account where email=:email", params);
        if (accounts.isEmpty()) {
            req.setAttribute("errMsg", "User with this email was not found");
        } else {
            Account account = accounts.get(0);
            if (account.validatePwd(password)) {
                createSession(req, resp, accounts);
                return;
            } else {
                req.setAttribute("errMsg", "Invalid password");
            }
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    private void createSession(HttpServletRequest req, HttpServletResponse resp,
                               List<Account> accounts)
            throws IOException {
        HttpSession sc = req.getSession();
        sc.setAttribute("account", accounts.get(0));
        resp.sendRedirect(req.getContextPath());
    }
}
