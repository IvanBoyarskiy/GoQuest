package com.kime.goquest;

import android.util.Log;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Map;

public class Database {

    private static final String TAG = "FirebaseDBHelper";
    private FirebaseFirestore db;

    public Database() {
        db = FirebaseFirestore.getInstance();
    }
    public interface FirestoreFieldCallback {
        void onFieldExists(boolean exists);
        void onError(String errorMessage);
    }


    public void saveData(String collectionName, String documentId, Map<String, Object> data) {
        db.collection(collectionName).document(documentId)
                .set(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Данные успешно сохранены");
                    } else {
                        Log.e(TAG, "Ошибка при сохранении данных", task.getException());
                    }
                });
    }


    public void updateData(String collectionName, String documentId, Map<String, Object> newData) {
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        docRef.update(newData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Данные успешно обновлены");
                    } else {
                        Log.e(TAG, "Ошибка при обновлении данных", task.getException());
                    }
                });
    }


    public void deleteData(String collectionName, String documentId) {
        db.collection(collectionName).document(documentId)
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Документ успешно удален");
                    } else {
                        Log.e(TAG, "Ошибка при удалении документа", task.getException());
                    }
                });
    }

    public void getAchievementFields(String collectionName, String documentId, FirestoreAchievementCallback callback) {
        db.collection(collectionName).document(documentId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Map<String, Object> allFields = document.getData();
                            callback.onSuccess(allFields.keySet().toArray(new String[0]));
                        } else {
                            callback.onSuccess(new String[0]); // пустой массив
                        }
                    } else {
                        callback.onError("Ошибка получения данных");
                    }
                });
    }

    public void checkFieldExists(String collectionName, String documentId, String fieldName, FirestoreFieldCallback callback) {
        db.collection(collectionName).document(documentId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // Документ существует, проверяем наличие поля
                            Object fieldValue = document.get(fieldName);
                            if (fieldValue != null) {
                                callback.onFieldExists(true);
                            } else {
                                callback.onFieldExists(false);
                            }
                        } else {
                            // Документа нет — значит и поля тоже нет
                            callback.onFieldExists(false);
                        }
                    } else {
                        // Ошибка получения данных
                        Log.e("Firestore", "Ошибка при получении документа", task.getException());
                        callback.onError(task.getException().getMessage());
                    }
                });
    }


    public void getData(String collectionName, String documentId, FirestoreCallback callback) {
        db.collection(collectionName).document(documentId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            callback.onSuccess(document.getData());
                        } else {
                            Log.d(TAG, "Документ не найден");
                            callback.onSuccess(null);
                        }
                    } else {
                        Log.e(TAG, "Ошибка при получении данных", task.getException());
                        callback.onError(task.getException().getMessage());
                    }
                });
    }


    public interface FirestoreCallback {
        void onSuccess(Map<String, Object> data);
        void onError(String errorMessage);
    }
}