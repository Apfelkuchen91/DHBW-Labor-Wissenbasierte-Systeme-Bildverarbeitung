package dhbwka2015.labwbsys.imgfilters;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class SegmentColorFilter implements ImageFilterIf {

	@Override
	public void filterImages(BufferedImage in, BufferedImage out, ArrayList<String> parameters) {
		WritableRaster inRaster = in.getRaster();
		WritableRaster outRaster = out.getRaster();

		ColorModel model = in.getColorModel();
		ColorModel outmodel = out.getColorModel();
		
		for (int i = 0; i < in.getWidth(); ++i) {
			for (int j = 0; j < in.getHeight(); ++j) {

				Object inPix = inRaster.getDataElements(i, j, null);
				
				RGB rgb = new RGB(model.getRGB(inPix));
				Color color = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getAlpha());
				color.getHSBColor();
				
				outRaster.setDataElements(i, j, outmodel.getDataElements(rgb, null));
			}
		}
	}

}
