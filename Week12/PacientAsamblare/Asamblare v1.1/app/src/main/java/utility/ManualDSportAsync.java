package utility;



import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class ManualDSportAsync extends AsyncTask<String, Integer, Boolean> {

    public AsyncResponse delegate = null;

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            ClientCommunicationHandler.sendSport(strings[0], strings[1], strings[2]);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Boolean result) {
        try {
            delegate.processFinish(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}