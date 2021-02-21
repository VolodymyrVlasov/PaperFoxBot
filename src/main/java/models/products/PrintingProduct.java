package models.products;

import models.material.Size;
import models.users.conditions.UserQueryStates;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class PrintingProduct {
    public boolean colorMode;
    public Size size;
    public List<File> designs;
    public String name;


    private double mediaDensity;
    private double price;
    private int quantity;
    private String orderPath;

    public PrintingProduct(UserQueryStates productType) {
        this.designs = new ArrayList<>();
        this.name = productType.getValue();
    }

    public abstract void setProductType(UserQueryStates materialSizeType);

    @Override
    public String toString() {
        return "PrintingProduct{" +
                "width=" + size.getWidth() +
                ", height=" + size.getHeight() +
                ", mediaDensity=" + mediaDensity +
                ", price=" + price +
                ", quantity=" + quantity +
                ", colorMode=" + colorMode +
                ", name='" + name + '\'' +
//                ", design=" + design +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public boolean isColorMode() {
        return colorMode;
    }

    public void attachFile(File design) {
        designs.add(design);
    }

    public String getOrderPath() {
        String pathToLastFile = designs.get(designs.size() - 1).getAbsolutePath();
        orderPath = pathToLastFile.substring(0, pathToLastFile.lastIndexOf("\\"));
        return orderPath;
    }
}
