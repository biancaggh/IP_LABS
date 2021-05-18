package utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class ClientCommunicationHandler {

    //static private String host = "http://localhost:8080";
    static private String host ="https://radiant-bayou-97811.herokuapp.com";

    //GET REQUESTS
    public static Integer login(String username, String password) throws JSONException {
        String tempurl = host + "/api/user/login/" + username + "/" + password;
        JSONObject jsonObject = getRequest(tempurl);
        System.out.println(jsonObject);
        if(jsonObject.has("login"))
            if(jsonObject.get("login").equals("true"))
                return 1;
            else
                return 0;
        else
            return 0;
    }
    public static JSONObject getPacient(String username){
        String tempurl = host + "/api/user/getPacient/" + username;
        return getRequest(tempurl);
    }
    public static JSONObject getPacientByCNP(String cnp){
        String tempurl = host + "/api/user/getPacientByCNP/" + cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getIstoric(String username){
        String tempurl = host + "/api/user/pacient/istoric/" + username;
        return getRequest(tempurl);
    }
    public static JSONObject getIstoricByCNP(String cnp){
        String tempurl = host + "/api/user/pacient/istoricByCNP/" + cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getDiagnostic(String username){
        String tempurl = host + "/api/user/pacient/diagnostic/" + username;
        return getRequest(tempurl);
    }

    public static JSONObject getDiagnosticByCNP(String cnp){
        String tempurl = host + "/api/user/pacient/diagnosticByCNP/" + cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getAsistent(String username){
        String tempurl = host + "/api/asistent/getAsistent/" + username;
        return getRequest(tempurl);
    }
    public static JSONObject getPacientList(){
        String tempurl = host + "/api/asistent/getPacientList";
        return getRequest(tempurl); // RETURNEAZA O LISTA DE CNP-URI!!
    }
    public static JSONObject getDoctor(String username){
        String tempurl = host + "/api/doctor/getDoctor/"+username;
        return getRequest(tempurl);
    }
    public static JSONObject getDoctorByCNP(String cnp){
        String tempurl = host + "/api/doctor/getDoctorByID/"+cnp;
        return getRequest(tempurl);
    }

    public static JSONObject getPuls(String cnp){
        String tempurl = host + "/api/data/get/puls/"+cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getCalorii(String cnp){
        String tempurl = host + "/api/data/get/calorii/"+cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getPasi(String cnp){
        String tempurl = host + "/api/data/get/pasi/"+cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getNivelOxigen(String cnp){
        String tempurl = host + "/api/data/get/nivelOxigen/"+cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getCalitateSomn(String cnp){
        String tempurl = host + "/api/data/get/calitateSomn/"+cnp;
        return getRequest(tempurl);
    }
    public static JSONObject getRol(String username){
        String tempurl = host + "/api/user/getRol/"+username;
        return getRequest(tempurl);
    }

    //POST REQUESTS
    public static void sendData(String username,String puls,String calorii,String nr_pasi) throws JSONException {
        String tempurl = host+ "/api/data/import/bigData";
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("username",username);
        jsonObject.putOpt("puls",puls);
        jsonObject.putOpt("calorii",calorii);
        jsonObject.putOpt("nr_pasi",nr_pasi);

        postRequest(tempurl,jsonObject);
    }

    public static void sendOxigen(String username,String oxigen) throws JSONException {
        String tempurl = host+ "/api/data/import/oxigen";
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("username",username);
        jsonObject.putOpt("nivel_oxigen",oxigen);

        postRequest(tempurl,jsonObject);
    }
    public static void sendCalitateSomn(String username,String calitate_somn) throws JSONException{
        String tempurl = host+ "/api/data/import/calitate_somn";
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("username",username);
        jsonObject.putOpt("calitate_somn",calitate_somn);

        postRequest(tempurl,jsonObject);
    }

    public static JSONObject getRequest(String URL){
        JSONObject response = null;
        HttpURLConnection connection = null;
        try {



            URL url = new URL(URL);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            response = new JSONObject(readFromConnection(connection));




        }catch (Exception e){
            response = new JSONObject();
            response.putOpt("Error!","Error on the client side contact the admin!");
            response.putOpt("Error!!",e.getCause());
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
            return response;
        }
    }
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
    public static String createJson(JSONObject jsonObject) throws JSONException {
        StringBuilder tempString = new StringBuilder();
        tempString.append("{");
        int count = 0;
        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ){
            String key = it.toString();
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
}
