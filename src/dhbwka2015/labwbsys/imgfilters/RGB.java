package dhbwka2015.labwbsys.imgfilters;

import java.awt.*;

/**
 * Created by JCDM on 31.03.2015.
 */
public class RGB {
    private int alpha = 0;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public RGB(int rgb) {
        setRGB(rgb);
    }

    public RGB(int a, int r, int g, int b) {
        setRGB(a, r, g, b);
    }

    public int getRGB() {
        return (getAlpha() << 24) | (getRed() << 16) | (getGreen() << 8) | (getBlue() << 0);
    }

    public void setRGB(int rgb) {
        setAlpha((rgb >> 24) & 0xFF);
        setRed((rgb >> 16) & 0xFF);
        setGreen((rgb >> 8) & 0xFF);
        setBlue(rgb & 0xFF);
    }

    public void setRGB(int a, int r, int g, int b) {
        setAlpha(a);
        setRed(r);
        setGreen(g);
        setBlue(b);
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    private int getGeayValue() {
        return (getRed() + getGreen() + getBlue()) / 3;
    }

    public RGB getGray() {

        return new RGB(getAlpha(), getGeayValue(), getGeayValue(), getGeayValue());
    }

    public HSB getHSB() {
        float[] hsb = new float[3];
        Color.RGBtoHSB(getRed(), getGreen(), getBlue(), hsb);
        return new HSB(hsb[0], hsb[1], hsb[2]);
    }

    public boolean isEqual(RGB compare) {
        return (getAlpha() == compare.getAlpha())
                && (getRed() == compare.getRed())
                && (getGreen() == compare.getGreen())
                && (getBlue() == compare.getBlue());
    }

    public boolean getBin() {
        return (getGeayValue() > 0xF);
    }
}
