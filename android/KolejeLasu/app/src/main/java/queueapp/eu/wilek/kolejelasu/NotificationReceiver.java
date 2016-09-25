package queueapp.eu.wilek.kolejelasu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final String OPTIMAL = "optimal";

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification;

        if (intent.getStringExtra(OPTIMAL) != null) {
            String minutes = intent.getStringExtra("asd");
            notification = builder.setContentTitle("Kolejka się zmniejszyła!")
                    .setContentText("Szacowany czas oczekiwania spadł do " + minutes + ".")
                    .setSmallIcon(R.drawable.time)
                    .addAction(R.drawable.time, "Zarezerwuj", pendingIntent)
                    .setContentIntent(pendingIntent).build();
        } else {
            String name = intent.getStringExtra("serwis");
            String ludzie = intent.getStringExtra("ludki");
            notification = builder.setContentTitle(name)
                    .setContentText("Liczba osób w kolejce spadła do " + ludzie + ".")
                    .setSmallIcon(R.drawable.time)
                    .setContentIntent(pendingIntent).build();
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
