package models.bots;

import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;

public class CustomViberBot {

    com.viber.bot.api.ViberBot bot = new com.viber.bot.api.ViberBot(
            new BotProfile("SampleBot", "http://viber.com/avatar.jpg"), "YOUR_AUTH_TOKEN_HERE");
}
