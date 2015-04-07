package dhbwka2015.labwbsys.imgfilters;


public class ErodeFilter extends MorphologicalImageProcessing{
    @Override
    boolean isAccepted(int compCount) {
        return compCount >= 8;
    }
}
