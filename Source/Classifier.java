import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Classifier {

    public Classifier(TrainingImages information) {
        ArrayList<Integer> labels = information.labels;
        ArrayList<boolean[][]> shaded = shading(information);
        HashMap<Integer, double[][]> probability = probabilityCalculator(labels, shaded);
    }

    public HashMap<Integer, double[][]> probabilityCalculator
            (ArrayList<Integer> labels, ArrayList<boolean[][]> shading) {

        int[][] numberOfShaded = new int[28][28];
        HashMap<Integer, int[][]> countShaded = new HashMap<>();

        for (int index=0; index<labels.size(); index++) {
            int label = labels.get(index);
            boolean[][] shade = shading.get(index);
            int[][] count = new int[28][28];

            for (int x=0; x<28; x++) {
                for (int y=0; y<28; y++) {
                    if (shade[x][y]) {
                        numberOfShaded[x][y]++;
                        count[x][y]++;
                    }
                }
            }

            for (int x=0; x<28; x++) {
                for (int y=0; y<28; y++) {
                    countShaded.get(label)[x][y] += count[x][y];
                }
            }
        }

        HashMap<Integer, double[][]> probabilities = new HashMap<>();
        for (int integer=0; integer<10; integer++) {
            double[][] numProbability = new double[28][28];
            int[][] numShaded = countShaded.get(integer);
            for (int x=0; x<28; x++) {
                for (int y=0; y<28; y++) {
                    double prob = numShaded[x][y]/ ((double) numberOfShaded[x][y]);
                    numProbability[x][y] = prob;
                }
            }
            probabilities.put(integer, numProbability);
        }

        return probabilities;
    }

    public ArrayList<boolean[][]> shading(TrainingImages information) {
        ArrayList<String> pictures = information.pictures;
        ArrayList<boolean[][]> shadedImages = new ArrayList<>();
        for (String picture : pictures) {
            boolean[][] shaded = new boolean[28][28];
            int character = 0;
            for (int x=0; x<28; x++) {
                for (int y=0; y<28; y++) {
                    Character charValue = picture.charAt(character);
                    if (charValue.equals('+') || charValue.equals('#')) {
                        shaded[x][y] = true;
                    } else {
                        shaded[x][y] = false;
                    }
                    character++;
                }
            }
            shadedImages.add(shaded);
        }
        return shadedImages;
    }

}
