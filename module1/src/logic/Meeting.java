package logic;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shai on 10/3/16.
 */
public class Meeting {

    private static long NEXT_MEETING_ID = 10000;
    private enum State {
        CREATED, PUBLISHED, ON_GOING, FINISHED
    }
    private long meetingID;
    private String name;
    private User host;
    private UserGroup guestsRequested;
    private UserGroup guestsApproved;
    private UserGroup guestsDeclined;
    private UserGroup guestsCanceled;
    private Date date;
    private TimeZone timezone;
    private State state;

    public Meeting(String name, User host, Date date, TimeZone timezone) {
        this.meetingID = createMeetingID();
        this.name = name;
        this.host = host;
        this.guestsRequested = new UserGroup("Requested");
        this.guestsApproved = new UserGroup("Approved");
        this.guestsDeclined = new UserGroup("Declined");
        this.guestsCanceled = new UserGroup("Canceled");
        this.date = date;
        this.timezone = timezone;
        this.state = State.CREATED;

        GA.getInstance().addMeeting(this);
    }

    private long createMeetingID() {
        long id = NEXT_MEETING_ID;
        NEXT_MEETING_ID++;
        return id;
    }

    public void request(User requestingUser) {
        if (isJoinable()) {
            this.guestsRequested.add(requestingUser);
            //this.host.setNewRequestFromMe(this, requestingUser);
        }
    }

    public void unRequest(User u) {
        if (isJoinable()) {
            if (u == this.host) {
                // impossible - throw exception
                return;
            }
            this.guestsRequested.remove(u);
            this.guestsApproved.remove(u);
            this.guestsCanceled.add(u);
        }
    }

    public boolean approveUser(User u) {
        if (this.guestsRequested.contains(u)) {
            this.guestsRequested.remove(u);
            this.guestsApproved.add(u);
            return true;
        }
        // throw exception ?
        return false;
    }

    public boolean declineUser(User u) {
        if (this.guestsRequested.contains(u)) {
            this.guestsRequested.remove(u);
            this.guestsDeclined.add(u);
            return true;
        }
        // throw exception ?
        return false;
    }

    public int numOfRequests() {
        return this.guestsRequested.size();
    }

    private boolean isJoinable() {
        return this.state == State.PUBLISHED || this.state == State.ON_GOING;
    }

    public boolean publish() {
        this.state = State.PUBLISHED;
        return true;
    }

    public boolean start() {
        this.state = State.ON_GOING;
        System.out.println("logic.Meeting " + this.name + " started!");
        return true;
    }

    public boolean finish() {
        this.state = State.FINISHED;
        for (User u :
                guestsApproved) {
            u.finishMeeting(this);
        }
        System.out.println("logic.Meeting " + this.name + " finished!");
        return true;
    }

    @Override
    public String toString() {
        return "logic.Meeting{" +
                "ID=" + meetingID +
                ", name='" + name + '\'' +
                ", host=" + host +
                (guestsRequested.isEmpty() ? "" : ", " + guestsRequested) +
                (guestsApproved.isEmpty() ? "" : ", " + guestsApproved) +
                (guestsDeclined.isEmpty() ? "" : ", " + guestsDeclined) +
                (guestsCanceled.isEmpty() ? "" : ", " + guestsCanceled) +
                ", date=" + date +
                ", timezone=" + timezone +
                ", state=" + state +
                '}';
    }
}
