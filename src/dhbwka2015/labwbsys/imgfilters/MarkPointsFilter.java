package dhbwka2015.labwbsys.imgfilters;

import dhbwka2015.labwbsys.neuronalesnetz.ClassicLearner;
import dhbwka2015.labwbsys.neuronalesnetz.LearnInstance;
import dhbwka2015.labwbsys.neuronalesnetz.Perceptron;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class MarkPointsFilter implements ImageFilterIf {

    @Override
    public void filterImages(BufferedImage in, BufferedImage out,
                             ArrayList<String> parameters) {

        Perceptron perceptron = new Perceptron(3, new ClassicLearner());
        perceptron.name = "RoteAugen-Operator";
        System.out.println(perceptron);
        ArrayList<LearnInstance> samples = new ArrayList<LearnInstance>();

        CsvCommentedReader reader = new CsvCommentedReader(parameters.get(2));
        ArrayList<int[]> pointList;
        ArrayList<Boolean> contrainsList;
        if (reader.readFile()) {
            System.out.println(reader.toString());

            pointList = readPoints(reader.getFileContent());
            contrainsList = readConditions(reader.getFileContent());
        } else {
            System.out.println("Points in CSV could not be read!");
            return;
        }

        WritableRaster inRaster = in.getRaster();
        WritableRaster outRaster = out.getRaster();
        ColorModel inModel = in.getColorModel();
        ColorModel outModel = out.getColorModel();

        for (int i = 0; i < in.getWidth(); ++i) {
            for (int j = 0; j < in.getHeight(); ++j) {
                int px = inModel.getRGB(inRaster.getDataElements(i, j, null));

                Object pxData = outModel.getDataElements(px, null);
                outRaster.setDataElements(i, j, pxData);
            }
        }

        int count = 0;
        for (int[] p : pointList) {
            System.out.println("Working on point " + p[0] + "/" + p[1]);

            RGB px = new RGB(inModel.getRGB(inRaster.getDataElements(p[0], p[1], null)));

            samples.add(new LearnInstance(contrainsList.get(count), px.getHSB().getVector()));

            int pxNew = 0xff000000 | (0xff << 8) | 0xff;

            Object pxData = outModel.getDataElements(pxNew, null);
            outRaster.setDataElements(p[0], p[1], pxData);
            count++;
        }

        perceptron.learn(samples);

        System.out.println("\nFinal Perceptron:");
        System.out.println(perceptron);

        System.out.println("\nRemove Red Eye");
        for (int i = 0; i < in.getWidth(); ++i) {
            for (int j = 0; j < in.getHeight(); ++j) {
                RGB px = new RGB(inModel.getRGB(inRaster.getDataElements(i, j, null)));

                double[] inPer = px.getHSB().getVector();
                if(perceptron.calcStepResult(inPer) > 0.9)
                {
                    System.out.println(i + " " + j);
                    px = new RGB(0xFF, 0xFF, 0xFF, 0xFF);
                }

                Object pxData = outModel.getDataElements(px.getRGB(), null);
                outRaster.setDataElements(i, j, pxData);
            }
        }
    }

    private ArrayList<int[]> readPoints(ArrayList<String[]> data) {
        ArrayList<int[]> res = new ArrayList<int[]>();

        for (String[] sa : data) {
            if (sa.length != 3) {
                continue;
            }

            int[] point = new int[2];
            try {
                int xpos = Integer.parseInt(sa[1].trim());
                int ypos = Integer.parseInt(sa[2].trim());

                point[0] = xpos;
                point[1] = ypos;
            } catch (Exception ex) {
                continue;
            }

            res.add(point);
        }

        return res;
    }

    private ArrayList<Boolean> readConditions(ArrayList<String[]> data) {
        ArrayList<Boolean> res = new ArrayList<Boolean>();

        for (String[] sa : data) {
            if (sa.length != 3) {
                continue;
            }

            boolean contains;

            try {
                contains = Boolean.parseBoolean(sa[0].trim());
            } catch (Exception ex) {
                continue;
            }

            res.add(contains);
        }

        return res;
    }


}
