package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class IstoricPacient implements DAO {
    private final Database database = Database.getInstance();

    private StringBuilder pacientID;
    private int pacientIDNumber;

    public IstoricPacient() {
        pacientID = Pacient.getPersonID();
        pacientIDNumber = Pacient.getPersonIDNumber();
    }

    public void insertIntoDB(String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 6)
            throw new InvalidNrOfArgsException("Number of parameters is 6.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("diagnostic", args[1]);
        dataToInsert.put("user_doctor", args[2]);
        dataToInsert.put("data_internare", args[3]);
        dataToInsert.put("data_externare", args[4]);
        dataToInsert.put("spital", args[5]);

        pacientID.replace(2, pacientID.length(), String.valueOf(pacientIDNumber));
        pacientIDNumber++;

        ApiFuture<WriteResult> insertData = database.db.collection("istoric_pacient").document(pacientID.toString()).set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection date_pacient!");

        database.disconnectFromDatabase();
    }
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        ApiFuture<WriteResult> removeData = database.db.collection("istoric_pacient").document(documentID).delete();
        removeData.get();

        database.disconnectFromDatabase();
    }
    public void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        DocumentReference updateRef = database.db.collection("istoric_pacient").document(documentID);
        ApiFuture<WriteResult> updateData = updateRef.update(field, value);
        updateData.get();

        database.disconnectFromDatabase();
    }
    public List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        CollectionReference collection = database.db.collection("istoric_pacient");
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Map<String, Object>> documents = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documents.add(getSortedMap(document.getData()));
        }
        database.disconnectFromDatabase();
        return documents;
    }
    public Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        DocumentReference getData = database.db.collection("istoric_pacient").document(documentID);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        Map<String, Object> resultData;
        if (documentData.exists()) {
            resultData = getSortedMap(documentData.getData());
            database.disconnectFromDatabase();
            return resultData;
        }
        database.disconnectFromDatabase();
        return null;
    }
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();

        resultData.put("user", map.get("user"));
        resultData.put("diagnostic", map.get("diagnostic"));
        resultData.put("user_doctor", map.get("user_doctor"));
        resultData.put("data_internare", map.get("data_internare"));
        resultData.put("data_externare", map.get("data_externare"));
        resultData.put("spital", map.get("spital"));

        return resultData;
    }
}
