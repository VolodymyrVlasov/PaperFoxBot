package com.ua.paperfox.models.products.categories.stickers;


import com.ua.paperfox.models.products.PrintingProduct;
import com.ua.paperfox.models.customer.conditions.UserQueryStates;

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
