package logic;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shai on 10/3/16.
 */
public class User implements Serializable {

    public enum State {
        ACTIVE, NON_ACTIVE
    }
    private UserID userID;      // should be thread-safe when created
    private State state;
    private PersonalInfo info;
    private Guest guest;
    private Host host;          // Once a user becomes a host, it's for life (unless if he cancels his account as a host)

    public User(PersonalInfo info) {
        this.userID = new UserID();
        this.state = State.ACTIVE;
        this.info = info;
        this.guest = new Guest(this);
        this.host = null;

        GA.getInstance().addUser(this);
    }

    public void requestJoinMeeting(Meeting m) {
        m.request(this);
        this.guest.addMeetingRequest(m);
    }

    public void cancelMeetingParticipation(Meeting m) {
        m.unRequest(this);
        this.guest.deleteMeetingRequest(m);
    }

    public boolean approveMeeting(Meeting m) {
        return this.guest.setMeetingApproved(m);
    }

    public void finishMeeting(Meeting m) {
        this.guest.setMeetingAsPast(m);
    }

    public PersonalInfo getInfo() {
        return info;
    }

    public void setInfo(PersonalInfo info) {
        this.info = info;
    }

    public Guest guest() {
        return this.guest;
    }

    /************** HOST **************/
    public void becomeHost() {
        this.host = new Host(this);
    }

    public boolean cancelHost() {
        if (this.host.hasCurrentMeetings()) {
            return false;
        }
        this.host = null;
        return true;
    }

    public boolean isHost() {
        return this.host != null;
    }

    /**
     *
     * @param name meeting name
     * @param dateStr meeting date in format dd/MM/yyyy
     * @return
     */
    public Meeting createMeeting(String name, String dateStr) {
        Meeting m = null;
        if (isHost()) {
            Date date = new Date();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
            m = this.host.createMeeting(name, date);
        }
        return m;
    }

    public void approveUserRequestForMeeting(User u, Meeting m) {
        this.host.approveRequest(m, u);
    }

    public void declineUserRequestForMeeting(User u, Meeting m) {
        this.host.declineRequest(m, u);
    }

    @Override
    public String toString() {
        return info.getFirstName() + " " + info.getLastName() + " (" + userID + ")";
    }
}
