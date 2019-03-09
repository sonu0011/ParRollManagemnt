package sonu.finds.payrollmanagement.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface PaYRollDao {

    @Insert
    long insert(PayRollEntity entity);

    @Query("SELECT * FROM payroll_table WHERE id = :id")
    PayRollEntity getResult(int id);


}
