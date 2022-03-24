package ru.job4j.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.adv.Image;
import ru.job4j.model.car.Car;
import ru.job4j.model.car.Engine;
import ru.job4j.model.car.Model;
import ru.job4j.model.user.Account;
import ru.job4j.pages.add.form.AddAdvForm;
import ru.job4j.pages.add.form.ImgData;
import ru.job4j.repository.hql.adv.AdsRepository;
import ru.job4j.repository.hql.car.CarModelRepo;
import ru.job4j.repository.hql.car.EngineRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class AddAdvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("adv/createAdv.jsp").forward(req, resp);
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

        AddAdvForm form = getFormData(req, resp, upload);
        Car car = createCar(form);
        Advertisement ad = createAdv(req, car, form);
        try {
            AdsRepository.instOf().add(ad);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath());
    }

    private AddAdvForm getFormData(HttpServletRequest req, HttpServletResponse resp,
                                   ServletFileUpload upload) throws IOException {
        AddAdvForm form = new AddAdvForm();
        try {
            List<FileItem> fields = upload.parseRequest(req);

            Iterator<FileItem> it = fields.iterator();
            if (!it.hasNext()) {
                return form;
            }
            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    form.setData(fileItem.getFieldName(), fileItem.getString());
                } else {
                    String imgName = fileItem.getName();
                    String contentType = fileItem.getContentType();
                    resp.setContentType(contentType);
                    long size = fileItem.getSize();
                    ImgData imgData = new ImgData(imgName, contentType, fileItem.get(), size);
                    form.addImg(imgData);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return form;
    }

    private Advertisement createAdv(HttpServletRequest req, Car car, AddAdvForm form) {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Advertisement advertisement = new Advertisement(account, car)
                .setPrice(form.getPrice());
        for (ImgData imgData : form.getImgData()) {
            Image image = new Image().setImgData(imgData.getData())
                    .setContentType(imgData.getContentType())
                    .setName(imgData.getName())
                    .setSize(imgData.getSize());
            advertisement.addImage(image);
        }
        return advertisement;
    }

    private Car createCar(AddAdvForm form) {
        Model model = CarModelRepo.instOf().findById(form.getModel());
        Engine engine = EngineRepo.instOf().findById(form.getEngineSize());

        return new Car(model)
                .setEngine(engine)
                .setMileage(form.getMileage())
                .setNrOfSeats(form.getNrOfSeats())
                .setGearbox(form.getGearBox())
                .setFuel(form.getGearBox())
                .setUsed(form.isUsed());
    }
}
