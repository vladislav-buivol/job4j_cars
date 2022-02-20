package ru.job4j.servlet;

import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.adv.Image;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Model;
import ru.job4j.model.user.Account;
import ru.job4j.repository.hql.AdsRepository;

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

        Model model = new Model();
        Car car = new Car(model);
        Advertisement ad = new Advertisement(new Account(), car);
        Image img = new Image();
        ad.addImage(img);
        try {
            AdsRepository.instOf().add(ad);
            AdsRepository.instOf().delete(ad.getId() + "");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        AdsRepository.instOf().findAll();
        AdsRepository.instOf().delete("17");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    private void getAdvData() {
        Model model = new Model();
        Car car = new Car(model);

        try {
            Advertisement ad = new Advertisement(new Account(), car);
            AdsRepository.instOf().add(ad);
            AdsRepository.instOf().findAll();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println(((AdsRepository) AdsRepository.instOf()).lastDayAnnouncements());
        System.out.println(((AdsRepository) AdsRepository.instOf()).showAdvWithPhotos());
        System.out.println(((AdsRepository) AdsRepository.instOf()).showAdvModel(1));
    }
}
