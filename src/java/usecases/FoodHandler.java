package usecases;

import entities.Food;
import entities.PerishableFood;
import entities.NonPerishableFood;

import java.time.LocalDate;
import java.util.ArrayList;

public class FoodHandler {
    private static ArrayList<Food> storeFoodList;

    public FoodHandler() {
        storeFoodList = new ArrayList<>();
    }


    /**
     * Create a Food object (Perishable or NonPerishable) based on single_array.
     * @param single_array the food object represented as an array list
     */
    public void createFood(ArrayList<String> single_array){
        String food = single_array.get(0);
        Double quantity = Double.parseDouble(single_array.get(1));
        String measurement = single_array.get(2);

//         If the length of single_array is less than 4, then there is no expiry date for the food. Then we know that
//         it is a NonPerishableFood. Otherwise, we know that the length is greater than four and that it has an expiry
//         date thus it is a PerishableFood.
        if (single_array.size() < 4){
            // Make a NonPerishable food item
            storeFoodList.add(new NonPerishableFood(food, quantity, measurement));
        }else{
            // Make a Perishable food item
            int day = Integer.parseInt(single_array.get(3));
            int month = Integer.parseInt(single_array.get(4));
            int year = Integer.parseInt(single_array.get(5));
            LocalDate local_date = LocalDate.of(day, month, year);

            storeFoodList.add(new PerishableFood(food,quantity, measurement, local_date));
        }
    }


    /**
     * Creates PerishableFood and NonPerishableFood items from an Array of Array of Strings.
     */
    public void initialLoad(ArrayList<ArrayList<String>> multi_array){
        for(ArrayList<String> i:multi_array){
            createFood(i);
        }
    }

    /**
     * Compare the expiry date of the Food object to today's date to return an ArrayList of expired foods called
     * expired_foods.
     * @return expired_foods the ArrayList that lists all the expired foods.
     */
    public ArrayList<Food> getPerishedFoods(){
        ArrayList<Food> expired_foods = new ArrayList<>();
        for (Food food:storeFoodList){
            if (food instanceof PerishableFood){
                PerishableFood new_food = (PerishableFood)food;
                if (new_food.getExpiryStatus()){
                    expired_foods.add(new_food);
                }
            }
        }
        return expired_foods;
    }


    /**
     * Create a getter method so that RecipeHandler can access the array of foods storeFoodList.
     */
    public static ArrayList<String> getStoreFoodList(){
        ArrayList<String> names = new ArrayList<>();
        for(Food foodName : storeFoodList){
            names.add(foodName.getName());
        }
        return names;
    }
}
