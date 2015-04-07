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

		float hueMin = 0.0f;
		float hueMax = 0.2f;
		float saturationMin = 0.7f;
		float saturationMax = 0.9f;
		float brightnessMin = 0.3f;
		float brightnessMax = 0.5f;

		for (int i = 0; i < in.getWidth(); ++i) {
			for (int j = 0; j < in.getHeight(); ++j) {

				Object inPix = inRaster.getDataElements(i, j, null);
				
				RGB rgb = new RGB(model.getRGB(inPix));
				HSB hsb = rgb.getHSB();

				if(		(hueMin <= hsb.getHue() && hsb.getHue() <= hueMax)
					&&	(saturationMin <= hsb.getSaturation() && hsb.getSaturation() <= saturationMax)
					&&	(brightnessMin <= hsb.getBrightness() && hsb.getBrightness() <= brightnessMax)	)
				{
					rgb = new RGB(0xFF, 0xFF, 0xFF, 0xFF);
				}
				
				outRaster.setDataElements(i, j, outmodel.getDataElements(rgb.getRGB(), null));
			}
		}
	}

}
