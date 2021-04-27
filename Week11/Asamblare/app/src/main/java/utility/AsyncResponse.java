package utility;

import org.json.JSONException;
import org.json.JSONObject;

public interface AsyncResponse {

    void processFinish(Integer output);
    void processFinish(JSONObject output) throws JSONException;
}
