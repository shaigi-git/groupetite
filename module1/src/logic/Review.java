package logic;

/**
 * Created by shai on 10/3/16.
 */
public class Review {

    public enum Grade {
        ONE_STAR, TWO_STARS, THREE_STARS, FOUR_STARS, FIVE_STARS
    }
    private UserID authorID;
    private Grade grade;
    private String description;
}
