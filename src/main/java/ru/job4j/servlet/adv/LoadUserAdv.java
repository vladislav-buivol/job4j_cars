package ru.job4j.servlet.adv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import org.hibernate.Session;
import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.user.Account;
import ru.job4j.repository.hql.adv.AdsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LoadUserAdv extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        Gson gson = createJson();
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int accId = account.getId();
            String adv = gson.toJson(
                    ((AdsRepository) AdsRepository.instOf()).showUserAdvData(accId));
            output.write(adv.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
    }

    private Gson createJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder()
                .addType(Advertisement.class)
                .registerOn(gsonBuilder);
        return gsonBuilder.create();
    }
}
