package afzal.carlos.marketplace;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutActivity extends Activity {

    public TextView mInputWeight, mWeightEntry, mRepsLabel, mRepsCount, mSetsLabel, mSetsCount;
    public Button mSaveButton, mSetButton;
    public String mWeight, mReps, mSets;
    public float currentZPos, oldZPos, currentXPos, oldXPos, acceleration;
    public int  numReps, numSets, changeReq, changeReqX;
    private SensorManager mSensorManager;

    //USE MYO ARMBAND TO UPDATE mReps AND mSets

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        mInputWeight = (TextView) findViewById(R.id.input_weight);
        mWeightEntry = (TextView) findViewById(R.id.weight_entry);
        mRepsLabel = (TextView) findViewById(R.id.reps_label);
        mRepsCount = (TextView) findViewById(R.id.reps_count);
        mSetsLabel = (TextView) findViewById(R.id.sets_label);
        mSetsCount = (TextView) findViewById(R.id.sets_count);

        numSets = 0;

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mWeight = (String) mWeightEntry.getText();
                mReps = (String) mRepsCount.getText();
                mSets = (String) mSetsCount.getText();
                //PARSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


            }
        });

        mSetButton = (Button) findViewById(R.id.set_button);
        mSetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRepsCount.setText("0");
                numReps = 0;
                numSets++;
                mSetsCount.setText(Integer.toString(numSets));


            }
        });

        currentZPos = 0;
        oldZPos = 0;
        numReps = 0;
        acceleration = 0.00f;
        changeReq = 20;
        changeReqX = 10;

        enableAccelerometer();
    }

    private void enableAccelerometer(){
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        SensorEventListener mSensorEventListener = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent mSensorEvent) {
                float xVal = mSensorEvent.values[0];
                float yVal = mSensorEvent.values[1];
                float zVal = mSensorEvent.values[2];

                currentZPos = zVal;
                currentXPos = xVal;

                //if step was taken
                if(/*Math.abs*/(currentZPos-oldZPos) > changeReq){
                    numReps++;
                    mRepsCount.setText(String.valueOf(numReps));
                }

                if(Math.abs(currentXPos-oldXPos) > changeReqX){

                }

                oldZPos = yVal;
                oldXPos = xVal;
            }


            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub
            }
        };
        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);



    }

}