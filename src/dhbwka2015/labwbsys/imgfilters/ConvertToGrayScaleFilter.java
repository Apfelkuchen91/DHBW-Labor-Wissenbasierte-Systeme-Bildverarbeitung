package dhbwka2015.labwbsys.imgfilters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class ConvertToGrayScaleFilter implements ImageFilterIf {

	public void filterImages(BufferedImage in, BufferedImage out, ArrayList<String> parameters) {
		WritableRaster inRaster = in.getRaster();
		WritableRaster outRaster = out.getRaster();
		
		ColorModel inModel = in.getColorModel();
		ColorModel outModel = out.getColorModel();

		for (int i = 0; i < in.getWidth(); ++i) {
			for (int j = 0; j < in.getHeight(); ++j) {
				int pxOld = inModel.getRGB(inRaster.getDataElements(i, j, null));

				int a = pxOld & 0xff000000;
				int r = (pxOld >> 16) & 0xff;
				int g = (pxOld >> 8) & 0xff;
				int b = pxOld & 0xff;

				int val = (r + g + b) / 3;

				// alternative conversion to grey scales
				// val = (r * 77 + g * 151 + b * 28) >> 8; // NTSC luma

				int pxNew = a | (val << 16) | (val << 8) | val;

				Object pxData = outModel.getDataElements(pxNew, null);

				outRaster.setDataElements(i, j, pxData);
			}
		}
	}

}
