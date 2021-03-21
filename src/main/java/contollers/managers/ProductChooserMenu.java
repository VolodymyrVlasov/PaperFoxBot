package contollers.managers;

import models.customer.TelegramCustomer;
import models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;

import static models.customer.conditions.UserQueryStates.*;

public class ProductChooserMenu extends AbstractMenu {

    public ProductChooserMenu(TelegramCustomer user, Update update) {
        super(user, update);
    }

    @Override
    public void handleUpdateEvent() {
        switch (super.user.getState()) {
            case CALC_PRODUCT -> {
                setReplyAndChangeStateTo(UserStates.CHOOSE_PRODUCT);
            }
            case CHOOSE_PRODUCT -> {
                if (super.update.hasCallbackQuery()) {
                    String query = super.update.getCallbackQuery().getData();
                    if (query.equals(KEY_STICKERS.toString())) {
                        user.setState(UserStates.STIKERS);
                        // todo: add realization StickersMenu
                    } else if (query.equals(KEY_CARDS.toString())) {
                        user.setState(UserStates.CARDS);
                        // todo: add realization CardsMenu
                    } else if (query.equals(KEY_BIZ_CARDS.toString())) {
                        user.setState(UserStates.BIZ_CARDS);
                        // todo: add realization BizCardsMenu
                    } else if (query.equals(KEY_FLYERS.toString())) {
                        user.setState(UserStates.FLYERS);
                        // todo: add realization FlyersMenu
                    }
                } else {
                    setInvalidInputAndGoToState(UserStates.SELECT_SIZE_COLOR);
                }
            }
        }
    }
}
