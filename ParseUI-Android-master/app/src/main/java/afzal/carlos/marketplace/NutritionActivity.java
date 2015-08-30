package afzal.carlos.marketplace;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NutritionActivity extends Activity {

    public TextView mEnterMeal, mEnterCalories;
    public EditText	mInputMeal, mInputCalories;
    public Button mSaveMealButton;
    String mMealString;
    int mCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_layout);

        mEnterMeal = (TextView) findViewById(R.id.enter_meal);
        mEnterCalories = (TextView) findViewById(R.id.enter_calories);
        mInputMeal = (EditText) findViewById(R.id.input_meal);
        mInputCalories = (EditText) findViewById(R.id.input_calories);
        mSaveMealButton = (Button) findViewById(R.id.submit_button);
        mSaveMealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mMealString = mInputMeal.getText().toString();

                mCalories = Integer.parseInt(mInputCalories.getText().toString());
                NutritionalInfo info = new NutritionalInfo();

                info.setCalories(mCalories);
                info.setMeal(mMealString);
                info.setID(ParseUser.getCurrentUser().getObjectId());

                info.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(getApplicationContext(), "Meal and Calories Successfully saved!",
                                Toast.LENGTH_LONG).show();
                    }
                });
                //send to parse
            }
        });

    }

}
