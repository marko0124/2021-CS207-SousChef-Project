package controllers;
import java.util.*;

import entities.Recipe;
import usecases.RecipeHandler;

public class RecipeController {
    protected ArrayList<ArrayList<String>> recipeRawArray;
    private final RecipeHandler handler;

    public RecipeController() {
        this.recipeRawArray = new ArrayList<>();
        this.handler = new RecipeHandler();
    }

    public boolean addRecipe(String recipeName, String ingrString, String instruction){
        String[] ingrSplit = ingrString.split(" ");
        List<String> a1 = Arrays.asList(ingrSplit);
        ArrayList<String> a2 = new ArrayList<>(a1);
        return handler.addOneRecipe(recipeName, a2, instruction);
    }

    public void initialLoad(ArrayList<String> csvLines){
        for (String i : csvLines){
            // Separating csv lines into an array
            String[] separated = i.split(",,");
            List<String> a1;
            a1 = Arrays.asList(separated);
            ArrayList<String> a2 = new ArrayList<>(a1);
            this.recipeRawArray.add(a2);

        }
        handler.initializeRecipe(this.recipeRawArray);
    }

    public ArrayList<Recipe> recommendRecipe () {
        return handler.recommendRecipe();
    }
}
