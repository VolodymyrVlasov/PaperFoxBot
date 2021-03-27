package com.ua.paperfox;

import com.ua.paperfox.constants.config.ConfigData;
import com.ua.paperfox.contollers.MainMenu;
import com.ua.paperfox.models.bots.CustomTelegramBot;
import com.ua.paperfox.models.customer.TelegramCustomer;
import com.ua.paperfox.models.customer.conditions.UserStates;
import com.ua.paperfox.models.shop.ShoppingCart;
import com.ua.paperfox.postgres.dao.CustomerDao;
import com.ua.paperfox.postgres.dao.impl.CustomerDaoImpl;
import com.ua.paperfox.services.mailServices.EmailTemplator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PaperfoxApplication  {
	private static CustomTelegramBot instance;
	private final Map<Long, TelegramCustomer> users = new HashMap<>();
	private CustomerDao customerDao = new CustomerDaoImpl();

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

		try {
			telegramBotsApi.registerBot(CustomTelegramBot.getInstance());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}

		SpringApplication.run(PaperfoxApplication.class, args);
	}
}
