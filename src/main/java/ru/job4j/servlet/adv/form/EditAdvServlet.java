package ru.job4j.servlet.adv.form;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.model.adv.Advertisement;
import ru.job4j.model.user.Account;
import ru.job4j.pages.add.form.AdvForm;
import ru.job4j.repository.store.adv.AdsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAdvServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String advId = req.getParameter("id");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (advId != null) {
            Advertisement adv = AdsRepository.instOf().findById(advId);
            if (adv.getAccount().getId() == account.getId()) {
                req.setAttribute("adv", adv);
                req.getRequestDispatcher("adv/createAdv.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/add.jsp");
        }
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
        Advertisement ad = form.updateAdv(form, req.getParameter("id"));
        AdsRepository.instOf().update(String.valueOf(ad.getId()), ad);
        resp.sendRedirect(req.getContextPath());
    }
}
