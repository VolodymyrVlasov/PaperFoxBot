package models.users.conditions;

public enum UserQueryStates {
    //Start message buttons
    KEY_QUICK_PRINT("Відправити файли на друк"),
    KEY_CALC_PRODUCT("Розрахувати вартість продукції"),

    //Quick print message buttons
    KEY_A4_BW("А4 - ч/б"),
    KEY_A4_CL("А4 - кол"),
    KEY_A3_BW("А3 - ч/б"),
    KEY_A3_CL("А3 - кол"),
    KEY_ONE_MORE_FILE("Та, додати"),
    KEY_SEND_QUICK_PRINT_ORDER("Ні, відправити"),

    //Calc product message buttons
    KEY_STICKERS("Наліпки / стікери"),
    KEY_CARDS("Картки / листівки"),
    KEY_BIZ_CARDS("Візитівки"),
    KEY_FLYERS("Флаєри / Ліфлети");

    private String value;

    UserQueryStates(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
