package usecases;
import entities.Recipe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * With a list of recipe creates recipe objects using the entities.Recipe class recipe constructors
 */

public class RecipeHandler {

    private final ArrayList<Recipe> recipeHandlerRecipeList;

    //HashMap<String, ArrayList<Object>> ingredient, String instructions

    public RecipeHandler() {
        this.recipeHandlerRecipeList = new ArrayList<>();
    }


    public int getRecipes(){
        return recipeHandlerRecipeList.size();
    }

    public void initializeRecipe(ArrayList<ArrayList<String>> ListOfRecipes) {
        // Looping through every Recipe on the list.
        for (ArrayList<String> currentRecipe : ListOfRecipes) {

            // Creating a new Recipe object using the constructor in Recipe entity class
            HashMap<String, ArrayList<String>> ingredientsDict = arrayListToDictHelper(currentRecipe);

            Recipe newFood = new Recipe(currentRecipe.get(0), ingredientsDict,
                    currentRecipe.get(currentRecipe.size() - 1));

            // Adding the new Recipe created into the RecipeHandlerRecipeList (The saved Recipe inside RecipeHandler)
            recipeHandlerRecipeList.add(newFood);
        }
    }

    public HashMap<String, ArrayList<String>> arrayListToDictHelper(ArrayList<String> listIngr) {
        HashMap<String, ArrayList<String>> ingredientsDict = new HashMap<>();
        for (int j = 1; j < listIngr.size() - 2; j = j + 3) {
            ArrayList<String> lst = new ArrayList<>();
            lst.add(listIngr.get(j + 1));
            lst.add(listIngr.get(j + 2));
            ingredientsDict.put(listIngr.get(j), lst);
        }
        return ingredientsDict;
    }

    public ArrayList<Recipe> recommendRecipe() {
        //TODO: Create a method that recommends recipe considering
        // 1. Availability of ingredients (This considers Expiry dates and how many ingredients we use from the fridge)
        // 2. Give a score to each of these Recipes
        // 3. Return the number of recipes that the user wants, returning from best score to worst score recipe.
        // Percentage of food
        // 4. Food scores are tracked by:
        //      1. Find all recipes using the specific ingredient.
        //      2. Find the score of each recipe by taking the total number of ingredients that overlap/total number of ingredient
        //      3. Return the Recipe with the highest score.
        int currentRecipeScore = 0;
        HashMap<Integer, ArrayList<String>> RankTracker = new HashMap<>();

        ArrayList<Recipe> RecommendedRecipe = new ArrayList<>();

        for (Recipe currentRecipe : recipeHandlerRecipeList) {
            for (String currentIngredient : currentRecipe.getIngredients().keySet()) {
                if (FoodHandler.getStoreFoodList().contains(currentIngredient)) {
                    currentRecipeScore += 1;
                }


                int currentRecipeWeightedScore = currentRecipeScore / (currentRecipe.getIngredients().keySet().size());
                if (RankTracker.containsKey(currentRecipeWeightedScore)) {
                    RankTracker.get(currentRecipeWeightedScore).add(currentRecipe.getRecipeName());
                }
                ArrayList<String> value = new ArrayList<>();
                value.add(currentRecipe.getRecipeName());
                RankTracker.put(currentRecipeWeightedScore, value);
            }
            TreeSet<Integer> sortedRanks = new TreeSet<>(RankTracker.keySet());
            ArrayList<String> BestRecipeNames = RankTracker.get(sortedRanks.first());
            Recipe BestRecipe = findRecipe(BestRecipeNames.get(0));
            RecommendedRecipe.add(BestRecipe);
        }
        return RecommendedRecipe;
    }



    public ArrayList<Recipe> getAllRecipes() {
        return this.recipeHandlerRecipeList;

    }

    public Recipe findRecipe(String foodName) {

        for (Recipe currentRecipe : getAllRecipes()) {
            if (currentRecipe.getRecipeName().equals(foodName)) {
                return currentRecipe;
            }

        }
        return null;
    }

    public boolean addOneRecipe(String recipeName, ArrayList<String> ingredientList, String instructions) {
        try {
            HashMap<String, ArrayList<String>> map = arrayListToDictHelper(ingredientList);
            Recipe newFood = new Recipe(recipeName, map, instructions);
            // Adding the new Recipe created into the RecipeHandlerRecipeList (The saved Recipe inside RecipeHandler)
            recipeHandlerRecipeList.add(newFood);
            return true;
        } catch(Exception IndexOutOfBoundsException) {
            return false;
        }
        return null;
    }

    }
}
