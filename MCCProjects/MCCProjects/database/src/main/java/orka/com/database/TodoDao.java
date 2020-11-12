package orka.com.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TodoDao {


        @Query("SELECT * FROM todo")
        List<Todo> getAll();



        @Insert
        void insertAll(Todo... todo);

        @Delete
        void delete(Todo user);


}
