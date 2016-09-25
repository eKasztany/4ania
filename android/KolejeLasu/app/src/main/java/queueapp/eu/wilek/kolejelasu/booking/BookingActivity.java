package queueapp.eu.wilek.kolejelasu.booking;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import queueapp.eu.wilek.kolejelasu.NotificationReceiver;
import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.services.ConfirmationDialog;
import queueapp.eu.wilek.kolejelasu.services.ServicesActivity;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class BookingActivity extends AppCompatActivity {

    public static final String WAITING_COUNT = "waitingCount";
    public static final String AVERAGE_TIME = "averageTime";
    public static final String OFFICE_COUNT = "offcieCount";
    public static final String GROUP = "group";

    private static final int VA_BOOKING_NOW = 0;
    private static final int VA_BOOKING_OPTI = 1;

    private static final String CONFIRMATION_DIALOG_TAG = "confirmationTag";

    private int waiting;

    private String departmentName;

    private Button queueNow;
    private Button queueBest;

    private ViewAnimator bookViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Bundle bundle = getIntent().getExtras();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(bundle.getString(GROUP));

        waiting = bundle.getInt(WAITING_COUNT);
        departmentName = bundle.getString(ServicesActivity.DEPARTMENT_NAME);
        int officeCount = bundle.getInt(OFFICE_COUNT);

        String time [] = bundle.getString(AVERAGE_TIME).split(":");

        int seconds = (((Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1])) * waiting) / 2;

        ((TextView) findViewById(R.id.waiting_number)).setText(String.valueOf(waiting));
        ((TextView) findViewById(R.id.waiting_person)).setText(getString(R.string.waiting_person_format,
                getResources().getQuantityString(R.plurals.waiting, waiting),
                getResources().getQuantityString(R.plurals.person, waiting)));

        ((TextView) findViewById(R.id.minutes)).setText(convertSecondsToTime(seconds));

        queueNow = (Button) findViewById(R.id.book_now);
        queueBest = (Button) findViewById(R.id.book_now_optimize);

        bookViewPager = (ViewAnimator) findViewById(R.id.va_booking);

        queueNow.setOnClickListener(createOnQueueClickListener());
        queueBest.setOnClickListener(createOnQueueOptimalClickListener());
    }


    public void onMinuteClick(final View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(getString(R.string.confirmation_minutes) + " " + ((Button) view).getText() + ".")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                        notificationIntent.addCategory("android.intent.category.DEFAULT");
                        notificationIntent.putExtra("asd", ((Button) view).getText());
                        notificationIntent.putExtra(NotificationReceiver.OPTIMAL, departmentName);

                        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, 5);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
                    }
                });
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBookNowClick(View view) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog();
        confirmationDialog.setNumber(waiting);
        confirmationDialog.setOnCloseListener(new ConfirmationDialog.OnCloseListener() {
            @Override
            public void onClose(int number) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
                notificationIntent.addCategory("android.intent.category.DEFAULT");
                notificationIntent.putExtra("ludki", "" + number);
                notificationIntent.putExtra("serwis", departmentName);

                PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
            }
        });
        confirmationDialog.show(getSupportFragmentManager(), CONFIRMATION_DIALOG_TAG);
    }


    private String convertSecondsToTime(int seconds) {
        return "" + (seconds / 60) + " minut";
    }


    private View.OnClickListener createOnQueueClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                queueNow.setBackgroundResource(R.drawable.rounded_button_color_left);
                queueNow.setTextColor(Color.WHITE);
                queueBest.setBackgroundResource(R.drawable.rounded_button_shape_right);
                queueBest.setTextColor(getResources().getColor(R.color.color_blue));
                bookViewPager.setDisplayedChild(VA_BOOKING_NOW);
            }
        };
    }


    private View.OnClickListener createOnQueueOptimalClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                queueBest.setBackgroundResource(R.drawable.rounded_button_color_right);
                queueBest.setTextColor(Color.WHITE);
                queueNow.setBackgroundResource(R.drawable.rounded_button_shape_left);
                queueNow.setTextColor(getResources().getColor(R.color.color_blue));
                bookViewPager.setDisplayedChild(VA_BOOKING_OPTI);
            }
        };
    }

}
