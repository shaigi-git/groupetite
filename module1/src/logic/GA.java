package logic;

/**
 * Created by shai on 10/7/16.
 */
public class GA {

    private static GA INSTANCE = null;
    private UserGroup users;
    private MeetingGroup meetings;

    private GA() {
        this.users = new UserGroup("System");
        this.meetings = new MeetingGroup("System");
    }

    public static GA getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GA();
        }
        return INSTANCE;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    @Override
    public String toString() {
        return "logic.GA{" +
                users +
                ", " + meetings +
                '}';
    }

    public UserGroup getUsers() {
        return users;
    }

    public MeetingGroup getMeetings() {
        return meetings;
    }
}
