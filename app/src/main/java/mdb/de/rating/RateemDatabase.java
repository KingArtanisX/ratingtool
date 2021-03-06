package mdb.de.rating;

import android.provider.BaseColumns;

/**
 * Created by LethmateB on 03.11.2015.
 */

/**
 * The Database
 */
public class RateemDatabase {
    public RateemDatabase() {}

    /**
     * The category entry for the Database
     */
    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "categories";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DELETABLE = "deletable";

        private static final String VARCHAR_TYPE = " VARCHAR(255)";
        private static final String BOOL_TYPE = " BOOLEAN";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        CategoryEntry.TABLE_NAME +
                        " (" +
                        CategoryEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        CategoryEntry.COLUMN_NAME_NAME +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        CategoryEntry.COLUMN_NAME_DELETABLE +
                        BOOL_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME;
    }

    /**
     * The spot entry for the Database
     */
    public static abstract class SpotEntry implements BaseColumns {
        public static final String TABLE_NAME = "spots";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_POSTCODE = "postcode";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_LONG = "longitude";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_VISIBLE = "visible";
        public static final String COLUMN_NAME_DELETABLE = "deletable";

        private static final String VARCHAR_TYPE = " VARCHAR(255)";
        private static final String FLOAT_TYPE = " FLOAT";
        private static final String BOOL_TYPE = " BOOLEAN";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        SpotEntry.TABLE_NAME +
                        " (" +
                        SpotEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        SpotEntry.COLUMN_NAME_NAME +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_STREET +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_POSTCODE +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_CITY +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_COUNTRY +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_LONG +
                        FLOAT_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_LAT +
                        FLOAT_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_VISIBLE +
                        BOOL_TYPE +
                        COMMA_SEP +
                        SpotEntry.COLUMN_NAME_DELETABLE +
                        BOOL_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpotEntry.TABLE_NAME;
    }

    /**
     * The user entry for the Database
     */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ALIAS = "alias";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD_HASH = "password_hash";
        public static final String COLUMN_NAME_ACTIVE = "active";
        public static final String COLUMN_NAME_LAST_LOGIN = "last_login";

        private static final String VARCHAR_TYPE = " VARCHAR(255)";
        private static final String TIMESTAMP_TYPE = " TIMESTAMP";
        private static final String BOOL_TYPE = " BOOLEAN";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        UserEntry.TABLE_NAME +
                        " (" +
                        UserEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        UserEntry.COLUMN_NAME_ALIAS +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        UserEntry.COLUMN_NAME_EMAIL +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        UserEntry.COLUMN_NAME_PASSWORD_HASH +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        UserEntry.COLUMN_NAME_ACTIVE +
                        BOOL_TYPE +
                        COMMA_SEP +
                        UserEntry.COLUMN_NAME_LAST_LOGIN +
                        TIMESTAMP_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
    }

    /**
     * The rank entry for the Database
     */
    public static abstract class RankEntry implements BaseColumns {
        public static final String TABLE_NAME = "ranks";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";

        private static final String VARCHAR_TYPE = " VARCHAR(255)";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        RankEntry.TABLE_NAME +
                        " (" +
                        RankEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        RankEntry.COLUMN_NAME_NAME +
                        VARCHAR_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + RankEntry.TABLE_NAME;
    }

    /**
     * The position entry for the Database
     */
    public static abstract class PositionEntry implements BaseColumns {
        public static final String TABLE_NAME = "positions";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_USER_ID = "user_id";

        private static final String INT_TYPE = " INTEGER";
        private static final String TIMESTAMP_TYPE = " TIMESTAMP";
        private static final String FLOAT_TYPE = " FLOAT";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        PositionEntry.TABLE_NAME +
                        " (" +
                        PositionEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        PositionEntry.COLUMN_NAME_TIMESTAMP +
                        TIMESTAMP_TYPE +
                        COMMA_SEP +
                        PositionEntry.COLUMN_NAME_LATITUDE +
                        FLOAT_TYPE +
                        COMMA_SEP +
                        PositionEntry.COLUMN_NAME_LONGITUDE +
                        FLOAT_TYPE +
                        COMMA_SEP +
                        PositionEntry.COLUMN_NAME_USER_ID +
                        INT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PositionEntry.TABLE_NAME;
    }

    /**
     * The rating entry for the Database
     */
    public static abstract class RatingEntry implements BaseColumns {
        public static final String TABLE_NAME = "ratings";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_LOCATION_ID = "spot_id";
        public static final String COLUMN_NAME_REPORTS = "reports";

        private static final String INT_TYPE = " INTEGER";
        private static final String VARCHAR_TYPE = " VARCHAR(140)";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        RatingEntry.TABLE_NAME +
                        " (" +
                        RatingEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        RatingEntry.COLUMN_NAME_TEXT +
                        VARCHAR_TYPE +
                        COMMA_SEP +
                        RatingEntry.COLUMN_NAME_USER_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        RatingEntry.COLUMN_NAME_LOCATION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        RatingEntry.COLUMN_NAME_REPORTS +
                        INT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + RatingEntry.TABLE_NAME;
    }

    /**
     * The criterion entry for the Database
     */
    public static abstract class CriterionEntry implements BaseColumns {
        public static final String TABLE_NAME = "criteria";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";

        private static final String VARCHAR_TYPE = " VARCHAR(255)";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        CriterionEntry.TABLE_NAME +
                        " (" +
                        CriterionEntry.COLUMN_NAME_ID +
                        " INTEGER PRIMARY KEY," +
                        CriterionEntry.COLUMN_NAME_NAME +
                        VARCHAR_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CriterionEntry.TABLE_NAME;
    }

    /**
     * The criterions valuations entry for the Database
     */
    public static abstract class CriterionValuationEntry implements BaseColumns {
        public static final String TABLE_NAME = "criteria_valuations";
        public static final String COLUMN_NAME_RATING_ID = "rating_id";
        public static final String COLUMN_NAME_CRITERION_ID = "criterion_id";
        public static final String COLUMN_NAME_STARS = "stars";

        private static final String INT_TYPE = " INTEGER";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        CriterionValuationEntry.TABLE_NAME +
                        " (" +
                        CriterionValuationEntry.COLUMN_NAME_RATING_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        CriterionValuationEntry.COLUMN_NAME_CRITERION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        CriterionValuationEntry.COLUMN_NAME_STARS +
                        INT_TYPE +
                        COMMA_SEP +
                        "PRIMARY KEY (" +
                        CriterionValuationEntry.COLUMN_NAME_RATING_ID  + "," +
                        CriterionValuationEntry.COLUMN_NAME_CRITERION_ID +
                        " ))";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + CriterionValuationEntry.TABLE_NAME;
    }

    /**
     * The users ranks entry for the Database
     */
    public static abstract class UserRanksEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_ranks";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_RANK_ID = "rank_id";

        private static final String INT_TYPE = " INTEGER";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        UserRanksEntry.TABLE_NAME +
                        " (" +
                        UserRanksEntry.COLUMN_NAME_USER_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        UserRanksEntry.COLUMN_NAME_RANK_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        "PRIMARY KEY (" +
                        UserRanksEntry.COLUMN_NAME_USER_ID  + "," +
                        UserRanksEntry.COLUMN_NAME_RANK_ID +
                        " ))";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserRanksEntry.TABLE_NAME;
    }

    /**
     * The users favourites entry for the Database
     */
    public static abstract class FavouriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favourits";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_LOCATION_ID = "spot_id";

        private static final String INT_TYPE = " INTEGER";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        FavouriteEntry.TABLE_NAME +
                        " (" +
                        FavouriteEntry.COLUMN_NAME_USER_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        FavouriteEntry.COLUMN_NAME_LOCATION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        "PRIMARY KEY (" +
                        FavouriteEntry.COLUMN_NAME_USER_ID  + "," +
                        FavouriteEntry.COLUMN_NAME_LOCATION_ID +
                        " ))";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FavouriteEntry.TABLE_NAME;
    }

    /**
     * The spots category entry for the Database
     */
    public static abstract class SpotCategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "spot_category";
        public static final String COLUMN_NAME_LOCATION_ID = "spot_id";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";

        private static final String INT_TYPE = " INTEGER";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        SpotCategoryEntry.TABLE_NAME +
                        " (" +
                        SpotCategoryEntry.COLUMN_NAME_LOCATION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        SpotCategoryEntry.COLUMN_NAME_CATEGORY_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        "PRIMARY KEY (" +
                        SpotCategoryEntry.COLUMN_NAME_LOCATION_ID  + "," +
                        SpotCategoryEntry.COLUMN_NAME_CATEGORY_ID +
                        " ))";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpotCategoryEntry.TABLE_NAME;
    }

    /**
     * The spots criterion entry for the Database
     */
    public static abstract class SpotCriteriaEntry implements BaseColumns {
        public static final String TABLE_NAME = "spot_criteria";
        public static final String COLUMN_NAME_LOCATION_ID = "spot_id";
        public static final String COLUMN_NAME_CRITERION_ID = "criterion_id";

        private static final String INT_TYPE = " INTEGER";

        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " +
                        SpotCriteriaEntry.TABLE_NAME +
                        " (" +
                        SpotCriteriaEntry.COLUMN_NAME_LOCATION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        SpotCriteriaEntry.COLUMN_NAME_CRITERION_ID +
                        INT_TYPE +
                        COMMA_SEP +
                        "PRIMARY KEY (" +
                        SpotCriteriaEntry.COLUMN_NAME_LOCATION_ID + "," +
                        SpotCriteriaEntry.COLUMN_NAME_CRITERION_ID +
                        " ))";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + SpotCriteriaEntry.TABLE_NAME;
    }

}
