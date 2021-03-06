package nesada.c4q.nyc.KeyboardKeyTest.backend;

/**
 * Created by nesadakoca on 1/11/17.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import nesada.c4q.nyc.KeyboardKeyTest.helper.Consumer;

public class Backend {

    private final RequestQueue mRequestQueue;
    private static final String BASE_URL = "http://jsjrobotics.nyc/cgi-bin/1_11_2017_exam.pl";

    public Backend(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

//    public static String getBaseUrl() {
//        return BASE_URL;
//    }

    public void downloadData(Consumer<ApiResponse> listener) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                BASE_URL,
                buildSuccessListener(listener),
                buildErrorListener()
        );
        mRequestQueue.add(request);
    }

    private Response.ErrorListener buildErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

    private Response.Listener<String> buildSuccessListener(final Consumer<ApiResponse> listener) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ApiResponse result = ApiResponse.parse(response);
                listener.accept(result);
            }
        };
    }
}
