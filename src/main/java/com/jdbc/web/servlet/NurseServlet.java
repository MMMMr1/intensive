package com.jdbc.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.dto.nurse.NurseEditDto;
import com.jdbc.service.api.NurseService;
import com.jdbc.service.fabrics.NurseServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "NurseServlet", urlPatterns = "/nurses")
public class NurseServlet extends HttpServlet {

    private final NurseService service;
    ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new Gson();

    public NurseServlet() throws PropertyVetoException {
        this.service = NurseServiceSingleton.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        try {
            List<Object> collect = service.findAll().stream()
                    .map(s -> gson.toJson(s))
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
            NurseCreateDto  nurse= objectMapper.readValue(req.getReader(), NurseCreateDto.class);
            service.create(nurse);
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
            Long uuid = Long.getLong(uuidParam);
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
            NurseEditDto nurse = objectMapper.readValue(req.getReader(), NurseEditDto.class);
            service.update(nurse.getUuid(), nurse);
        } catch (RuntimeException e) {
            if (e.getCause() != null) {
                out.write("<p>" + e.getMessage() + ": " + e.getCause() + "</p>");
            } else {
                out.write("<p>" + e.getMessage() + "</p>");
            }
        }
    }
}

