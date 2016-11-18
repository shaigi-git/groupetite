package logic;

/**
 * Created by shai on 10/3/16.
 */
public class Main {

    private static void printGuestsInfo(User users[]) {
        for (User u :
                users) {
            System.out.println(u.guest());
        }
    }

    private static void sysStatus(User users[]) {
        GA ga = GA.getInstance();
        System.out.println(ga);
        printGuestsInfo(users);
    }
    public static void main(String args[]) {

        System.out.println("Start");
        GA ga = GA.getInstance();
        System.out.println(ga);

        // Creating few users
        System.out.println("Creating few users");
        PersonalInfo info1 = new PersonalInfo("Shai", "Givony");
        User shai = new User(info1);
        PersonalInfo info2 = new PersonalInfo("Oualid", "M'barki");
        User oualid = new User(info2);
        PersonalInfo info3 = new PersonalInfo("Moshe", "A");
        User moshe = new User(info3);
        PersonalInfo info4 = new PersonalInfo("Zrubi", "B");
        User zrubi = new User(info4);
        User users[] = { oualid, shai, moshe, zrubi };
        System.out.println(ga.getUsers());
        sysStatus(users);


        System.out.println("Shai is creating a meeting");
        shai.becomeHost();
        Meeting m1 = shai.createMeeting("Oualid's Bday", "08/06/2016");
        m1.publish();
        System.out.println(ga.getMeetings());
        sysStatus(users);

        // Now meeting m1 should be visible to users, and they can request to join

        System.out.println("Oualid is requesting to join the meeting");
        oualid.requestJoinMeeting(m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Shai is approving Oualid");
        shai.approveUserRequestForMeeting(oualid, m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Moshe is requesting to join the meeting");
        moshe.requestJoinMeeting(m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Shai is declining Moshe");
        shai.declineUserRequestForMeeting(moshe, m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Zrubi is requesting to join the meeting");
        zrubi.requestJoinMeeting(m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Shai is approving Zrubi");
        shai.approveUserRequestForMeeting(zrubi, m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Oualid is canceling");
        oualid.cancelMeetingParticipation(m1);
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("logic.Meeting starting");
        m1.start();
        System.out.println("logic.Meeting finishing");
        m1.finish();
        System.out.println(ga.getMeetings());
        sysStatus(users);

        System.out.println("Oualid is creating a meeting");
        oualid.becomeHost();
        Meeting m2 = oualid.createMeeting("Romantic Dinner", "10/18/2016");
        m2.publish();
        System.out.println(ga.getMeetings());

        System.out.println("Moshe is requesting to join");
        moshe.requestJoinMeeting(m2);
        System.out.println(ga.getMeetings());

        System.out.println("Zrubi is requesting to join");
        zrubi.requestJoinMeeting(m2);
        System.out.println(ga.getMeetings());

        System.out.println("Shai is requesting to join");
        shai.requestJoinMeeting(m2);
        System.out.println(ga.getMeetings());


        System.out.println("Oualid is declining both of them");
        oualid.declineUserRequestForMeeting(zrubi, m2);
        oualid.declineUserRequestForMeeting(moshe, m2);
        oualid.approveUserRequestForMeeting(shai, m2);
        System.out.println(ga.getMeetings());
    }


}
