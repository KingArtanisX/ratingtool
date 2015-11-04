package mdb.de.rating;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by LethmateB on 04.11.2015.
 */
public class RateemDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASENAME = "RateemDatabase.db";
    public static final int SEARCH_LIMIT = 50;

    public SQLiteDatabase db = this.getReadableDatabase();

    public RateemDatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        this.db.execSQL(RateemDatabase.CategoryEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.CriterionEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.CriterionValuationEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.FavouriteEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.LocationCategoryEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.LocationCriteriaEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.LocationEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.PositionEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.RankEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.RatingEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.UserEntry.SQL_CREATE_ENTRIES);
        this.db.execSQL(RateemDatabase.UserRanksEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Location fillLocationData(Cursor cursor) {
        Location location = new Location();
        location.setName(cursor.getString(1));
        location.setStreet(cursor.getString(2));
        location.setPostcode(cursor.getString(3));
        location.setCity(cursor.getString(4));
        location.setCountry(cursor.getString(5));
        location.setLatitude(cursor.getFloat(6));
        location.setLongitude(cursor.getFloat(7));

        return location;
    }

    public ArrayList<Location> getAllLocations(int start) {
        ArrayList<Location> locationArrayList = new ArrayList<>();
        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.LocationEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.LocationEntry.COLUMN_NAME_ID +
                        ">" +
                        start +
                        " LIMIT " +
                        SEARCH_LIMIT;
        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Location location = fillLocationData(cursor);
                locationArrayList.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return locationArrayList;
    }

    public ArrayList<Location> getLocationsForSearch(String search) {
        ArrayList<Location> locationArrayList = new ArrayList<>();

        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.LocationEntry.TABLE_NAME +
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
                Location location = fillLocationData(cursor);
                locationArrayList.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return locationArrayList;
    }

    public ArrayList<Location> getLocationsForCategory(String category, int start) {
        ArrayList<Location> locationArrayList = new ArrayList<>();

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
                            RateemDatabase.LocationEntry.TABLE_NAME +
                            " WHERE category_id = " +
                            cursor.getInt(0);
            cursor.close();
            cursor = this.db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do locationArrayList.add(fillLocationData(cursor));
                while (cursor.moveToNext());
            }

            cursor.close();
        }
        return locationArrayList;
    }

    public ArrayList<Location> getLocationsForCity(String city, int start) {
        ArrayList<Location> locationArrayList = new ArrayList<>();

        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.LocationEntry.TABLE_NAME +
                        " WHERE city = " +
                        city;

        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do locationArrayList.add(fillLocationData(cursor));
            while (cursor.moveToNext());
        }
        cursor.close();
        return locationArrayList;
    }


}
