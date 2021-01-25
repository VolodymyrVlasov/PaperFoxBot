package models.products;

import java.io.File;

public abstract class PrintingProduct {
    private double width;
    private double height;
    private double mediaDensity;
    private double price;
    private int quantity;
    private boolean colorMode;
    private String name;
    private File design;


    @Override
    public String toString() {
        return "PrintingProduct{" +
                "width=" + width +
                ", height=" + height +
                ", mediaDensity=" + mediaDensity +
                ", price=" + price +
                ", quantity=" + quantity +
                ", colorMode=" + colorMode +
                ", name='" + name + '\'' +
                ", design=" + design +
                '}';
    }

    public Integer getQuantityPerSheet(){
        return 0;
    }

    public Integer getTotalSheetsQuantity() {
        return 0;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMediaDensity() {
        return mediaDensity;
    }

    public void setMediaDensity(double mediaDensity) {
        this.mediaDensity = mediaDensity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isColorMode() {
        return colorMode;
    }

    public void setColorMode(boolean colorMode) {
        this.colorMode = colorMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getDesign() {
        return design;
    }

    public void attachDesign(File design) {
        this.design = design;
    }
}
