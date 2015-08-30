package afzal.carlos.marketplace;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by afzal8690 on 5/30/2015.
 */
public class NutritionalDisplay extends Activity {

    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter, adapter2;


    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date date;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.);
        new RemoteDataTask().execute();


        //listview = (ListView)findViewById(R.id.listView1);
    }


    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        // RemoteDataTask AsyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Create a progressdialog
            mProgressDialog = new ProgressDialog(NutritionalDisplay.this);
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
            query.whereEqualTo("setID", ParseUser.getCurrentUser().getObjectId());
            query.orderByDescending("_created_at");


            try {
                ob = query.find();
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

            adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_item);

            // Retrieve object "name" from Parse.com database
            for (ParseObject country : ob) {
                date = country.getCreatedAt();
                adapter.add("On " + formatter.format(date) + "\nYou ate " + country.getString("meal")
                            + "\nWhich consisted of " + country.getString("calories"));
            }

            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);

            // Close the progressdialog
            mProgressDialog.dismiss();
            // Capture button clicks on ListView items
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // ob.get(position).deleteInBackground()


                }
            });
        }
    }





}
