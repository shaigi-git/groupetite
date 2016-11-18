package logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by shai on 10/8/16.
 */
public class MeetingGroup implements Iterable<Meeting> {

    private String name;
    private Set<Meeting> meetings;

    public MeetingGroup(String name) {
        this.name = name;
        this.meetings = new HashSet<Meeting>();
    }

    public boolean add(Meeting u) { return this.meetings.add(u); }
    public boolean remove(Meeting u) { return this.meetings.remove(u); }
    public int size() { return this.meetings.size(); }
    public boolean contains(Meeting u) { return this.meetings.contains(u); }
    public boolean isEmpty() { return this.meetings.isEmpty(); }

    @Override
    public Iterator<Meeting> iterator() {
        return this.meetings.iterator();
    }

    @Override
    public String toString() {
        return name + " Meetings(" + this.meetings.size() + "){" + meetings + '}';
    }
}
