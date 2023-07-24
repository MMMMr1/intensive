package com.jdbc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.service.api.MedicalHistoryService;
import com.jdbc.service.fabrics.MedicalHistoryServiceSingleton;

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

@WebServlet(name = "MedicalHistoryServlet", urlPatterns = "/histories")
public class MedicalHistoryServlet extends HttpServlet {

    private final MedicalHistoryService service;
    ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new Gson();

    public MedicalHistoryServlet() throws PropertyVetoException {
        this.service = MedicalHistoryServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
        List<Object> collect = service.findAll().stream()
                .map(s ->  gson.toJson(s))
                .collect(Collectors.toList());
        out.print(collect);
        out.flush();
        }catch (RuntimeException e) {
            if (e.getCause() != null) {
                out.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                out.write("<p>" + e.getMessage() + "</p>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
        MedicalHistoryCreateDto history = objectMapper.readValue(req.getReader(), MedicalHistoryCreateDto.class);
        service.create(history);
        } catch (RuntimeException e) {
            if (e.getCause() != null) {
                out.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                out.write("<p>" + e.getMessage() + "</p>");
            }
        }
    }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            try {
            String uuidParam = req.getParameter("uuid");
            UUID uuid = UUID.fromString(uuidParam);
            service.delete(uuid);
            } catch (RuntimeException e) {
                if (e.getCause() != null) {
                    out.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
                } else {
                    out.write("<p>" + e.getMessage() + "</p>");
                }
            }
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
        MedicalHistoryEditDto history = objectMapper.readValue(req.getReader(), MedicalHistoryEditDto.class);
        service.update(history.getUuid(), history);
        } catch (RuntimeException e) {
            if (e.getCause() != null) {
                out.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                out.write("<p>" + e.getMessage() + "</p>");
            }
        }
    }
}

