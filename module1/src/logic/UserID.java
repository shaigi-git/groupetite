package logic;

/**
 * Created by shai on 10/3/16.
 */
public class UserID {

    private static long NEXT_ID = 1000000;

    private long id;

    public UserID() {
        this.id = NEXT_ID;
        NEXT_ID++;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
