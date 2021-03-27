package com.ua.paperfox.postgres.dao;

import com.ua.paperfox.models.customer.Customer;
import com.ua.paperfox.models.customer.TelegramCustomer;

import java.sql.SQLException;

public interface CustomerDao {
    void createCustomer(Customer customer) throws SQLException;
    void createTelegramCustomer(TelegramCustomer customer) throws SQLException;

}
