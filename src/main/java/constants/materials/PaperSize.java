package constants.materials;

public class PaperSize {
    public static final String A6 = "A6";
    public static final String A5 = "A5";
    public static final String A4 = "A4";
    public static final String A3 = "A3";
    public static final String A2 = "A2";
    public static final String A1 = "A1";
    public static final String A0 = "A0";
    public static final String SRA4 = "SRA4";
    public static final String SRA3 = "SRA3";

    private double width;
    private double height;

    public PaperSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public PaperSize getPaperSize() {
        return this;
    }
}
