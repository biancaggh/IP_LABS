package com.example.medicalapp.utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public interface AsyncResponse {

    void processFinish(Integer output);
    void processFinish(JSONObject output) throws JSONException;
    void processFinish(Boolean output) throws JSONException;
    void processFinish(ArrayList<String> output);
}
