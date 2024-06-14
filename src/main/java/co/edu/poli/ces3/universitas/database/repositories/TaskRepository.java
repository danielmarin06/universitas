package co.edu.poli.ces3.universitas.database.repositories;

import co.edu.poli.ces3.universitas.database.CRUD;
import co.edu.poli.ces3.universitas.database.ConexionFirebase;
import co.edu.poli.ces3.universitas.database.dao.Task;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TaskRepository extends ConexionFirebase implements CRUD<Task> {
    @Override
    public List<Task> get() throws SQLException {
        return null;
    }

    @Override
    public Task getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public Task insert() {
        try {
            Firestore db = FirestoreClient.getFirestore();

            CollectionReference collectionTasks = db.collection("tasks");

            Map<String, Object> task = new HashMap<>();

            task.put("name", "Pepe");
            task.put("age", 30);

            ApiFuture<WriteResult> result = collectionTasks.document().set(task);

            System.out.println("Se creo la tare en " + result.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Task update(JsonObject userUpdate, int id) throws SQLException {
        return null;
    }

    public static void main(String[] args) {
        new TaskRepository().insert();
    }
}
