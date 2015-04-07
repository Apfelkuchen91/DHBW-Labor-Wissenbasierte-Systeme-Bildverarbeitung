package dhbwka2015.labwbsys.imgfilters;



public class OutlineFilter extends MorphologicalImageProcessing {

    @Override
    boolean isAccepted(int compCount) {
        return compCount == 8;
    }
}
