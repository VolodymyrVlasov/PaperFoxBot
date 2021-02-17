package models.products.categories.digitalPrints;

import models.products.PrintingProduct;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlainPrint extends PrintingProduct {
    private double width;
    private double height;
    private String name;
    private boolean colorMode;
    private List<File> designs;


    public PlainPrint(String name, boolean isColor) {
        this.name = name;
        this.colorMode = colorMode;
        designs = new ArrayList<>();
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
    public void attachFile(File design) {
        designs.add(design);
    }

    @Override
    public String getOrderPath() {
                String pathToLastFile = designs.get(designs.size() - 1).getAbsolutePath();
                String orderPath = pathToLastFile.substring(0, pathToLastFile.lastIndexOf("\\"));
        return orderPath;
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
                "\n\tSIZE: " + name + " (" + width + "x"+  height + " mm)" + "\tCOLOR MODE: " + colorMode +
                "\n\tFILES TO PRINT:\n" + sb.toString();
    }
}
