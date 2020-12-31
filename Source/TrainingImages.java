import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TrainingImages {
    public ArrayList<Integer> labels;
    public ArrayList<String> pictures;

    public TrainingImages() {
        labels = readTrainingLabels();
        pictures = readTrainingPictures();
    }

    public ArrayList<Integer> readTrainingLabels() {
        ArrayList<Integer> labels = new ArrayList<>();
        try {
            File myObj = new File("../handwritingAI/digitdata/traininglabels");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int intData = Integer.parseInt(data);
                labels.add(intData);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return labels;
    }

    public ArrayList<String> readTrainingPictures() {
        ArrayList<String> images = new ArrayList<>();
        try {
            File myObj = new File("../handwritingAI/digitdata/trainingimages");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String image = "";
                for (int line=0; line<28; line++) {
                    String data = myReader.nextLine();
                    image += data + "\n";
                }
                images.add(image);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return images;
    }




}
