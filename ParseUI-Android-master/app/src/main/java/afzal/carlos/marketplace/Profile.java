package afzal.carlos.marketplace;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by afzal8690 on 4/27/2015.
 */
public class Profile extends Activity {

    ListView listview;
    List<ParseObject> ob, ob2, ob3;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    Date date;
    Format formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        new RemoteDataTask().execute();
     //   new RemoteDataTask2().execute();
     //   new RemoteDataTask3().execute();









        listview = (ListView)findViewById(R.id.itemList);

    }

    // for getting the Nutritional stats
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        // RemoteDataTask AsyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Profile.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Your Meals");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "Nutrition");
            query.orderByDescending("_created_at");
            try {
                ob = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>(
                    "Pedometer"
            );

            query1.orderByDescending("_created_at");

            try {
                ob2 = query1.find();
            }
            catch (ParseException e){

            }


            ParseQuery<ParseObject> query3 = new ParseQuery<ParseObject>("WeightLifting");

            query3.orderByDescending("_created_at");

            try{
                ob3 = query3.find();
            }
            catch (ParseException e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Pass the results into an ArrayAdapter
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.listview_item);
            // Retrieve object "name" from Parse.com database
            for (ParseObject country : ob) {
                adapter.add("You Ate: " + country.getString("meal") + "\n   Which Contained: " + country.getInt("calories") + "\n\n");
               /* ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("objectId", (String)country.get("userID"));
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            for (ParseObject obj : objects)
                            {
                                date = obj.getCreatedAt();
                                String s = formatter.format(date);
                                adapter.add("On " + s + "\n   You Ate: " + obj.getString("meal") + "\n   Which Contained: " + obj.getString("calories") );
                            }
                        }
                    }
                }); */
            }

            for (ParseObject country2 : ob2)
            {
                adapter.add("You walked: " + country2.getInt("Steps"));
            }


            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }//https://www.parse.com/questions/best-way-to-send-push-notification-to-specific-device
    }



    // for getting the Running stats
    private class RemoteDataTask2 extends AsyncTask<Void, Void, Void> {
        // RemoteDataTask AsyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Profile.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Your Walking Statistics");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "RunInfo");
            query.orderByDescending("_created_at");
            try {
                ob2 = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Pass the results into an ArrayAdapter
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.listview_item);
            // Retrieve object "name" from Parse.com database
            for (ParseObject country : ob2) {

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("objectId", (String)country.get("userID"));
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            for (ParseObject obj : objects)
                            {
                                date = obj.getCreatedAt();
                                String s = formatter.format(date);
                                adapter.add("On " + s + "\n   You Walked: " + obj.getString("steps") + "steps" );
                            }
                        }
                    }
                });
            }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }//https://www.parse.com/questions/best-way-to-send-push-notification-to-specific-device
    }




    // for getting the WeightLifting stats
    private class RemoteDataTask3 extends AsyncTask<Void, Void, Void> {
        // RemoteDataTask AsyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Profile.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Your WeightLifting Stats");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the class table named "Country" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "WeightLift");
            query.orderByDescending("_created_at");
            try {
                ob3 = query.find();
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Pass the results into an ArrayAdapter
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    R.layout.listview_item);
            // Retrieve object "name" from Parse.com database
            for (ParseObject country : ob3) {

                ParseQuery<ParseUser> query = ParseUser.getQuery();
                query.whereEqualTo("objectId", (String)country.get("userID"));
                query.findInBackground(new FindCallback<ParseUser>() {
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e == null) {
                            for (ParseObject obj : ob3)
                            {
                                date = obj.getCreatedAt();
                                String s = formatter.format(date);
                                adapter.add("On " + s + "\n   You lifted: " + obj.getString("weight") + "\n   With " + obj.getString("reps") + " reps and " + obj.getString("sets"));
                            }
                        }
                    }
                });
            }
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }//https://www.parse.com/questions/best-way-to-send-push-notification-to-specific-device
    }


}