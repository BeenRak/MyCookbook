package virabe.example.mycookbook.database

import androidx.room.*

/**
 * Created by Beenal Patel on 03-06-2020.
 */
@Entity(
    tableName = "Tag", indices = [(Index(value = ["tagId"], name = "idx_tags_tag_id"))],
    foreignKeys = [(ForeignKey(
        entity = TagType::class,
        parentColumns = ["tagType"],
        childColumns = ["tagTypeId"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ))]
)
data class Tag(
    @ColumnInfo(name = "tagTypeId") val tagTypeId: Long,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "tagId") val tagId: Long,
    @ColumnInfo(name = "tagName") val name: String
)