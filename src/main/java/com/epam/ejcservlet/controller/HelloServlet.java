package com.epam.ejcservlet.controller;

import com.epam.ejcservlet.configuration.DatabaseConfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection postgreSQLConnection = DatabaseConfiguration.getPostgreSQLConnection();
            PreparedStatement preparedStatement = postgreSQLConnection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS customer(\n" +
                            "  id SERIAL PRIMARY KEY,\n" +
                            "  first_name VARCHAR NOT NULL\n" +
                            ");");
            final PreparedStatement preparedStatement1 =
                    postgreSQLConnection.prepareStatement("INSERT INTO customer(first_name) VALUES ('Ivan')");
            preparedStatement.execute();
            preparedStatement1.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String name = "Boris";
        req.setAttribute("name", name);
        req.getRequestDispatcher("/hello.jsp").forward(req, resp);
    }

}
