package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataParser {
    public static final String FOOD_FILE = "src/data/fooddata.csv";
    public static final String RECIPE_FILE = "src/data/recipedata.csv";

    /**
     * Write to the data file according to the file specified
     *
     * @param data The string that needs to be added to the csv files
     * @param useFood True for when adding to fooddata and False for adding to recipedata
     * @throws IOException exception for writing file errors
     */
    public static void writeToFile(String data, boolean useFood) throws IOException {
        FileWriter fw;
        if (useFood){ fw = new FileWriter(FOOD_FILE, true); }
        else { fw = new FileWriter(RECIPE_FILE, true); }

        fw.write(data + "\n");
        fw.close();
    }

    /**
     * Reads all lines in a specified csv file
     *
     * @param useFood true for reading from fooddata, false for recipedata
     * @return An ArrayList of strings for every line in the csv file that was read
     * @throws IOException exception for writing file errors
     */
    public static ArrayList<String> readFile(boolean useFood) throws IOException {
        FileReader fr;
        if (useFood){ fr = new FileReader(FOOD_FILE); }
        else { fr = new FileReader(RECIPE_FILE); }
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String currentLine = br.readLine();
        while (currentLine != null) {
            lines.add(currentLine);
            currentLine = br.readLine();
        }
        br.close();
        fr.close();
        return lines;
    }

    /**
     * Deletes a specified row from fooddata
     * @param deletedFoodIndex an integer specifying the row to be deleted in fooddata
     * @throws IOException exception for writing file errors
     */
    public static void deleteRowFromFoodFile(int deletedFoodIndex) throws IOException {
        ArrayList<String> foodFile = readFile(true);
        foodFile.remove(deletedFoodIndex);
        clearFile(true);
        for (String item: foodFile){
            writeToFile(item, true);
        }
    }

    /**
     * Clears the content of the specified csv file
     * @param useFood true if clearing fooddata, false if clearing recipedata
     * @throws IOException exception for writing file errors
     */
    private static void clearFile(boolean useFood) throws IOException {
        FileWriter fw;
        if (useFood){
            fw = new FileWriter(FOOD_FILE, false);
        }
        else {
            fw = new FileWriter(RECIPE_FILE, false);
        }
        fw.close();
    }
}
