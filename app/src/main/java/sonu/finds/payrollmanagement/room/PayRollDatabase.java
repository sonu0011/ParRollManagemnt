package sonu.finds.payrollmanagement.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {PayRollEntity.class},version = 1)
public abstract class PayRollDatabase extends RoomDatabase {

    private static PayRollDatabase payRollDatabase;

    public abstract PaYRollDao paYRollDao();

    public static synchronized PayRollDatabase getPayRollDatabase(Context context){

        if (payRollDatabase == null){
            payRollDatabase =    Room.databaseBuilder(context,PayRollDatabase.class,"payroll_db")
                                .addMigrations()
                                .build();
        }
        return payRollDatabase;
    }
}
