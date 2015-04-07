package dhbwka2015.labwbsys.imgfilters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GrayArtImageFilter implements ImageFilterIf {

	ImageFilterIf grayConverter, framer;
	
	public GrayArtImageFilter(){
		grayConverter = new GrayScaleFilter();
		framer = new FrameFilter();
	}
	
	@Override
	public void filterImages(BufferedImage in, BufferedImage out,
			ArrayList<String> parameters) {
		
		BufferedImage tempImage = new BufferedImage(in.getWidth(),
				in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		grayConverter.filterImages(in, tempImage, EMPTYPARAMS);
		framer.filterImages(tempImage, out, parameters);
	}

}
