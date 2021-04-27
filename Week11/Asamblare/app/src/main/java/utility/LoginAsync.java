package utility;



import android.os.AsyncTask;
import org.json.JSONException;

public class LoginAsync extends AsyncTask<String, Integer, Integer> {

    public AsyncResponse delegate = null;
    protected int value;
    @Override
    protected Integer doInBackground(String... strings) {
        try {
            value= CCH.login(strings[0],strings[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Integer result) {
        delegate.processFinish(result);
    }

}
