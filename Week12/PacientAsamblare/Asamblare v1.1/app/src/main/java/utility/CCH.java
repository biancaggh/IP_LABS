package utility;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CCH {

    //static private String host = "http://localhost:8080";
    static private String host = "https://radiant-bayou-97811.herokuapp.com";

    //GET REQUESTS
    public static Integer login(String username, String password) throws JSONException {
        String tempurl = host + "/api/user/login/" + username + "/" + password;
        JSONObject jsonObject = getRequest(tempurl);
        System.out.println(jsonObject);
        System.out.println(tempurl);

        if (jsonObject.has("login"))
            if (jsonObject.get("login").equals("true"))
                return 1;
            else
                return 0;
        else
            return 0;
    }

    public static JSONObject getPacient(String username) {
        String tempurl = host + "/api/user/getPacient/" + username;

        return getRequest(tempurl);
    }

    public static JSONObject getIstoric(String username) {
        String tempurl = host + "/api/user/pacient/istoric/" + username;
        return getRequest(tempurl);
    }

    public static JSONObject getDiagnostic(String username) {
        String tempurl = host + "/api/user/pacient/diagnostic/" + username;
        return getRequest(tempurl);
    }

    //POST REQUESTS
    /*
    public static void sendData(String username,String time,String puls,String calorii,String nr_pasi,String nivel_oxigen,String calitate_somn){
        String tempurl = host+ "/api/user/pacient/importData";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("username",username);
        jsonObject.append("time",time);
        jsonObject.append("puls",puls);
        jsonObject.append("calorii",calorii);
        jsonObject.append("nr_pasi",nr_pasi);
        jsonObject.append("nivel_oxigen",nivel_oxigen);
        jsonObject.append("calitate_somn",calitate_somn);

        postRequest(tempurl,jsonObject);
    }
     */
    public static JSONObject getRequest(String URL) {
        JSONObject response = null;

        HttpURLConnection connection = null;
        try {


            URL url = new URL(URL);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            try {
                response = new JSONObject(readFromConnection(connection));
            }
            finally {
                connection.disconnect();
            }

        } catch (Exception e) {
            response = new JSONObject();
            response.putOpt("Error!", "Error on the client side contact the admin!");
            response.putOpt("Error!!", e.getCause());
            System.out.println(e.getCause());
            e.printStackTrace();
        } finally {
            return response;
        }
    }

    /*
    public static void postRequest(String URL,JSONObject jsonObject){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(URL);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(createJson(jsonObject).getBytes());
            System.out.println(createJson(jsonObject));
            os.flush();
            os.close();
            int responseCode = connection.getResponseCode();
        } catch (Exception e){
            System.out.println("Unable to make post request!");
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
            System.out.println(URL);
        }
    }

     */
    public static String readFromConnection(HttpURLConnection connection) throws IOException {
        StringBuilder content;
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        content = new StringBuilder();
        while ((line = input.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }
    /*
    public static String createJson(JSONObject jsonObject){
        StringBuilder tempString = new StringBuilder();
        tempString.append("{");
        int count = 0;
        for(String key : jsonObject.keySet()){
            if(count!=0)
                tempString.append(",");
            tempString.append("\"");
            tempString.append(key);
            tempString.append("\"");
            tempString.append(":");
            String temp = jsonObject.get(key).toString();
            temp = temp.substring(1);
            temp = temp.substring(0,temp.length()-2);
            tempString.append(temp);
            tempString.append("\"");
            count++;
        }
        tempString.delete(tempString.length()-1,tempString.length()-1);
        tempString.append("}");
        return tempString.toString();
    }

     */
}
