package mdb.de.rating;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by LethmateB on 04.11.2015.
 */

/**
 * The Rate'emDatabaseHelper is used for certain standard database calls
 * that are used multiple times in the app.
 *
 * SEARCH_LIMIT is used for the lazy loading.
 */
public class RateemDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASENAME = "RateemDatabase.db";
    public static final int SEARCH_LIMIT = 50;

    public SQLiteDatabase db = this.getReadableDatabase();

    public RateemDatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }

    /**
     * Set up all the databases on the device
     *
     * @param db the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        this.db.execSQL(RateemDatabase.CategoryEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.CriterionEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.CriterionValuationEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.FavouriteEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.SpotCategoryEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.SpotCriteriaEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.SpotEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.PositionEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.RankEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.RatingEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.UserEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.UserRanksEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     *
     * @param cursor data for the spot
     * @return the spot
     */
    public Spot fillSpotData(Cursor cursor) {
        Spot spot = new Spot();
        spot.setName(cursor.getString(1));
        spot.setStreet(cursor.getString(2));
        spot.setPostcode(cursor.getString(3));
        spot.setCity(cursor.getString(4));
        spot.setCountry(cursor.getString(5));
        spot.location.setLatitude(cursor.getFloat(6));
        spot.location.setLongitude(cursor.getFloat(7));

        return spot;
    }

    /**
     *
     * @param start id to start with
     * @return a list of SEARCH_LIMIT (default 50) spots without any restriction
     */
    public ArrayList<Spot> getAllSpots(int start) {
        ArrayList<Spot> spotArrayList = new ArrayList<>();
        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.SpotEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.SpotEntry.COLUMN_NAME_ID +
                        ">" +
                        start +
                        " LIMIT " +
                        SEARCH_LIMIT;
        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Spot spot = fillSpotData(cursor);
                spotArrayList.add(spot);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return spotArrayList;
    }

    /**
     *
     * @param search the search string
     * @return all spots that have the string occuring in
     * the city, the postcode, the country or the name
     */
    public ArrayList<Spot> getSpotsForSearch(String search) {
        ArrayList<Spot> spotArrayList = new ArrayList<>();

        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.SpotEntry.TABLE_NAME +
                        " WHERE city like '%" +
                        search +
                        "%' OR postcode like '%" +
                        search +
                        "%' OR country like " +
                        search +
                        "%' OR name like '%" +
                        search + "%'";
        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Spot spot = fillSpotData(cursor);
                spotArrayList.add(spot);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return spotArrayList;
    }

    /**
     *
     * @param category the category to get spots for
     * @param start the id to start with
     * @return a list of SEARCH_LIMIT (default 50) spots that are attached to this category
     */
    public ArrayList<Spot> getSpotsForCategory(String category, int start) {
        ArrayList<Spot> spotArrayList = new ArrayList<>();

        String categoryQuery =
                "SELECT id FROM " +
                        RateemDatabase.CategoryEntry.TABLE_NAME +
                        " WHERE name like " +
                        category +
                        " AND id>" +
                        start +
                        " LIMIT " +
                        SEARCH_LIMIT;

        Cursor cursor = this.db.rawQuery(categoryQuery, null);
        if (cursor.moveToFirst()) {
            String selectQuery =
                    "SELECT * FROM " +
                            RateemDatabase.SpotEntry.TABLE_NAME +
                            " WHERE category_id = " +
                            cursor.getInt(0);
            cursor.close();
            cursor = this.db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do spotArrayList.add(fillSpotData(cursor));
                while (cursor.moveToNext());
            }

            cursor.close();
        }
        return spotArrayList;
    }

    /**
     *
     * @param city the city to find spots for
     * @param start the id to start with
     * @return a list of all spots in the city
     */
    public ArrayList<Spot> getSpotsForCity(String city, int start) {
        ArrayList<Spot> spotArrayList = new ArrayList<>();

        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.SpotEntry.TABLE_NAME +
                        " WHERE city = '" +
                        city + "'";

        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do spotArrayList.add(fillSpotData(cursor));
            while (cursor.moveToNext());
        }
        cursor.close();
        return spotArrayList;
    }

    /**
     *
     * @param alias the username
     * @return all ranks the user has achieved
     */
    public ArrayList<Rank> getRanksForUser(String alias) {
        ArrayList<Rank> userRanksList = new ArrayList<>();

        String userQuery =
                "SELECT id FROM " +
                        RateemDatabase.UserEntry.TABLE_NAME +
                        " WHERE alias = '" +
                        alias + "'";

        Cursor cursor = this.db.rawQuery(userQuery, null);
        if (cursor.moveToFirst()) {
            String rankQuery =
                    "SELECT rank_id FROM " +
                            RateemDatabase.UserRanksEntry.TABLE_NAME +
                            " WHERE user_id = " +
                            cursor.getInt(0);
            cursor.close();
            cursor = this.db.rawQuery(rankQuery, null);

            if (cursor.moveToFirst()) {
                String selectQuery =
                        "SELECT name FROM " +
                                RateemDatabase.RankEntry.TABLE_NAME +
                                " WHERE id = " +
                                cursor.getInt(0);
                cursor.close();
                cursor = this.db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do {
                        Rank rank = new Rank();
                        rank.setName(cursor.getString(0));
                        userRanksList.add(rank);
                    }
                    while (cursor.moveToNext());
                }
            }
        }
        cursor.close();
        return userRanksList;
    }
}
