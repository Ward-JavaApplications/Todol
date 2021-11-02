import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FirestoreManager {
    private Firestore db = null;
    private final String collectionName = "Tasks";

    public FirestoreManager(){
        setup();
        pushData(null);
        if(idPresentInDB(2)) System.out.println("present");
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

    private void pushData(HashMap<String,TodoEntry> data){
        Random random = new Random();
        int id = random.nextInt();

        ApiFuture<WriteResult> future = db.collection(collectionName).document("two").set(new TodoEntry("test","tuesday",1).getAsHashMap());
        try {
            System.out.println(future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private boolean idPresentInDB(int id){
        CollectionReference tasks = db.collection(collectionName);
        DocumentReference docRef = tasks.document(String.valueOf(id));
        return false;

    }
}
