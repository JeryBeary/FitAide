package afzal.carlos.marketplace;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class FitnessApplication extends Application {
	
	@Override
    public void onCreate()
    {
        super.onCreate();

        ParseObject.registerSubclass(NutritionalInfo.class);

        Parse.initialize(this, "CVDc0gZcyWOhSwhxuZDsbWNh23HXyOjtUmse2KKb", "2rXXd1eDugeQ4RImZsInXrW70dq02HMWXXdlNU3E");

        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

    }
}
