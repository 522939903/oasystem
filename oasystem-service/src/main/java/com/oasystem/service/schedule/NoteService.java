package com.oasystem.service.schedule;

import com.oasystem.ResultDTO;
import com.oasystem.model.Note;
import com.oasystem.model.NoteGroup;

import java.util.List;

/**
 * @ClassName NoteService
 * @Description
 * @Author suguoming
 * @Date 2020/2/26 8:39 下午
 */
public interface NoteService {

    /**
     * 新增便签分组
     *
     * @param noteGroup
     * @return
     */
    ResultDTO<Boolean> insertNoteGroup(NoteGroup noteGroup);

    /**
     * 查询用户的便签分组
     *
     * @param userId
     * @return
     */
    ResultDTO<List<NoteGroup>> queryNoteGroup(Integer userId);

    /**
     * 删除便签
     *
     * @param id
     * @return
     */
    ResultDTO<Boolean> delteNoteGroup(Integer id);

    /**
     * 修改便签
     *
     * @param noteGroup
     * @return
     */
    ResultDTO<Boolean> UpdateNoteGroup(NoteGroup noteGroup);

    /**
     * 根据便签分组查询便签
     * @param noteGruopId
     * @return
     */
    ResultDTO<List<Note>> queryNoteByNoteGroup(Integer noteGruopId,Integer userId);

    /**
     * 新增便签
     * @param note
     * @return
     */
    ResultDTO<Boolean> addNote(Note note);

    /**
     * 修改便签
     * @param note
     * @return
     */
    ResultDTO<Boolean> updateNote(Note note);

    /**
     * 删除便签
     * @param id
     * @return
     */
    ResultDTO<Boolean> deleteNote(Integer id);

    ResultDTO<Boolean> updateNoteDone(List<Note> noteList);
}
