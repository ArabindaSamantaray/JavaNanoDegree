package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Models.Notes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class NoteService {

    NoteMapper noteMapper;
    UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public void createNotes(Notes notes, String userName){
        Integer userid = userMapper.getUser(userName).getUserid();
        notes.setUserid(userid);
        noteMapper.createNotes(notes);
    }

    public List<Notes> getAllNotes(String userName){
        Integer userid = userMapper.getUser(userName).getUserid();
        return noteMapper.getAllNotes(userid);
    }

    public void deleteNotes(Integer noteid, String userName){
        Integer userid = userMapper.getUser(userName).getUserid();
        noteMapper.deleteNote(noteid, userid);
    }

    public void updateNotes(Notes notes, String userName){
        Integer userid = userMapper.getUser(userName).getUserid();
        notes.setUserid(userid);
        noteMapper.updateNote(notes);
    }
}
