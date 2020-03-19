package com.epam.ejcservlet.controller;

import com.epam.ejcservlet.dao.CustomerDAO;
import com.epam.ejcservlet.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    CustomerDAO customerDAO = new CustomerDAO();

    public CustomerServlet() throws SQLException, ClassNotFoundException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> allCustomers = null;
        try {
            allCustomers = customerDAO.getAllCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("listCustomers", allCustomers);
        req.getRequestDispatcher("/customers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String firstName = req.getParameter("firstName").trim();
        try {
            System.out.println(customerDAO.addCustomer(firstName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("id").trim();
        Customer customerById = customerDAO.getCustomerById(id);
        req.setAttribute("customer", customerById);
        req.getRequestDispatcher("/customerInfo.jsp").forward(req, resp);
    }

}

// CRUD - CREATE READ UPDATE DELETE
// CREATE (POST)
// READ (GET)
// UPDATE (PUT)
// DELETE (DELETE)
// PATCH