package logic;

/**
 * Created by shai on 10/3/16.
 */
public class Guest {

    private User user;
    private MeetingGroup requestedMeetings;
    private MeetingGroup meetingsPast;
    private MeetingGroup meetingsCurrent;

    public Guest(User user) {
        this.user = user;
        this.requestedMeetings = new MeetingGroup("Requested");
        this.meetingsPast = new MeetingGroup("Past");
        this.meetingsCurrent = new MeetingGroup("Current");
    }

    public void addMeetingRequest(Meeting m) {
        this.requestedMeetings.add(m);
    }

    public boolean setMeetingApproved(Meeting m) {
        if (this.requestedMeetings.contains(m)) {
            this.requestedMeetings.remove(m);
            this.meetingsCurrent.add(m);
            return true;
        }
        return false;
    }

    public boolean setMeetingAsPast(Meeting m) {
        if (this.meetingsCurrent.contains(m)) {
            this.meetingsCurrent.remove(m);
            this.meetingsPast.add(m);
            return true;
        }
        return false;
    }

    public void deleteMeetingRequest(Meeting m) {
        if (this.requestedMeetings.contains(m)) {
            this.requestedMeetings.remove(m);
        }
        if (this.meetingsCurrent.contains(m)) {
            this.meetingsCurrent.remove(m);
        }
    }

    @Override
    public String toString() {
        return "logic.Guest " + user.getInfo().getName() + " {" +
                requestedMeetings +
                ", " + meetingsPast +
                ", " + meetingsCurrent +
                '}';
    }
}
