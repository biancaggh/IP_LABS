//package utility;
//
//
//
//import android.os.AsyncTask;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class ManualDHRAsync extends AsyncTask<JSONObject, Integer, JSONObject> {
//
//    public AsyncResponse delegate = null;
//
//    @Override
//    protected JSONObject doInBackground(JSONObject ... JSONObjects) {
//        JSONObject jsonObject= ClientCommunicationHandler.send(JSONObjects[0]);
//        return jsonObject;
//    }
//
//    protected void onProgressUpdate(Integer... progress) {
//    }
//
//    protected void onPostExecute(JSONObject jsonObject) {
//        try {
//            delegate.processFinish(jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//*/}