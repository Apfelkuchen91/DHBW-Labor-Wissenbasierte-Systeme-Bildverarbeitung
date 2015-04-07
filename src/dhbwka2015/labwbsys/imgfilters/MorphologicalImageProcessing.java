package dhbwka2015.labwbsys.imgfilters;


import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.*;


public abstract class MorphologicalImageProcessing implements ImageFilterIf {

    @Override
    public void filterImages(BufferedImage in, BufferedImage out, ArrayList<String> parameters) {
        WritableRaster inRaster = in.getRaster();
        WritableRaster outRaster = out.getRaster();

        ColorModel model = in.getColorModel();
        ColorModel outmodel = out.getColorModel();

        for (int i = 1; i < in.getWidth() - 1; ++i) {
            for (int j = 1; j < in.getHeight() - 1; ++j) {

                Object inPix = inRaster.getDataElements(i, j, null);

                RGB rgb = new RGB(model.getRGB(inPix));
                int val = rgb.getBin() ? 0xFF : 0;

                RGB outRgb = new RGB(rgb.getAlpha(), val, val, val);

                if (getValueArround(in, i, j)) {
                    int valare = rgb.getBin() ? 0 : 0xFF;
                    outRgb = new RGB(rgb.getAlpha(), valare, valare, valare);
                }

                outRaster.setDataElements(i, j, outmodel.getDataElements(outRgb.getRGB(), null));
            }
        }
    }

    boolean getValueArround(BufferedImage in, int i, int j) {
        WritableRaster inRaster = in.getRaster();
        ColorModel model = in.getColorModel();
        Object middlePix = inRaster.getDataElements(i, j, null);
        RGB masterRgb = new RGB(model.getRGB(middlePix));
        int compCount = 0;

        Vector<RGB> surroundRgb = new Vector<RGB>();
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i, j - 1, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i + 1, j - 1, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i + 1, j, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i + 1, j + 1, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i, j + 1, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i - 1, j + 1, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i - 1, j, null))));
        surroundRgb.add(new RGB(model.getRGB(inRaster.getDataElements(i - 1, j - 1, null))));

        for (RGB compRgb : surroundRgb) {
            if (compRgb.getBin() == masterRgb.getBin()) {
                compCount++;
            }
        }

        return isAccepted(compCount);
    }

    abstract boolean isAccepted(int compCount);
}
