package com.oasystem.service.schedule.impl;

import com.oasystem.ResultDTO;
import com.oasystem.dao.schedule.NoteGroupMapper;
import com.oasystem.dao.schedule.NoteMapper;
import com.oasystem.dao.schedule.ScheduleInfoMapper;
import com.oasystem.model.Note;
import com.oasystem.model.NoteGroup;
import com.oasystem.model.ScheduleInfo;
import com.oasystem.service.schedule.NoteService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteGroupMapper noteGroupMapper;

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private ScheduleInfoMapper scheduleInfoMapper;


    @Override
    public ResultDTO<Boolean> insertNoteGroup(NoteGroup noteGroup) {
        int i = noteGroupMapper.insertSelective(noteGroup);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<NoteGroup>> queryNoteGroup(Integer userId) {
        List<NoteGroup> noteGroups = noteGroupMapper.selectAllByUserId(userId);
        return ResultDTO.successResult(noteGroups);
    }

    @Override
    public ResultDTO<Boolean> delteNoteGroup(Integer id) {
        int i = noteGroupMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> UpdateNoteGroup(NoteGroup noteGroup) {
        int i = noteGroupMapper.updateByPrimaryKeySelective(noteGroup);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<List<Note>> queryNoteByNoteGroup(Integer noteGruopId, Integer userId) {
        List<Note> notes = noteMapper.selectAllByNoteGroup(noteGruopId, userId);

        return ResultDTO.successResult(notes);
    }

    @Override
    public ResultDTO<Boolean> addNote(Note note) {
        if (ObjectUtils.isNotEmpty(note.getEndtime())) {
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setUserId(note.getUserId());
            scheduleInfo.setEndTime(note.getEndtime());
            scheduleInfo.setStartTime(new Date());
            scheduleInfo.setContent(note.getContent());
            scheduleInfoMapper.insertSelective(scheduleInfo);
        }
        int i = noteMapper.insertSelective(note);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> updateNote(Note note) {
        int i = noteMapper.updateByPrimaryKeySelective(note);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> deleteNote(Integer id) {
        int i = noteMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return ResultDTO.successResult(true);
        }
        return ResultDTO.successResult(false);
    }

    @Override
    public ResultDTO<Boolean> updateNoteDone(List<Note> noteList) {
        Boolean flag = false;
        for (Note note : noteList) {
            Integer isDone = note.getIsDone();
            if (isDone == 0) {
                note.setIsDone(1);
            } else {
                note.setIsDone(0);
            }
            ResultDTO<Boolean> booleanResultDTO = this.updateNote(note);
            flag = booleanResultDTO.getData();
        }
        return ResultDTO.successResult(flag);

    }
}
