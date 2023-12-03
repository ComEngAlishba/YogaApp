package Models;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "YourDatabaseName";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_COURSE = "Course";
    private static final String TABLE_SCHEDULE = "ClassSchedule";

    // Column names for Course Table
    private static final String KEY_COURSE_ID = "course_id";
    private static final String KEY_DAY_OF_WEEK = "day_of_week";
    private static final String KEY_TIME_OF_COURSE = "time_of_course";
    private static final String KEY_CAPACITY = "capacity";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_PRICE_PER_CLASS = "price_per_class";
    private static final String KEY_TYPE_OF_CLASS = "type_of_class";
    private static final String KEY_DESCRIPTION = "description";

    // Column names for ClassSchedule table
    private static final String KEY_SCHEDULE_ID = "schedule_id";
    private static final String KEY_SCHEDULE_DATE = "date";
    private static final String KEY_SCHEDULE_TEACHER_NAME = "teacher_name";
    private static final String KEY_COURSE_NAME = "course_name";
    private static final String KEY_SCHEDULE_ADDITIONAL_COMMENTS = "additional_comments";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Course table
        String createCourseTable = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_COURSE_ID + " INTEGER PRIMARY KEY,"
                + KEY_DAY_OF_WEEK + " TEXT NOT NULL,"
                + KEY_TIME_OF_COURSE + " TEXT NOT NULL,"
                + KEY_CAPACITY + " INTEGER,"
                + KEY_DURATION + " INTEGER,"
                + KEY_PRICE_PER_CLASS + " REAL,"
                + KEY_TYPE_OF_CLASS + " TEXT NOT NULL,"
                + KEY_DESCRIPTION + " TEXT "
                + ")";
        db.execSQL(createCourseTable);
        // Create ClassSchedule table
        String createScheduleTable = "CREATE TABLE " + TABLE_SCHEDULE + "("
                + KEY_SCHEDULE_ID + " INTEGER PRIMARY KEY,"
                + KEY_SCHEDULE_DATE + " TEXT NOT NULL,"
                + KEY_SCHEDULE_TEACHER_NAME + " TEXT NOT NULL,"
                + KEY_COURSE_NAME + " TEXT NOT NULL,"
                + KEY_SCHEDULE_ADDITIONAL_COMMENTS + " TEXT "
                + ")";
        db.execSQL(createScheduleTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        onCreate(db);
    }

    // Function to add a course
    public void addCourse(Course course, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAY_OF_WEEK, course.getDayOfWeek());
        values.put(KEY_TIME_OF_COURSE, course.getTimeOfCourse());
        values.put(KEY_CAPACITY, course.getCapacity());
        values.put(KEY_DURATION, course.getDuration());
        values.put(KEY_PRICE_PER_CLASS, course.getPricePerClass());
        values.put(KEY_TYPE_OF_CLASS, course.getTypeOfClass());
        values.put(KEY_DESCRIPTION, course.getDescription());

        // Inserting Row
        long result = db.insert(TABLE_COURSE, null, values);

        // Log the SQL query
        String sqlQuery = SQLiteQueryBuilder.buildQueryString(
                false, TABLE_COURSE, null, null, null, null, null, null);
        Log.d("DatabaseHelper", "SQL Query: " + sqlQuery);

        db.close(); // Closing database connection

        if (result != -1) {
            showToast(context, "Course added successfully");
        } else {
            showToast(context, "Failed to add course");
        }
    }

    // Function to delete a course
    public void deleteCourse(int courseId, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_COURSE, KEY_COURSE_ID + " = ?",
                new String[]{String.valueOf(courseId)});
        db.close();

        // Display toast based on the result
        if (result > 0) {
            showToast(context, "Course deleted successfully");
        } else {
            showToast(context, "Failed to delete course");
        }
    }
    @SuppressLint("Range")
    public Course getCourseByCourseId(int courseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Course course = null;

        String[] columns = {
                KEY_COURSE_ID,
                KEY_DAY_OF_WEEK,
                KEY_TIME_OF_COURSE,
                KEY_CAPACITY,
                KEY_DURATION,
                KEY_PRICE_PER_CLASS,
                KEY_TYPE_OF_CLASS,
                KEY_DESCRIPTION,
        };

        String selection = KEY_COURSE_ID + "=?";
        String[] selectionArgs = {String.valueOf(courseId)};

        Cursor cursor = db.query(TABLE_COURSE, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            course = new Course();
            course.setCourseId(cursor.getInt(cursor.getColumnIndex(KEY_COURSE_ID)));
            course.setDayOfWeek(cursor.getString(cursor.getColumnIndex(KEY_DAY_OF_WEEK)));
            course.setTimeOfCourse(cursor.getString(cursor.getColumnIndex(KEY_TIME_OF_COURSE)));
            course.setCapacity(cursor.getInt(cursor.getColumnIndex(KEY_CAPACITY)));
            course.setDuration(cursor.getInt(cursor.getColumnIndex(KEY_DURATION)));
            course.setPricePerClass(cursor.getDouble(cursor.getColumnIndex(KEY_PRICE_PER_CLASS)));
            course.setTypeOfClass(cursor.getString(cursor.getColumnIndex(KEY_TYPE_OF_CLASS)));
            course.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));

            cursor.close();
        }

        db.close();

        return course;
    }

    // Function to get all courses
    @SuppressLint("Range")
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourseId(cursor.getInt(cursor.getColumnIndex(KEY_COURSE_ID)));
                course.setDayOfWeek(cursor.getString(cursor.getColumnIndex(KEY_DAY_OF_WEEK)));
                course.setTimeOfCourse(cursor.getString(cursor.getColumnIndex(KEY_TIME_OF_COURSE)));
                course.setCapacity(cursor.getInt(cursor.getColumnIndex(KEY_CAPACITY)));
                course.setDuration(cursor.getInt(cursor.getColumnIndex(KEY_DURATION)));
                course.setPricePerClass(cursor.getDouble(cursor.getColumnIndex(KEY_PRICE_PER_CLASS)));
                course.setTypeOfClass(cursor.getString(cursor.getColumnIndex(KEY_TYPE_OF_CLASS)));
                course.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;
    }


    //updating course
    public boolean updateCourse(String id, String day, String time, String capacity, String duration, String price, String yogaType, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAY_OF_WEEK, day);
        values.put(KEY_TIME_OF_COURSE, time);
        values.put(KEY_CAPACITY, capacity);
        values.put(KEY_DURATION, duration);
        values.put(KEY_TYPE_OF_CLASS, yogaType);
        values.put(KEY_DESCRIPTION, description);

        long result = db.update(TABLE_COURSE, values, KEY_COURSE_ID + " = ?", new String[]{id});

        boolean isUpdated = result > 0;
        db.close();
        return isUpdated;
    }
//updateCourse function
   /* public boolean updateCourse(Course course)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DAY_OF_WEEK, course.getDayOfWeek());
        values.put(KEY_TIME_OF_COURSE, course.getTimeOfCourse());
        values.put(KEY_CAPACITY, course.getCapacity());
        values.put(KEY_DURATION, course.getDuration());
        values.put(KEY_PRICE_PER_CLASS, course.getPricePerClass());
        values.put(KEY_TYPE_OF_CLASS, course.getTypeOfClass());
        values.put(KEY_DESCRIPTION, course.getDescription());

        long result = db.update(TABLE_COURSE, values, KEY_COURSE_ID + " = ?", new String[]{String.valueOf(course.getCourseId())});

        boolean isUpdated = result > 0;
        db.close();
        return isUpdated;
    }
*/

    // Function to add a class schedule
    public void addClassSchedule(ClassSchedule classSchedule, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCHEDULE_DATE, classSchedule.getDate());
        values.put(KEY_SCHEDULE_TEACHER_NAME, classSchedule.getTeacherName());
        values.put(KEY_COURSE_NAME, classSchedule.getCourseName());
        values.put(KEY_SCHEDULE_ADDITIONAL_COMMENTS, classSchedule.getAdditionalComments());

        long result = db.insert(TABLE_SCHEDULE, null, values);
        db.close();

        // Display toast based on the result
        if (result != -1) {
            showToast(context, "Class schedule added successfully");
        } else {
            showToast(context, "Failed to add class schedule");
        }
    }

    //code trial
    public boolean updateClassSchedule(String id, String date, String teacherName, String courseName, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCHEDULE_DATE, date);
        values.put(KEY_SCHEDULE_TEACHER_NAME, teacherName);
        values.put(KEY_COURSE_NAME, courseName);
        values.put(KEY_SCHEDULE_ADDITIONAL_COMMENTS, comment);

        long result = db.update(TABLE_SCHEDULE, values, KEY_SCHEDULE_ID + " = ?", new String[]{id});

        boolean isUpdated = result > 0;
        db.close();
        return isUpdated;
    }

    // Function to delete a class schedule
    public void deleteClassSchedule(int scheduleId, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_SCHEDULE, KEY_SCHEDULE_ID + " = ?",
                new String[]{String.valueOf(scheduleId)});
        db.close();

        // Display toast based on the result
        if (result > 0) {
            showToast(context, "Class schedule deleted successfully");
        } else {
            showToast(context, "Failed to delete class schedule");
        }
    }

    // Function to get all class schedules for a course
    @SuppressLint("Range")
    public List<ClassSchedule> getAllClassSchedulesForCourse(int courseId) {
        List<ClassSchedule> scheduleList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SCHEDULE +
                " WHERE " + KEY_COURSE_ID + " = " + courseId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ClassSchedule classSchedule = new ClassSchedule();
                classSchedule.setScheduleId(cursor.getInt(cursor.getColumnIndex(KEY_SCHEDULE_ID)));
                classSchedule.setDate(cursor.getString(cursor.getColumnIndex(KEY_SCHEDULE_DATE)));
                classSchedule.setTeacherName(cursor.getString(cursor.getColumnIndex(KEY_SCHEDULE_TEACHER_NAME)));
                classSchedule.setCourseName(cursor.getString(cursor.getColumnIndex(KEY_COURSE_NAME)));
                classSchedule.setAdditionalComments(cursor.getString(cursor.getColumnIndex(KEY_SCHEDULE_ADDITIONAL_COMMENTS)));

                scheduleList.add(classSchedule);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return scheduleList;
    }

    // Helper method to display toast messages
    private void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public Cursor readData(){
        String query = "SELECT * FROM " + TABLE_SCHEDULE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //for deleting data from schedule table
    public void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_SCHEDULE, KEY_SCHEDULE_ID + " = ?", new String[]{row_id});

        if (result > 0) {
            // Deletion successful
            Log.d("DatabaseHelper", "Data deleted successfully");
        } else {
            // Deletion failed
            Log.e("DatabaseHelper", "Failed to delete data");
        }

        db.close();
    }

    public Cursor readCourses(){
        String query = "SELECT * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
