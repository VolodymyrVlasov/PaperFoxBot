package models.products.categories.digitalPrints;

import javassist.NotFoundException;
import models.material.Size;
import models.products.PrintingProduct;
import models.users.conditions.UserQueryStates;

import java.io.File;

public class PlainPrint extends PrintingProduct {
    public PlainPrint(UserQueryStates productType) {
        super(productType);

    }

    @Override
    public void setProductSize(UserQueryStates materialSizeType) {
        switch(materialSizeType) {
            case KEY_A4_BW -> {
                this.size = new Size(10, 25);
                this.colorMode = false;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (File e : designs) {
            sb.append("\t\tfile " + i +": " + e.getName() + "\n");
            i++;
        }
        String colorMode = isColorMode() ? "CMYK" : "GRAYSCALE";
        return "Plain print ->" +
                "\n\tSIZE: " + name + " (" + size.getWidth() + "x"+  size.getHeight() + " mm)" + "\tCOLOR MODE: " + colorMode +
                "\n\tFILES TO PRINT:\n" + sb.toString();
    }
}
