package com.paperfox.ua.postgres.dao;

import com.paperfox.ua.models.customer.Customer;
import com.paperfox.ua.models.customer.TelegramCustomer;

import java.sql.SQLException;

public interface CustomerDao {
    void createCustomer(Customer customer) throws SQLException;
    void createTelegramCustomer(TelegramCustomer customer) throws SQLException;

}
