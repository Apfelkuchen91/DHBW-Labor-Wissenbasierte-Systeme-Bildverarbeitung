package dhbwka2015.labwbsys.imgfilters;

import com.sun.javafx.scene.layout.region.Margins;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class BorderFilter implements ImageFilterIf {

    @Override
    public void filterImages(BufferedImage in, BufferedImage out, ArrayList<String> parameters) {
        WritableRaster inRaster = in.getRaster();
        WritableRaster outRaster = out.getRaster();

        ColorModel model = in.getColorModel();
        ColorModel outmodel = out.getColorModel();

        int borderWidth = Integer.parseInt(parameters.get(0));
        RGB borderRGB = new RGB(Integer.parseInt(parameters.get(1)), Integer.parseInt(parameters.get(2)), Integer.parseInt(parameters.get(3)), Integer.parseInt(parameters.get(4)));
        int iImageStart = borderWidth;
        int iImageEnde = in.getWidth() - borderWidth;
        int jImageStart = borderWidth;
        int jImageEnde = in.getHeight() - borderWidth;

        for (int i = 0; i < iImageStart; ++i) {
            for (int j = 0; j < in.getHeight(); ++j) {
                outRaster.setDataElements(i, j, outmodel.getDataElements(borderRGB.getRGB(), null));
            }
        }

        for (int i = iImageStart; i < iImageEnde; ++i) {
            for (int j = 0; j < jImageStart; ++j) {

                outRaster.setDataElements(i, j, outmodel.getDataElements(borderRGB.getRGB(), null));
            }

            for (int j = jImageStart; j < jImageEnde; ++j) {

                Object inPix = inRaster.getDataElements(i, j, null);

                RGB rgb = new RGB(model.getRGB(inPix));

                outRaster.setDataElements(i, j, outmodel.getDataElements(rgb.getRGB(), null));
            }

            for (int j = jImageEnde; j < in.getHeight(); ++j) {


                outRaster.setDataElements(i, j, outmodel.getDataElements(borderRGB.getRGB(), null));
            }
        }

        for (int i = iImageEnde; i < in.getWidth(); ++i) {
            for (int j = 0; j < in.getHeight(); ++j) {


                outRaster.setDataElements(i, j, outmodel.getDataElements(borderRGB.getRGB(), null));
            }
        }
    }

}
