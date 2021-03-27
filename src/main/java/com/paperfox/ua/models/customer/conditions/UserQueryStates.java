package com.paperfox.ua.models.customer.conditions;

public enum UserQueryStates {
    //Start message buttons
    KEY_QUICK_PRINT("Відправити файли на друк"),
    KEY_CALC_PRODUCT("Розрахувати вартість продукції"),

    //Quick print message buttons
    KEY_A4_BW("А4 - чорно-білий"),
    KEY_A4_CL("А4 - кольоровий"),
    KEY_A3_BW("А3 - чорно-білий"),
    KEY_A3_CL("А3 - кольоровий"),
    KEY_OTHER_PRODUCT("\uD83D\uDCF0 Обрати іншу категорію"),
    KEY_SEND_QUICK_PRINT_ORDER("✅ Відправити замовлення"),
    KEY_CANCEL_ORDER("⏏ Повернутись у головне меню"),

    SRA4(""),
    SRA3(""),

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
