package dhbwka2015.labwbsys.imgfilters;

/**
 * Created by JCDM on 31.03.2015.
 */
public class RGB {
    private int alpha = 0;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    public RGB(int _a, int _r, int _g, int _b) {
        setRGB(_a, _r, _g, _b);
    }

    public RGB(int _rgb) {
        setRGB(_rgb);
    }

    public int getRGB() {
        return (getAlpha() << 24) | (getRed() << 16) | (getGreen() << 8) | (getBlue() << 0);
    }

    public void setRGB(int _rgb) {
        setAlpha((_rgb >> 24) & 0xff);
        setRed((_rgb >> 16) & 0xff);
        setGreen((_rgb >> 8) & 0xff);
        setBlue((_rgb >> 0) & 0xff);
    }

    public void setRGB(int _a, int _r, int _g, int _b) {
        setAlpha(_a);
        setRed(_r);
        setGreen(_g);
        setBlue(_b);
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
}
