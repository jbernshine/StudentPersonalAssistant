package comp3350.spa.objects;

import java.util.Calendar;

public class CalendarEvent {

    public enum Priority {
        NONE,
        LOW,
        MEDIUM,
        HIGH
    }

    private int eventId = -1;
    private String name;
    private Calendar date;
    private Priority priority;

    public CalendarEvent(String eventName, Calendar eventDate, Priority eventPriority) {
        name = eventName;
        date = eventDate;
        priority = eventPriority;
    }

    public void setEventId(int id) {
        eventId = id;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return name;
    }

    public Calendar getEventDate() {
        return date;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority newPriority) {
        priority = newPriority;
    }

    public boolean isSameDay(Calendar compareDate) {
        boolean result = false;
        if (compareDate != null)
            result = (date.get(Calendar.YEAR) == compareDate.get(Calendar.YEAR) && date.get(Calendar.DAY_OF_YEAR) == compareDate.get(Calendar.DAY_OF_YEAR));

        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
