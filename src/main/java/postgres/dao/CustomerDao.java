package postgres.dao;

import models.customer.Customer;
import models.customer.TelegramCustomer;

import java.sql.SQLException;

public interface CustomerDao {
    void createCustomer(Customer customer) throws SQLException;
    void createTelegramCustomer(TelegramCustomer customer) throws SQLException;

}
