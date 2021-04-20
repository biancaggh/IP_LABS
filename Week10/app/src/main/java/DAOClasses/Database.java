package DAOClasses;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Database {
    private static Database single_database = null;
    private FileInputStream serviceAccount;
    private FirebaseOptions options;
    protected Firestore db;

    //Get instance method
    public static Database getInstance() {
        if (single_database == null)
            single_database = new Database();
        return single_database;
    }

    //Constructor
    private Database() {
        /*
        personID.append("0#");
        pacientID.append("1#");
        doctorID.append("2#");
        asistentID.append("3#");
        */
    }

    //Connect to DB
    public void connectToDatabase() {
        //Setting db .json file
        try {
            serviceAccount = new FileInputStream("iot-in-medical-domain-firebase-adminsdk-zw2pe-dcf8137ac2.json");
        }
        catch (FileNotFoundException e) {
            System.out.println("DAOClasses.Database .json file could not be found or does not exist!");
        }

        //Setting credentials and database URL
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://iot-in-medical-domain-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();
        }
        catch (IOException e) {
            System.out.println("DAOClasses.Database .json file was not set up!");
        }

        //Connecting to the database
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

        System.out.println("Connection to database successful!");
    }

    public void disconnectFromDatabase() {
        try {
            db.close();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}