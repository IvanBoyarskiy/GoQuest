package com.kime.goquest;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

public class GoCodeAIExecutor {

    private static final String TAG = "GoCodeAIExecutor";
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    private static final String API_KEY = "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";

    public interface ExecutionCallback {
        void onResult(@NonNull String result);
    }

    public void analyzeGoCode(String goCode, ExecutionCallback callback) {
        new AiAnalysisTask(callback).execute(goCode);
    }

    private static class AiAnalysisTask extends AsyncTask<String, Void, String> {

        private final ExecutionCallback callback;
        private final OkHttpClient client = new OkHttpClient();

        AiAnalysisTask(ExecutionCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... params) {
            String code = params[0];

            String prompt = "Проанализируй следующий Go-код:\n\n" + code +
                    "\n\nКакой будет вывод программы? Отправь только вывод!";

            String jsonBody = "{"
                    + "\"model\": \"deepseek/deepseek-r1:free\","
                    + "\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt.replace("\"", "\\\"") + "\"}],"
                    + "\"temperature\": 0,"
                    + "\"max_tokens\": 500"
                    + "}";

            MediaType JSON = MediaType.get("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(jsonBody, JSON);

            Request request = new Request.Builder()
                    .url(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    Log.e(TAG, "Ошибка запроса: " + response.code());
                    return "Ошибка: HTTP " + response.code();
                }

                if (response.body() == null) {
                    return "Ошибка: пустой ответ от сервера";
                }

                String responseBody = response.body().string();
                Log.d(TAG, "Получен ответ: " + responseBody);

                JSONObject obj = new JSONObject(responseBody);
                String answer = obj.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                return answer;

            } catch (Exception e) {
                Log.e(TAG, "Ошибка выполнения", e);
                return "Ошибка: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (callback != null) {
                callback.onResult(result);
            }
        }
    }
}