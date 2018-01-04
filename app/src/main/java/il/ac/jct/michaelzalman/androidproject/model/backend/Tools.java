package il.ac.jct.michaelzalman.androidproject.model.backend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.view.KeyEvent;
import android.widget.Toast;

import il.ac.jct.michaelzalman.androidproject.R;
import il.ac.jct.michaelzalman.androidproject.controller.AddCarActivity;

/**
 * Created by מיכאל on 03/01/2018.
 */

public class Tools {
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null);

    }

    public static boolean isInternetConnectedToast(Context context) {


        if (!isInternetConnected(context)) {
            Toast.makeText(context, context.getResources().getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isInternetConnectedAlert(final Activity activity) {

        if (!isInternetConnected(activity)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
            builder1.setMessage("אין חיבור נתונים\nהתחבר לרשת ונסה שוב");
            builder1.setCancelable(true);
            builder1.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                            && keyCode == KeyEvent.KEYCODE_BACK
                            && event.getRepeatCount() == 0) {
                        dialog.cancel();
                        activity.finish();
                        return true;
                    }
                    return false;

                }
            });
            builder1.setPositiveButton(
                    "מובן",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            activity.finish();
                        }
                    });

            AlertDialog alert = builder1.create();
            alert.show();
            return false;
        }

        return true;
    }

}
