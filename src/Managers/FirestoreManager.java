package Managers;

import Containers.TodoEntry;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirestoreManager {
    private Firestore db = null;
    private final String collectionName = "Tasks";

    public FirestoreManager(){
        setup();
    }



    private void setup() {
        try {
            FileInputStream serviceAccount = new FileInputStream("todol-a7566-firebase-adminsdk-9sly6-fd8f4347de.json");
            FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                    .setProjectId("todol-a7566")
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            db = firestoreOptions.getService();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TodoEntry> getAllData(){
        try {
            CollectionReference tasks = db.collection(collectionName);
            List<QueryDocumentSnapshot> documents = tasks.get().get().getDocuments();
            ArrayList<TodoEntry> taskList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : documents) {
                Date date = new Date();
                date.setTime(documentSnapshot.getLong("taskDay"));
                taskList.add(new TodoEntry(documentSnapshot.getString("task"),date ,documentSnapshot.getLong("priority"), Integer.parseInt(documentSnapshot.getId())));
            }
            return taskList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int getNewId(){
        Random random = new Random();
        int id = random.nextInt();
        while(idPresentInDB(id)){
            id = random.nextInt();
        }
        return id;
    }

    public void pushData(TodoEntry entry){
        int id = getNewId();
        db.collection(collectionName).document(String.valueOf(id)).set(entry.getAsHashMap());
    }

    private boolean idPresentInDB(int id) {

        try {
            CollectionReference tasks = db.collection(collectionName);
            DocumentReference docRef = tasks.document(String.valueOf(id));
            DocumentSnapshot documentSnapshot = docRef.get().get();
            return documentSnapshot.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public void deleteEntry(TodoEntry entry){
        DocumentReference ref = db.collection(collectionName).document(String.valueOf(entry.getId()));
        ref.delete();
    }

    public void changeEntry(TodoEntry newEntry){
        DocumentReference ref = db.collection(collectionName).document(String.valueOf(newEntry.getId()));
        ref.set(newEntry.getAsHashMap());
    }
}
