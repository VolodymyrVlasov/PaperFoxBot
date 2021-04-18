package com.ua.paperfox;

import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.postgres.dao.CustomerDao;
import com.ua.paperfox.postgres.dao.impl.CustomerDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaperfoxApplication  {
	public static void main(String[] args) {
		SpringApplication.run(PaperfoxApplication.class, args);
	}
}
