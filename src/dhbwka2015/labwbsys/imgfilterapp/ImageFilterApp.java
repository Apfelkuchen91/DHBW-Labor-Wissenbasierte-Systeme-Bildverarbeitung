package dhbwka2015.labwbsys.imgfilterapp;

import dhbwka2015.labwbsys.imgfilters.*;


public class ImageFilterApp {

    static String DefaultImageFile = "data/redeye1.jpg";

    public static void main(String[] args) {
        ImageFilterIf filter = null;

//		filter = new CopyFilter();
//		filter = new UnknownFilter();
//		filter = new GrayScaleFilter();
//		filter = new FrameFilter();
//		filter = new GrayArtImageFilter();
//      filter = new SegmentColorFilter();
        filter = new OutlineFilter();

        FilteredImageWindow app = new FilteredImageWindow(filter, DefaultImageFile);
        app.setTitle("Image Filter Test Application");
    }

}

