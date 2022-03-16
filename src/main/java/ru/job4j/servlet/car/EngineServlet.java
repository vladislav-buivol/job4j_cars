package ru.job4j.servlet.car;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.repository.hql.car.EngineRepo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class EngineServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String cities = GSON.toJson(EngineRepo.instOf().findAll());
        output.write(cities.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
