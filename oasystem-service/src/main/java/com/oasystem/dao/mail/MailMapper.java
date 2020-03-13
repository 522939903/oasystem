package com.oasystem.dao.mail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.oasystem.model.Mail;

public interface MailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mail record);

    int insertSelective(Mail record);

    Mail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKey(Mail record);

    List<Mail> selectAllBySendUserId(@Param("sendUserId")Integer sendUserId);


    List<Mail> selectAllByAcceptUserId(@Param("acceptUserId")String acceptUserId);

    List<Mail> querySendMail(@Param("sendUserId")Integer sendUserId);


}