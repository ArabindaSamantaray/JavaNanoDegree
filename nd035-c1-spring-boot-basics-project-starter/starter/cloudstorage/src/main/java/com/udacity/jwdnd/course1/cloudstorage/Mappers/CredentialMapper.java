package com.udacity.jwdnd.course1.cloudstorage.Mappers;

import com.udacity.jwdnd.course1.cloudstorage.Models.Credentials;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.persistence.GeneratedValue;
import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("Insert into Credentials (url, username, key, password, userid) values (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    public int createCredentials(Credentials credentials);

    @Select("Select * from Credentials where userid = #{arg0}")
    public List<Credentials> getCredentials(Integer userid);

    @Delete("Delete from Credentials where credentialid=#{arg0} and userid=#{arg1}")
    public void deleteCredentials(Integer credentialId, Integer userId);

    @Update("Update Credentials set url = #{url}, username=#{username}, password=#{password}, key=#{key} where credentialid=#{credentialid} and userid=#{userid}")
    public void updateCredentials(Credentials credentials);
}
