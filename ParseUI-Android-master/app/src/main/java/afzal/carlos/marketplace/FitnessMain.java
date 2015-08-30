package afzal.carlos.marketplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

/**
 * Created by ppl on 5/30/2015.
 */
public class FitnessMain extends Activity implements  View.OnClickListener{

    Button workoutTracker, nutrition, running, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitness_main);

        // if no one logged in
        if (ParseUser.getCurrentUser() == null)
        {
            ParseLoginBuilder builder = new ParseLoginBuilder(FitnessMain.this);
            startActivityForResult(builder.build(), 0);
        }

        workoutTracker = (Button)findViewById(R.id.workoutButton);
        workoutTracker.setOnClickListener(this);

        nutrition = (Button)findViewById(R.id.nutritionButton);
        nutrition.setOnClickListener(this);

        running = (Button)findViewById(R.id.runButton);
        running.setOnClickListener(this);

        profile = (Button)findViewById(R.id.profileButton);
        profile.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {

        switch(v.getId())
        {
            case R.id.workoutButton:
                // if no one logged in
                if (ParseUser.getCurrentUser() == null)
                {
                    Toast.makeText(getApplicationContext(), "You must log in to start working out",
                            Toast.LENGTH_LONG).show();
                }

                else{
                    Intent myIntent = new Intent(FitnessMain.this, CurlActivity.class);
                    FitnessMain.this.startActivity(myIntent);
                }
                break;

            case R.id.nutritionButton:
                if (ParseUser.getCurrentUser() == null)
                {
                    Toast.makeText(getApplicationContext(), "You must log in to start tracking nutrition",
                            Toast.LENGTH_LONG).show();
                }

                else {
                    Intent myIntent = new Intent(FitnessMain.this, NutritionActivity.class);
                    FitnessMain.this.startActivity(myIntent);
                }
                break;

            case R.id.runButton:
                if (ParseUser.getCurrentUser() == null)
                {
                    Toast.makeText(getApplicationContext(), "You must log in to start tracking walking statistics",
                            Toast.LENGTH_LONG).show();
                }

                else{
                    Intent myIntent = new Intent(FitnessMain.this, PedometerActivity.class);
                    FitnessMain.this.startActivity(myIntent);}

                break;

            case R.id.profileButton:
                Intent i = new Intent(FitnessMain.this, Profile.class);
                FitnessMain.this.startActivity(i);
                break;
        }
    }
}
