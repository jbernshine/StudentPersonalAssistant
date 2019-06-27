package comp3350.spa.business;

import java.util.Calendar;

import java.util.ArrayList;
import java.util.List;

import comp3350.spa.application.CommonAccess;
import comp3350.spa.application.Main;
import comp3350.spa.objects.CalendarEvent;
import comp3350.spa.persistence.Database;

public class Events {

    private Database db;

    public Events() {
        db = CommonAccess.getDatabase(Main.getDBName());
    }

    public Events(Database database) {
        db = database;
    }

    public void clearAllEvents() {
        db.clearAllEvents();
    }

    public boolean addEvent(CalendarEvent event) {
        boolean result = false;
        if (!db.containsEvent(event)) {
            db.insertEvent(event);
            result = true;
        }

        return result;
    }

    public int getNumEvents() {
        final ArrayList<CalendarEvent> events = new ArrayList<>();
        db.getEventSequential(events);
        return events.size();
    }

    public void getEvents(List<CalendarEvent> events) {
        events.clear();
        db.getEventSequential(events);
    }

    public CalendarEvent getRandomEvent() {
        final ArrayList<CalendarEvent> events = new ArrayList<>();
        CalendarEvent result = null;

        db.getEventSequential(events);
        if (events.size() > 0) {
            int index = (int)(Math.random() * events.size());
            result = events.get(index);
        }

        return result;
    }

    public void getEventsForDay(List<CalendarEvent> events, Calendar day) {
        events.clear();
        List<CalendarEvent> eventList = new ArrayList<>();
        db.getEventSequential(eventList);

        for (CalendarEvent event : eventList){
            if (event.isSameDay(day))
                events.add(event);
        }
    }

    public void removeEvent(CalendarEvent event){
        db.deleteEvent(event);
    }

    public boolean containsEvent(CalendarEvent event){
        return db.containsEvent(event);
    }

}
