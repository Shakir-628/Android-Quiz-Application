package com.example.simplequizapplication

import android.database.sqlite.SQLiteOpenHelper
import com.example.simplequizapplication.QuizContract.MovieEntry
import android.content.ContentValues
import android.content.Context
import java.util.ArrayList
import android.database.sqlite.SQLiteDatabase as SQLiteDatabase1

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // tasks table name
   private lateinit var dbase : SQLiteDatabase1

    override fun onCreate(db: SQLiteDatabase1) {
        dbase = db
        val sql = ("CREATE TABLE IF NOT EXISTS " + MovieEntry.TABLE_QUEST + " ( "
                + MovieEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MovieEntry.KEY_QUES
                + " TEXT, " + MovieEntry.KEY_ANSWER + " TEXT, " + MovieEntry.KEY_OPTA + " TEXT, "
                + MovieEntry.KEY_OPTB + " TEXT, " + MovieEntry.KEY_OPTC + " TEXT," + MovieEntry.KEY_OPTD + " TEXT)")
        db.execSQL(sql)
        addQuestions()
        //db.close();
    }

    fun addQuestions(
        ques: String,
        option1: String,
        option2: String,
        option3: String,
        option4: String,
        answer: String
    ) {
        dbase = this.writableDatabase
        val question = Question(ques, option1, option2, option3, option4, answer)
        addQuestion(question)
    }

    fun addQuestions() {
        /*   val question = Question(ques!!, option1!!, option2!!, option3!!, option4!!, answer!!)
            addQuestion(question)*/
        val q1 = Question(
            "If price of one book is Rs.21 then price of such three books is:",
            "63",
            "43 ",
            "23",
            "13",
            "63"
        )
        this.addQuestion(q1)
        val q2 = Question("26261 x 10:", "262610:", "26261", "2626110:", "2626100:", "262610:")
        this.addQuestion(q2)
        val q3 = Question("98 centimeters:?", "98/100meters", "0.98meters", "Both", "None", "Both")
        this.addQuestion(q3)
        val q4 = Question("LCM of 1, 2, 3 and 6 is:", "1", "2", "3", "6", "6")
        this.addQuestion(q4)
        val q5 = Question(
            "If area of rectangular field is 324 square meters and its length is 27meters then its breadth will be:",
            "12",
            "13",
            "14",
            "15",
            "12"
        )
        this.addQuestion(q5)
        val q6 = Question(
            "If the price of 5 books is Rs. 150 then the price of one book will be:",
            "30",
            "50",
            "20",
            "10",
            "30"
        )
        this.addQuestion(q6)
        val q7 = Question("25/5+5:?", "1", "10", "20", "5", "10")
        this.addQuestion(q7)
        val q8 = Question(
            "Find the smallest number which is exactly divisible by 8,12 and 24:",
            "8",
            "12",
            "24",
            "30",
            "24"
        )
        this.addQuestion(q8)
        val q9 = Question(
            "Raza obtained 6, 7, 8 and 9 marks in four different mathematics tests. The average marks she obtained in each test are:",
            "7.5",
            "8.5",
            "9.5",
            "10.5",
            "7.5"
        )
        this.addQuestion(q9)
        val q10 = Question(
            "Majid participated in 60 matches and won 40% of the matches. How many matches did he win: ?",
            "240",
            "100",
            "20",
            "24",
            "24"
        )
        this.addQuestion(q10)
        val q11 = Question(
            "The number just before 53 is: ?",
            "52",
            "48",
            "59",
            "31",
            "52"
        )
        this.addQuestion(q11)
        val q12 = Question(
            "Put [> or <] correctly in the blank 12 ____ 18: ?",
            "=",
            ">",
            "<",
            "None of these",
            "<"
        )
        this.addQuestion(q12)
    }

    override fun onUpgrade(db: SQLiteDatabase1, oldV: Int, newV: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_QUEST)
        // Create tables again
        onCreate(db)
    }

    // Adding new question
    fun addQuestion(quest: Question) {
        //SQLiteDatabase1 db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(MovieEntry.KEY_QUES, quest.qUESTION)
        values.put(MovieEntry.KEY_ANSWER, quest.aNSWER)
        values.put(MovieEntry.KEY_OPTA, quest.oPTA)
        values.put(MovieEntry.KEY_OPTB, quest.oPTB)
        values.put(MovieEntry.KEY_OPTC, quest.oPTC)
        values.put(MovieEntry.KEY_OPTD, quest.oPTD)
        // Inserting Row
        dbase.insert(MovieEntry.TABLE_QUEST, null, values)
    }

    // Select All Query

    // looping through all rows and adding to list
    // return quest list
    fun allQuestions(): List<Question> {
        val quesList: MutableList<Question> = ArrayList()
        // Select All Query
        val selectQuery = "SELECT  * FROM " + MovieEntry.TABLE_QUEST
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest = Question()
                quest.iD = cursor.getInt(0)
                quest.qUESTION = cursor.getString(1)
                quest.aNSWER = cursor.getString(2)
                quest.oPTA = cursor.getString(3)
                quest.oPTB = cursor.getString(4)
                quest.oPTC = cursor.getString(5)
                quest.oPTD = cursor.getString(6)
                quesList.add(quest)
            } while (cursor.moveToNext())
        }
        // return quest list
        return quesList
    }

    fun rowcount(): Int {
        var row = 0
        val selectQuery = "SELECT  * FROM " + MovieEntry.TABLE_QUEST
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        row = cursor.count
        return row
    }

    companion object {
        private const val DATABASE_VERSION = 1
        // Database Name
        private const val DATABASE_NAME = "triviaQuiz"
    }
}