package vn.kjapps.demodishtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Dish> listDish;
    DishAdapter dishAdapter;
    ListView lvDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvDish = (ListView) findViewById(R.id.lvDish);
        listDish = new ArrayList<>();
        listDish.add(new Dish("Rosemary Chicken Noodle Soup", R.drawable.chicken_noodle, R.drawable.high_chicken_noodle,R.drawable.dish1));
        listDish.add(new Dish("Super Easy White Queso With No Velveeta", R.drawable.white_queso, R.drawable.high_queso,R.drawable.dish2));
        listDish.add(new Dish("Soft Buttery Dinner Rolls", R.drawable.soft_buttery, R.drawable.high_soft_buttery,R.drawable.dish3));
        listDish.add(new Dish("Rigatoni With Mushrooms And Rosemary", R.drawable.mushrooms, R.drawable.high_mushrooms,R.drawable.dish4));
        listDish.add(new Dish("Garlic Lovers Salmon", R.drawable.garlic_salmon, R.drawable.high_garlic_salmon,R.drawable.dish5));
        listDish.add(new Dish("Chicken With Pumpkin Seed Sauce", R.drawable.chicken_pumpkin, R.drawable.high_chicken_pumpkin,R.drawable.dish6));
        listDish.add(new Dish("Apple Cider Mezcal Margarita", R.drawable.apple_cider, R.drawable.high_apple_cider,R.drawable.dish7));
        listDish.add(new Dish("30' Sesame Chicken Noodle Stir Fly", R.drawable.sesame_chicken_noodle, R.drawable.high_sesame_chicken_noodle,R.drawable.dish8));
        listDish.add(new Dish("Slow Cooker Crispy Chicken Carnitas", R.drawable.crispy_chicken, R.drawable.high_crispy_chicken,R.drawable.dish9));
        listDish.add(new Dish("Feel Good Pineapple Smoothie", R.drawable.pineapple, R.drawable.high_pineapple,R.drawable.dish10));
        listDish.add(new Dish("Korean Steak Kabobs", R.drawable.steak_kabobs, R.drawable.high_steak_kabobs,R.drawable.dish11));
        dishAdapter = new DishAdapter(this, R.layout.custom_item_list, listDish);
        lvDish.setAdapter(dishAdapter);
    }
}
