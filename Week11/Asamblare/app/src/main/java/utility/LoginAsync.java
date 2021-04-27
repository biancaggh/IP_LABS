package utility;


import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;

public class LoginAsync extends AsyncTask<String, Integer, Integer> {

    public LoginAsyncResponse delegate = null;
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
