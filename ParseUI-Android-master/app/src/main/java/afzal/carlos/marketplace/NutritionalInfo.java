package afzal.carlos.marketplace;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by afzal8690 on 5/30/2015.
 */
@ParseClassName("Nutrition")
public class NutritionalInfo extends ParseObject {

    public NutritionalInfo() {
    }

    public void setCalories(int calories) {
        put("calories", calories);
    }

    public void setMeal(String meal) {
        put("meal", meal);
    }


    public void setID(String id){
        put("userID", id);
    }
}