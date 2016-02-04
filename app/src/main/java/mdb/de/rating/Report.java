package mdb.de.rating;

/**
 * Created by LethmateB on 28.01.2016.
 */
public class Report {
    public Integer ratingId;
    public Integer userId;
    public String reason;


    public Report() {
        super();
    }

    public Report(Integer ratingId, Integer userId, String reason) {
        super();
        this.ratingId = ratingId;
        this.userId = userId;
        this.reason = reason;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
