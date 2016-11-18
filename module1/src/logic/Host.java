package logic;

import java.util.Date;

/**
 * Created by shai on 10/3/16.
 */
public class Host {

    public enum Answer {
        APPROVED, DECLINED, NO_SUCH_MEETING
    }
    private User user;
    private MeetingGroup meetingsPast;
    private MeetingGroup meetingsCurrent;

    public Host(User user) {
        this.user = user;
        this.meetingsPast = new MeetingGroup("Past");
        this.meetingsCurrent = new MeetingGroup("Current");
    }

    public Meeting createMeeting(String name, Date date) {
        Meeting m = new Meeting(name, this.user, date, this.user.getInfo().getTimeZone());
        this.meetingsCurrent.add(m);
        return m;
    }

//    public void setNewRequestFromMe(logic.Meeting m, logic.User u) {
//        if (this.meetingsCurrent.contains(m)) {
//            m.request(u);
//        }
//    }
//
//    public void unsetNewRequestFromMe(logic.Meeting m, logic.User u) {
//        if (this.meetingsCurrent.contains(m)) {
//            m.request(u);
//        }
//    }

    public Answer approveRequest(Meeting m, User u) {
        if (this.meetingsCurrent.contains(m)) {
            m.approveUser(u);
            u.approveMeeting(m);
            return Answer.APPROVED;
        }
        return Answer.NO_SUCH_MEETING;
    }

    public Answer declineRequest(Meeting m, User u) {
        if (this.meetingsCurrent.contains(m)) {
            m.declineUser(u);
            return Answer.DECLINED;
        }
        // TODO: throw exception!!!
        return Answer.NO_SUCH_MEETING;
    }

    public boolean hasCurrentMeetings() {
        return !this.meetingsCurrent.isEmpty();
    }
}
