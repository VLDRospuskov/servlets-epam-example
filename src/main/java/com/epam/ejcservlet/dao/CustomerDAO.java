package com.epam.ejcservlet.dao;

import com.epam.ejcservlet.configuration.DatabaseConfiguration;
import com.epam.ejcservlet.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final String GET_ALL_CUSTOMERS_SQL = "SELECT * FROM customer";
    private final String ADD_CUSTOMER_SQL = "INSERT INTO customer(first_name) VALUES ";
    private final String GET_CUSTOMER_BY_ID_SQL = "SELECT * FROM customer WHERE id = ?";
    private final Connection postgreSQLConnection = DatabaseConfiguration.getPostgreSQLConnection();

    public CustomerDAO() throws SQLException, ClassNotFoundException {
    }

    public Customer getCustomerById(String id) {
        Customer customer = new Customer();
        try {
            PreparedStatement preparedStatement =
                    postgreSQLConnection.prepareStatement(GET_CUSTOMER_BY_ID_SQL);
            preparedStatement.setLong(1, Long.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final long id1 = resultSet.getLong("id");
                customer.setId(id1);
                final String first_name = resultSet.getString("first_name");
                customer.setFirstName(first_name);
                System.out.println(id1 + " " + first_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }


//    public Customer getCustomerById(String id) {
//        Customer customer = new Customer();
//        try {
//            Statement statement = postgreSQLConnection.createStatement();
//            ResultSet resultSet = statement.executeQuery(GET_CUSTOMER_BY_ID_SQL + id);
//            while (resultSet.next()) {
//                final long id1 = resultSet.getLong("id");
//                customer.setId(id1);
//                final String first_name = resultSet.getString("first_name");
//                customer.setFirstName(first_name);
//                System.out.println(id1 + " " + first_name);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customer;
//    }


//    public Customer getCustomerById(String id) {
//        Customer customer = new Customer();
//        try {
//            PreparedStatement preparedStatement =
//                    postgreSQLConnection.prepareStatement(GET_CUSTOMER_BY_ID_SQL + id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                final long id1 = resultSet.getLong("id");
//                customer.setId(id1);
//                final String first_name = resultSet.getString("first_name");
//                customer.setFirstName(first_name);
//                System.out.println(id1 + " " + first_name);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customer;
//    }

//    INSERT INTO customer(first_name) VALUES ('Ivan')
    public boolean addCustomer(String name) throws SQLException {
        PreparedStatement preparedStatement =
                postgreSQLConnection.prepareStatement(ADD_CUSTOMER_SQL + "('" + name + "')");
         return preparedStatement.execute();
    }

    public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = postgreSQLConnection.prepareStatement(GET_ALL_CUSTOMERS_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getLong("id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customers.add(customer);
        }
        return customers;
    }

}
