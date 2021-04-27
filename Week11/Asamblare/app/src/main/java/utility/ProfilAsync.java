package utility;



import android.os.AsyncTask;

import org.json.JSONObject;

public class ProfilAsync extends AsyncTask<String, Integer, JSONObject> {

    public AsyncResponse delegate = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject= CCH.getPacient(strings[0]);
        return jsonObject;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Integer result) {
        delegate.processFinish(result);
    }

}