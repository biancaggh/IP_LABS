package utility;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilDAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONObject jsonObject= ClientCommunicationHandler.getDoctor(strings[0]);
        System.out.println(jsonObject);
        return jsonObject;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(JSONObject jsonObject) {
        try {
            delegate.processFinish(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}