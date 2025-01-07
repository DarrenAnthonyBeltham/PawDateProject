package id.ac.binus.pawdate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "PawDateDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE IF NOT EXISTS pets " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "breed TEXT, " +
                "age INTEGER, " +
                "vaccinated BOOLEAN)";
        db.execSQL(sqlStatement);

        String createPetsTable = "CREATE TABLE IF NOT EXISTS pets (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "breed TEXT, " +
                "age INTEGER, " +
                "description TEXT, " +
                "vaccinated BOOLEAN, " +
                "imageResId INTEGER)";
        db.execSQL(createPetsTable);

        String createMatchesTable = "CREATE TABLE IF NOT EXISTS matches (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pet_id INTEGER, " +
                "user_id INTEGER, " +
                "FOREIGN KEY(pet_id) REFERENCES pets(id))";
        db.execSQL(createMatchesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pets");
        db.execSQL("DROP TABLE IF EXISTS matches");
        onCreate(db);
    }


    public boolean insertData(String name, String breed, int age, boolean vaccinated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("breed", breed);
        contentValues.put("age", age);
        contentValues.put("vaccinated", vaccinated);
        long status = db.insert("pets", null, contentValues);
        return status != -1;
    }

    public boolean scheduleMatch(int petId, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pet_id", petId);
        contentValues.put("user_id", userId);

        try {
            long result = db.insert("matches", null, contentValues);
            Log.d("DatabaseHelper", "Inserted into matches: petId=" + petId + ", userId=" + userId);
            return result != -1;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting into matches table", e);
            return false;
        }
    }


    public Cursor getMatchesForUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = "SELECT * FROM matches WHERE user_id = ?";
        return db.rawQuery(sqlStatement, new String[]{String.valueOf(userId)});
    }

    public boolean deleteMatch(int matchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("matches", "id = ?", new String[]{String.valueOf(matchId)});
        return rowsDeleted > 0;
    }

    public boolean deletePet(int petId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("pets", "id = ?", new String[]{String.valueOf(petId)});
        return rowsDeleted > 0;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = "SELECT * FROM pets";
        return db.rawQuery(sqlStatement, null);
    }

    public boolean updateData(int id, String name, String breed, int age, boolean vaccinated) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("breed", breed);
        contentValues.put("age", age);
        contentValues.put("vaccinated", vaccinated);
        int rowsAffected = db.update("pets", contentValues, "id = ?", new String[]{String.valueOf(id)});
        return rowsAffected > 0;
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("pets", "id = ?", new String[]{String.valueOf(id)});
        return rowsDeleted < 0;
    }
}