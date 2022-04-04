package ru.job4j.servlet.adv.form;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.car.Car;
import ru.job4j.pages.add.form.AdvForm;
import ru.job4j.repository.store.adv.AdsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddAdvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/adv/createAdv.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        if (!isMultipartContent) {
            return;
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        AdvForm form;
        try {
            form = AdvForm.of(upload.parseRequest(req));
        } catch (FileUploadException e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        Car car = form.createCar(form);
        Advertisement ad = form.createAdv(req.getSession(), car, form);
        try {
            AdsRepository.instOf().add(ad);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath());
    }
}
