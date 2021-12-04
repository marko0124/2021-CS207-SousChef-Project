package com.example.foodapp;

import adapters.Adapter;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    public FoodFragment() {
        // Required empty public constructor
    }

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_food, container, false);

//        ArrayList<String> given_recipes = CommandInput.getRecipeRecommendation();

        ArrayList<String> given_foods = new ArrayList<>(Arrays.asList("meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat", "meat"));

        LinearLayout foodList = view.findViewById(R.id.food_list);
        for (String food : given_foods) {
            System.out.println(food);

            TextView textView = new TextView(getContext());
            textView.setText(food);
            textView.setTextSize(24);
            textView.setGravity(Gravity.TOP|Gravity.START);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            foodList.addView(textView);
        }

//        TextView testText = new TextView(getContext());
//        testText.setText("test text lol");
//        recipeList.addView(testText);

        return view;

    }
}