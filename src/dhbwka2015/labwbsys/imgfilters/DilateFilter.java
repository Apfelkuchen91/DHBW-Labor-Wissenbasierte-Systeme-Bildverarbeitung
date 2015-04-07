package dhbwka2015.labwbsys.imgfilters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;


public class DilateFilter extends MorphologicalImageProcessing {

    @Override
    boolean isAccepted(int compCount) {
        return compCount > 1;
    }
}
