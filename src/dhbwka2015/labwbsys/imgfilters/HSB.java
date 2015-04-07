package dhbwka2015.labwbsys.imgfilters;

import java.awt.*;

public class HSB {
    private float hue;
    private float saturation;
    private float brightness;

    public HSB(float hue, float saturation, float brightness) {
        setHSB(hue, saturation, brightness);
    }

    public void setHSB(float h, float s, float v) {
        setHue(h);
        setSaturation(s);
        setBrightness(v);
    }

    public float getHue() {
        return hue;
    }

    public void setHue(float hue) {
        this.hue = hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public RGB getRGB() {
        return new RGB(Color.HSBtoRGB(getHue(), getSaturation(), getBrightness()));
    }
}
