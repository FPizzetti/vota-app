package com.prices.products.productsprices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static String PREFEITOS_URL = "https://dl.dropboxusercontent.com/u/40990541/prefeito.json";
    public static String VEREADORES_URL = "https://dl.dropboxusercontent.com/u/40990541/vereador.json";
    public static String VOTOS_URL = "http://192.168.1.6:8000/votos?token=";

    public static Bitmap baixarImagem(String url) throws IOException {
        URL endereco;
        InputStream inputStream;

        Bitmap bitmap;

        endereco = new URL(url);
        inputStream = endereco.openStream();
        bitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return bitmap;
    }

    public static String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String votar(String token, Candidato vereador, Candidato prefeito) {
        String response = null;
        String json = "{" + "mayor_id:" + prefeito.getId() + "," +
                "councilman_id:" + vereador.getId() + "}";
        try {
            URL url = new URL(VOTOS_URL + token);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            JSONObject jsonObject = new JSONObject(convertStreamToString(in));
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder builder = new StringBuilder();
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
