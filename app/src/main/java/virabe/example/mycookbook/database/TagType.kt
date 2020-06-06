package virabe.example.mycookbook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Beenal Patel on 03-06-2020.
 */
@Entity(tableName = "TagType")
data class TagType(@PrimaryKey(autoGenerate = true)
              @ColumnInfo(name = "tagTypeId")
              val tagTypeId: Long = 0,
              @ColumnInfo(name = "tagTypeName")
              var tagTypeName: String)