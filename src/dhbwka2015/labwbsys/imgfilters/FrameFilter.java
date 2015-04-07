package dhbwka2015.labwbsys.imgfilters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class FrameFilter implements ImageFilterIf {

	@Override
	public void filterImages(BufferedImage in, BufferedImage out,
			ArrayList<String> parameters) {
		WritableRaster inRaster = in.getRaster();
		WritableRaster outRaster = out.getRaster();

		ColorModel model = in.getColorModel();
		ColorModel outmodel = out.getColorModel();

		int width, frameColor;
		try {
			width = Integer.parseInt(parameters.get(0));
			frameColor = Integer.decode(parameters.get(1));
			frameColor = 0xff000000 | frameColor;
		} catch (Exception ex) {
			width = 10;
			frameColor = 0xff000000;
		}

		for (int i = 0; i < in.getWidth(); ++i) {
			for (int j = 0; j < in.getHeight(); ++j) {

				int rgbNew;
				if (i < width || i > (in.getWidth() - width) || j < width
						|| j > (in.getHeight() - width)) {
					rgbNew = frameColor;
				} else {
					Object inPix = inRaster.getDataElements(i, j, null);

					int rgb = model.getRGB(inPix);

					int a = rgb & 0xff000000;
					int r = (rgb & 0x00ff0000) >> 16;
					int g = (rgb & 0x0000ff00) >> 8;
					int b = rgb & 0x000000ff;

					int val = (r + g + b) / 3;

					rgbNew = a | (val << 16) | (val << 8) | val;
				}

				outRaster.setDataElements(i, j,
						outmodel.getDataElements(rgbNew, null));
			}
		}

	}

}
