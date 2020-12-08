package ilyos.app.examplemvvm.utils;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Errors {

    public static void traceErrors(Throwable t, Context context) {
        if (t instanceof UnknownHostException) {
            Toast.makeText(context, "Check your internet connection. And try again", Toast.LENGTH_SHORT).show();
        } else if (t instanceof ConnectException) {
            Toast.makeText(context, "Check your internet connection. And try again", Toast.LENGTH_SHORT).show();
        } else if (t instanceof SocketTimeoutException) {
            Toast.makeText(context, "Internet speed is slow or downloading takes longer than usual. Please wait", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_SHORT).show();
            /*if (t instanceof HttpException) {
                String errorBody = null;
                try {
                    errorBody = ((HttpException) t).response().errorBody().string();

                    if (errorBody.contains("apierror")) {
                        ErrorResponse errorResponse = new Gson().fromJson(errorBody, ErrorResponse.class);
                        Toast.makeText(context, errorResponse.getApierror().getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (errorBody.contains("error_description")) {
                        SignInResponse errorResponse = new Gson().fromJson(errorBody, SignInResponse.class);
                        Toast.makeText(context, errorResponse.getError_description(), Toast.LENGTH_SHORT).show();
                    } else if (errorBody.contains("message") && errorBody.contains("status")) {
                        SignInResponse errorResponse = new Gson().fromJson(errorBody, SignInResponse.class);
                        Toast.makeText(context, errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, errorBody, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (t instanceof EOFException) {
                t.printStackTrace();
            } else
                Toast.makeText(context, "Check API base url, something went wrong with it", Toast.LENGTH_SHORT).show();
            Timber.e(t.toString());*/
        }
    }
}
