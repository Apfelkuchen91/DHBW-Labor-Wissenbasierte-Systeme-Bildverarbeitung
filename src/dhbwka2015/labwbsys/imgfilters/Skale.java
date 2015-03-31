package dhbwka2015.labwbsys.imgfilters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Skale implements ImageFilterIf {

	@Override
	public void filterImages(BufferedImage in, BufferedImage out, ArrayList<String> parameters) {
		WritableRaster inRaster = in.getRaster();
		WritableRaster outRaster = out.getRaster();

		ColorModel model = in.getColorModel();
		ColorModel outmodel = out.getColorModel();

		int faktor = Integer.parseInt(parameters.get(0));

		for (int i = 0; i < in.getWidth(); i+=faktor) {
			for (int j = 0; j < in.getHeight(); j+=faktor) {

				Object inPix = inRaster.getDataElements(i, j, null);
				
				int rgb = model.getRGB(inPix);
				
				outRaster.setDataElements(i/faktor, j/faktor, outmodel.getDataElements(rgb, null));
			}
		}
	}

}
