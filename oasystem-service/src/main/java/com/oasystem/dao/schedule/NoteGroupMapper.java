package com.oasystem.dao.schedule;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.oasystem.model.NoteGroup;

public interface NoteGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoteGroup record);

    int insertSelective(NoteGroup record);

    NoteGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteGroup record);

    int updateByPrimaryKey(NoteGroup record);

    List<NoteGroup> selectAllByUserId(@Param("userId")Integer userId);

    int deleteByName(@Param("name")String name);


}