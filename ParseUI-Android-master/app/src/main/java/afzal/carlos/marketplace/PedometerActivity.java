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
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PedometerActivity extends Activity {

    public TextView mStepCount;
    public Button mSaveButton;
    public float currentYPos, oldYPos, acceleration;
    public int stepCount, changeReq;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        mStepCount = (TextView) findViewById(R.id.number_steps);
        mSaveButton = (Button)	findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PedoInfo info = new PedoInfo();

                info.setSteps(stepCount);
                info.setUserID(ParseUser.getCurrentUser().getObjectId());

                info.saveInBackground(new SaveCallback() {
                                          @Override
                                          public void done(ParseException e) {
                                              Toast.makeText(getApplicationContext(), "Steps Saved!", Toast.LENGTH_LONG).show();
                                          }
                                      });

                        resetSteps();
            }
        });

        currentYPos = 0;
        oldYPos = 0;
        stepCount = 0;
        acceleration = 0.00f;
        changeReq = 10;

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

                currentYPos = yVal;

                //if step was taken
                if(Math.abs(currentYPos-oldYPos) > changeReq){
                    stepCount++;
                    mStepCount.setText(String.valueOf(stepCount));
                }

                oldYPos = yVal;
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub
            }
        };
        mSensorManager.registerListener(mSensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);



    }

	/*public void onSensorChanged(SensorEvent mSensorEvent){
		float xVal = mSensorEvent.values[0];
		float yVal = mSensorEvent.values[1];
		float zVal = mSensorEvent.values[2];

		currentYPos = yVal;

		//if step was taken
		if(Math.abs(currentYPos-oldYPos) > changeReq){
			stepCount++;
			mStepCount.setText(String.valueOf(stepCount));
		}

		oldYPos = yVal;
	}*/

    public void resetSteps(){
        stepCount = 0;
        mStepCount.setText(String.valueOf(stepCount));
    }

}