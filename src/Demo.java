import Containers.TodoEntry;
import Managers.FirestoreManager;
import Managers.GUIManager;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        new GUIManager();
        //pushData();
    }

    private static void pushData(){
        FirestoreManager firestoreManager = new FirestoreManager();
        for(int x=0;x <20; x++){
            firestoreManager.pushData(new TodoEntry("Text",new Date(),2));
        }
    }
}
