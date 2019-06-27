package comp3350.spa.presentation;

import comp3350.spa.R;
import comp3350.spa.business.Events;
import comp3350.spa.objects.CalendarEvent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.support.v4.app.FragmentTransaction;
import com.roomorama.caldroid.CaldroidFragment;
import android.widget.ListView;
import android.widget.Toast;
import com.roomorama.caldroid.CaldroidListener;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;


public class CalendarActivity extends AppCompatActivity implements CalendarAddFragment.NoticeDialogListener {

    ListView listView;
    ArrayAdapter<CalendarEvent> adapter;
    Date selectedDate = new Date();
    Events accessEvents;
    ArrayList<CalendarEvent> events;
    CaldroidFragment caldroidFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        events = new ArrayList<>();
        setContentView(R.layout.activity_calendar);
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();

        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        accessEvents = new Events();
        ArrayList<CalendarEvent> events = new ArrayList<>();
        final ArrayList<CalendarEvent> dayEvents = new ArrayList<>();
        accessEvents.getEventsForDay(dayEvents, cal);

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                selectedDate = date;
                accessEvents.getEventsForDay(dayEvents, cal);
                adapter.notifyDataSetChanged();
            }
        };

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dayEvents);
        caldroidFragment.setCaldroidListener(listener);
        updateDates();
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendarLayout, caldroidFragment);
        t.commit();
        listView = (ListView)findViewById(R.id.calendarList);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CalendarEvent event = ((CalendarEvent)listView.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), event.getEventName() , Toast.LENGTH_LONG).show();

                new AlertDialog.Builder(CalendarActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Remove Event")
                        .setMessage("Are you sure you want to remove this event?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                caldroidFragment.setTextColorForDate(R.color.caldroid_black, event.getEventDate().getTime());
                                accessEvents.removeEvent(event);
                                accessEvents.getEventsForDay(dayEvents, event.getEventDate());
                                updateDates();
                                adapter.notifyDataSetChanged();

                            }

                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                actionAdd();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void actionAdd() {
        DialogFragment newFragment = new CalendarAddFragment();
        newFragment.show(getSupportFragmentManager(), "calAdd");
    }

    @Override
    public void onDialogPositiveClick(CalendarAddFragment dialog) {
        CalendarEvent.Priority priority;
        switch(dialog.eventPriority) {
            case "Low":
                priority = CalendarEvent.Priority.LOW;
                break;
            case "Medium":
                priority = CalendarEvent.Priority.MEDIUM;
                break;
            case "High":
                priority = CalendarEvent.Priority.HIGH;
                break;
            default:
                priority = CalendarEvent.Priority.NONE;
        }

        Calendar cal = new GregorianCalendar();
        cal.setTime(selectedDate);
        CalendarEvent newEvent = new CalendarEvent(dialog.textEdit.getText().toString(), cal, priority);
        accessEvents.addEvent(newEvent);
        updateDates();
        Toast.makeText(getApplicationContext(),
                       dialog.textEdit.getText() + " : " + dialog.eventPriority + " : " +
                       selectedDate, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick(CalendarAddFragment dialog) {

    }

    private void updateDates() {
        accessEvents.getEvents(events);
        for (CalendarEvent event : events) {
            int setColor = R.color.caldroid_darker_gray;
            switch (event.getPriority()) {
                case HIGH:
                    setColor = R.color.red;
                    break;
                case MEDIUM:
                    setColor = R.color.blue;
                    break;
                case LOW:
                    setColor = R.color.green;
                    break;
            }

            caldroidFragment.setTextColorForDate(setColor, event.getEventDate().getTime());
        }

        caldroidFragment.refreshView();
    }
}
