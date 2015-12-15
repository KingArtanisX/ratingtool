package mdb.de.rating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
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
    public static final int DATABASE_VERSION = 2;
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

        insertDemoData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     *
     * @param cursor data for the rating
     * @return the rating
     */
    public Rating fillRatingData(Cursor cursor) {
        Rating rating = new Rating();
        rating.setId(cursor.getInt(0));
        rating.setText(cursor.getString(1));
        rating.setUser_id(cursor.getInt(2));
        rating.setSpot_id(cursor.getInt(3));
        rating.setReports(cursor.getInt(4));
        return rating;
    }

    /**
     *
     * @param cursor data for the spot
     * @return the spot
     */
    public Spot fillSpotData(Cursor cursor) {
        Spot spot = new Spot();
        spot.setId(cursor.getInt(0));
        spot.setName(cursor.getString(1));
        spot.setStreet(cursor.getString(2));
        spot.setPostcode(cursor.getString(3));
        spot.setCity(cursor.getString(4));
        spot.setCountry(cursor.getString(5));
        spot.setLatitude(cursor.getLong(6));
        spot.setLongitude(cursor.getLong(7));
        spot.setRating(getRatingForSpot(spot.getId()));
        return spot;
    }

    //region getters

    public ArrayList<Criterion> getCriteriaForSpot(Integer spotid) {
        ArrayList<Criterion> criterionArrayList = new ArrayList<>();
        String selectQuery =
                "SELECT " + RateemDatabase.SpotCriteriaEntry.COLUMN_NAME_CRITERION_ID+ " FROM " +
                        RateemDatabase.SpotCriteriaEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.SpotCriteriaEntry.COLUMN_NAME_LOCATION_ID +
                        "=" +
                        spotid;
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                selectQuery =
                        "SELECT * FROM " +
                                RateemDatabase.CriterionEntry.TABLE_NAME +
                                " WHERE " +
                                RateemDatabase.CriterionEntry.COLUMN_NAME_ID +
                                "=" +
                                cursor.getInt(0);
                Cursor cursor2 = this.db.rawQuery(selectQuery, null);
                if (cursor2.moveToFirst()) {
                    do {
                        Criterion criterion = new Criterion();
                        criterion.setId(cursor2.getInt(0));
                        criterion.setName(cursor2.getString(1));
                        criterionArrayList.add(criterion);
                    } while (cursor2.moveToNext());
                }
                cursor2.close();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return criterionArrayList;
    }

    public Float getRatingForSpot(Integer spotid) {
        Float rating = 0F;
        String selectQuery =
                "SELECT id FROM " +
                        RateemDatabase.RatingEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.RatingEntry.COLUMN_NAME_LOCATION_ID +
                        "=" +
                        spotid;
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                selectQuery =
                        "SELECT stars FROM " +
                                RateemDatabase.CriterionValuationEntry.TABLE_NAME +
                                " WHERE " +
                                RateemDatabase.CriterionValuationEntry.COLUMN_NAME_RATING_ID +
                                "=" +
                                cursor.getInt(0);
                Cursor cursor2 = this.db.rawQuery(selectQuery, null);
                if (cursor2.moveToFirst()) {
                    Integer i = 0;
                    do {
                        i++;
                        rating += cursor2.getInt(0);
                    } while (cursor2.moveToNext());
                    rating = rating/i;
                }
                cursor2.close();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return rating;
    }

    public ArrayList<Rating> getRatingsForSpot(Integer spotid, Integer start) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        String selectQuery =
                "SELECT * FROM " +
                        RateemDatabase.RatingEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.RatingEntry.COLUMN_NAME_LOCATION_ID +
                        "=" +
                        spotid +
                        " AND " +
                        RateemDatabase.RatingEntry.COLUMN_NAME_ID +
                        " > " +
                        start +
                        " LIMIT " +
                        SEARCH_LIMIT;
        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Rating rating = fillRatingData(cursor);
                ratingArrayList.add(rating);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return ratingArrayList;
    }

    /**
     *
     * @param id id to start with
     * @return a list of SEARCH_LIMIT (default 50) spots without any restriction
     */
    public Spot getSpotForId(Integer id) {
        Spot spot = new Spot();
        String selectQuery =
                "SELECT * FROM " +
                        RateemDatabase.SpotEntry.TABLE_NAME +
                        " WHERE " +
                        RateemDatabase.SpotEntry.COLUMN_NAME_ID +
                        " LIKE " +
                        id;
        Cursor cursor = this.db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            spot = fillSpotData(cursor);
        }

        cursor.close();
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
        city = "Gelsenkirchen";
        String selectQuery =
                        "SELECT * FROM " +
                        RateemDatabase.SpotEntry.TABLE_NAME +
                        " WHERE city LIKE '" +
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

    //endregion getters

    //region inserts

    public Boolean insertRatingForSpot( ArrayList<CriterionRating> criterionRatingArrayList, Rating rating) {
        ContentValues values = new ContentValues();

        /**
         * TODO:
         * block rating table for others
         * insert Rating into DB
         * select id of last rating
         * open rating table for others
         * insert CriterionValuationEntry
         *
         */
        return true;
    }

    public void insertDemoData() {
        ContentValues values = new ContentValues();

        /*Kategorien*/
        values.put("name", "Essen & Trinken");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.CategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Lifestyle");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.CategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Sport");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.CategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Shopping");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.CategoryEntry.TABLE_NAME, null, values);
        values.clear();

        /*Orte*/
        values.put("name", "Veltins Arena");
        values.put("street", "Arenaring 1");
        values.put("postcode", "45891");
        values.put("city", "Gelsenkirchen");
        values.put("country", "Deutschland");
        values.put("longitude", "7,067639");
        values.put("latitude", "51,554472");
        values.put("visible", "y");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.SpotEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Alte Hütte");
        values.put("street", "Middelicher Str. 187");
        values.put("postcode", "45892 ");
        values.put("city", "Gelsenkirchen");
        values.put("country", "Deutschland");
        values.put("longitude", "7,101099");
        values.put("latitude", "51,576610");
        values.put("visible", "y");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.SpotEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Copa Ca Backum");
        values.put("street", "Teichstraße 18");
        values.put("postcode", "45699");
        values.put("city", "Herten");
        values.put("country", "Deutschland");
        values.put("longitude", "7,139513");
        values.put("latitude", "51,602867");
        values.put("visible", "y");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.SpotEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("name", "Hotel und Restaurant La Scala");
        values.put("street", "Schlesischer Ring 3");
        values.put("postcode", "45894");
        values.put("city", "Gelsenkirchen");
        values.put("country", "Deutschland");
        values.put("longitude", "7,042336");
        values.put("latitude", "51,581573");
        values.put("visible", "y");
        values.put("deletable", "n");
        this.db.insert(RateemDatabase.SpotEntry.TABLE_NAME, null, values);
        values.clear();

        /*Orte/Kategorie*/
        values.put("spot_id", 1);
        values.put("category_id", 3);
        this.db.insert(RateemDatabase.SpotCategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 2);
        values.put("category_id", 2);
        this.db.insert(RateemDatabase.SpotCategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("category_id", 3);
        this.db.insert(RateemDatabase.SpotCategoryEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 4);
        values.put("category_id", 1);
        this.db.insert(RateemDatabase.SpotCategoryEntry.TABLE_NAME, null, values);
        values.clear();

        /*Kriterien*/
        values.put("id", 1);
        values.put("name", "Service");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 2);
        values.put("name", "Sauberkeit");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 3);
        values.put("name", "Auftreten des Personals");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 4);
        values.put("name", "Erreichbarkeit (Infrastruktur)");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 5);
        values.put("name", "Parkmöglichkeiten");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 6);
        values.put("name", "Preis/Leistung");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 7);
        values.put("name", "Location");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 8);
        values.put("name", "Kapazität");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 9);
        values.put("name", "Essen & Trinken");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 10);
        values.put("name", "Öffnungszeiten");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 11);
        values.put("name", "Sanitäre Anlagen");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 12);
        values.put("name", "Zahlungsmöglichkeiten");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("id", 13);
        values.put("name", "Angebot und Auswahl");
        this.db.insert(RateemDatabase.CriterionEntry.TABLE_NAME, null, values);
        values.clear();

        /*Kriterien/Ort*/
        values.put("spot_id", 1);
        values.put("criterion_id", 4);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 1);
        values.put("criterion_id", 5);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 1);
        values.put("criterion_id", 8);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("criterion_id", 2);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("criterion_id", 4);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("criterion_id", 5);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("criterion_id", 6);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
        values.put("spot_id", 3);
        values.put("criterion_id", 7);
        this.db.insert(RateemDatabase.SpotCriteriaEntry.TABLE_NAME, null, values);
        values.clear();
    }
    //endregion inserts
}
