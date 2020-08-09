package com.udacity.jwdnd.course1.cloudstorage.Mappers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("Insert into Notes(notetitle, notedescription, userid) values (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    public int createNotes(Notes notes);

    @Select("Select * from Notes where userid = #{userid}")
    public List<Notes> getAllNotes(Integer userid);

    @Delete("Delete from Notes where userid=#{arg1} and noteid=#{arg0}")
    public void deleteNote(Integer noteid, Integer userid);

    @Update("Update Notes set notetitle = #{notetitle}, notedescription=#{notedescription} where noteid=#{noteid} and userid=#{userid}")
    public void updateNote(Notes notes);
}
