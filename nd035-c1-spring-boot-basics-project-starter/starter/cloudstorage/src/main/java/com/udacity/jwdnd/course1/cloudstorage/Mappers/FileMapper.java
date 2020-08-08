package com.udacity.jwdnd.course1.cloudstorage.Mappers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("Select * from Files where userid=${userid}")
    List<Files> getAllFiles(Integer userid);

    @Insert("Insert into Files(filename, contenttype, filesize, filedata, userid) values(#{filename}, #{contenttype}, #{filesize}, #{filedata}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFiles(Files file);

    @Delete("Delete from Files where fileid = #{arg0} and userid = #{arg1}")
    void deleteFile(int fileId, int userId);
}
