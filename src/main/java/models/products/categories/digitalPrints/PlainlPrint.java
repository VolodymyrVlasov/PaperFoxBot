package models.products.categories.digitalPrints;

import models.products.PrintingProduct;

import java.io.File;

public class PlainlPrint extends PrintingProduct {
    private double width;
    private double height;
    private String name;
    private boolean colorMode;
    private File design;


    public PlainlPrint(String name, boolean isColor) {
        this.name = name;
        this.colorMode = colorMode;
        setSize(name);
    }

    private void setSize(String name) {
        switch (name) {
            case "A4":
                width = 210;
                height = 297;
                break;
            case "A3":
                width = 297;
                height = 420;
                break;

        }
    }

    public void setCustomSize(double width, double height) {
        this.width = width;
        this.height = height;
        this.name = width + "x" + height + " mm";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isColorMode() {
        return colorMode;
    }

    @Override
    public void setColorMode(boolean colorMode) {
        this.colorMode = colorMode;
    }

    @Override
    public File getDesign() {
        return design;
    }

    @Override
    public void attachDesign(File design) {
        this.design = design;
    }

    @Override
    public String toString() {
        return "\nPlain print ->" +
                "\n         name:         " + name +
                "\n         size:         " + width + "x"+  height + " mm" +
                "\n         printInColor: " + colorMode +
                "\n         design:       " + design;
    }
}