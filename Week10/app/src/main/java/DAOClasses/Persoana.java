package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Persoana implements DAO {
    private final Database database = Database.getInstance();

    private final StringBuilder personID = new StringBuilder();
    private int personIDNumber;

    public Persoana() {
        personID.append("0#");
        personIDNumber = 1;
    }

    public void insertIntoDB(String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("nume", args[1]);
        dataToInsert.put("prenume", args[2]);
        dataToInsert.put("sex", args[3]);

        personID.replace(2, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;
        ApiFuture<WriteResult> insertData = database.db.collection("persoana").document(personID.toString()).set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection persoana!");

        database.disconnectFromDatabase();
    }
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {
        try {
            database.connectToDatabase();
        }
        catch (Exception e) {
            System.err.println("Error: " + e);
        }

        ApiFuture<WriteResult> removeData = database.db.collection("persoana").document(documentID).delete();
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

        DocumentReference updateRef = database.db.collection("persoana").document(documentID);
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

        CollectionReference collection = database.db.collection("persoana");
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

        DocumentReference getData = database.db.collection("persoana").document(documentID);
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
        resultData.put("nume", map.get("nume"));
        resultData.put("prenume", map.get("prenume"));
        resultData.put("sex", map.get("sex"));
        return resultData;
    }
}
