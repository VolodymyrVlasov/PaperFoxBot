package com.paperfox.ua.models.products.categories.stickers;


import com.paperfox.ua.models.products.PrintingProduct;
import com.paperfox.ua.models.customer.conditions.UserQueryStates;

public class RoundSticker extends PrintingProduct {
    public RoundSticker(UserQueryStates productType) {
        super(productType);
        this.colorMode = true;
    }

    @Override
    public void setProductType(UserQueryStates materialSizeType) {
        // todo: add realization
    }
}
