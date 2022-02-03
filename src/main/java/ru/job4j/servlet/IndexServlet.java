package ru.job4j.servlet;

import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.adv.Image;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Model;
import ru.job4j.model.user.Account;
import ru.job4j.repository.postgres.AdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    private void getAdvData() {
        Model model = new Model();
        Car car = new Car(model);

        try {
            Advertisement ad = new Advertisement(new Account(), car);
            AdRepository.instOf().add(ad);
            ((AdRepository) AdRepository.instOf()).execute(session -> {
                session.save(new Image(ad));
                return 1;
            });

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println(((AdRepository) AdRepository.instOf()).lastDayAnnouncements());
        System.out.println(((AdRepository) AdRepository.instOf()).showAdvWithPhotos());
        System.out.println(((AdRepository) AdRepository.instOf()).showAdvModel(1));
    }
}
