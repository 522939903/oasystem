package com.oasystem.dao.schedule;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.oasystem.model.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    int updateIsDoneById(@Param("updatedIsDone")Integer updatedIsDone,@Param("id")Integer id);


    List<Note> selectAllByNoteGroup(@Param("noteGroup")Integer noteGroup,@Param("userId")Integer userId);

}