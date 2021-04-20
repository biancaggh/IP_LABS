package DAOClasses;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface DAO {
    void insertIntoDB(String... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException;
    void removeFromDB(String documentID) throws InterruptedException, ExecutionException;
    void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException;
    List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException;
    Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException;
    Map<String, Object> getSortedMap(Map<String, Object> map);
}
