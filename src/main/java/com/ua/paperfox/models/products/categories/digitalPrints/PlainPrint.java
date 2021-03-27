package com.paperfox.ua.models.products.categories.digitalPrints;

import com.paperfox.ua.models.material.Size;
import com.paperfox.ua.models.products.PrintingProduct;
import com.paperfox.ua.models.customer.conditions.UserQueryStates;

import java.io.File;

public class PlainPrint extends PrintingProduct {
    public PlainPrint(UserQueryStates productType) {
        super(productType);

    }

    @Override
    public void setProductType(UserQueryStates materialSizeType) {
        switch(materialSizeType) {
            case KEY_A4_BW -> {
                this.size = new Size(210, 297);
                this.colorMode = false;
            }
            case KEY_A4_CL -> {
                this.size = new Size(210, 297);
                this.colorMode = true;
            }
            case KEY_A3_BW -> {
                this.size = new Size(297, 420);
                this.colorMode = false;
            }
            case KEY_A3_CL -> {
                this.size = new Size(297, 420);
                this.colorMode = true;
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
                "<br>\n\tSIZE: " + name  +
                "<br>\n\tFILES TO PRINT:\n<br>" + sb.toString();
    }
}
