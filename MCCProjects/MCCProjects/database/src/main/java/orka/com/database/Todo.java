package orka.com.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
        @PrimaryKey
        public int uid;


        public Todo(int uid,String name) {
            this.uid = uid;
            this.name = name;
        }

    @ColumnInfo(name = "name")
        public String name;


}
