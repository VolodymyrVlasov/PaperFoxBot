package models.products.categories.stickers;


import models.products.PrintingProduct;
import models.users.conditions.UserQueryStates;

public class RoundSticker extends PrintingProduct {
    public RoundSticker(UserQueryStates productType) {
        super(productType);
        this.colorMode = true;
    }

    @Override
    public void setProductSize(UserQueryStates materialSizeType) {
        // todo: add realization
    }
}
