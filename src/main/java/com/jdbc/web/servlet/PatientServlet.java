package com.jdbc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jdbc.dto.PatientCreateDto;
import com.jdbc.dto.PatientEditDto;
import com.jdbc.service.api.PatientService;
import com.jdbc.service.fabrics.PatientServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(name = "PatientServlet", urlPatterns = "/patients")
public class PatientServlet extends HttpServlet {

    private final PatientService service;
    ObjectMapper objectMapper = new ObjectMapper();
//    private Gson gson = new Gson();

    public PatientServlet() throws PropertyVetoException {
        this.service = PatientServiceSingleton.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

//        List<Object> collect = service.findAll().stream()
//                .map(s ->  gson.toJson(s))
//                .collect(Collectors.toList());
        PrintWriter out = resp.getWriter();
        out.print("collect");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PatientCreateDto patient = objectMapper.readValue(req.getReader(), PatientCreateDto.class);
        service.create(patient);
    }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");

            PrintWriter writer = resp.getWriter();
            String uuidParam = req.getParameter("uuid");
            UUID uuid = UUID.fromString(uuidParam);
            service.delete(uuid);
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PatientEditDto patient = objectMapper.readValue(req.getReader(), PatientEditDto.class);

        PrintWriter writer = resp.getWriter();
        writer.write("uuid" + patient.getUuid()+ " patient "+ patient.getLastName());
        service.update(patient.getUuid(), patient);
    }
}

