package com.kime.goquest;

public interface FirestoreAchievementCallback {
    void onSuccess(String[] fields);
    void onError(String errorMessage);
}
