package com.oasystem.schedu;

import com.alibaba.fastjson.JSONObject;
import com.oasystem.model.AttendsDate;
import com.oasystem.service.attend.AttendsService;
import com.oasystem.service.attend.impl.AttendsServiceImpl;
import com.oasystem.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;



@Component
public class ScheduTask {

    @Resource
    private AttendsService attendsService;



    @Scheduled(cron = "0 0 0 ? * *")
    private void attendInfo(){
        System.out.println("1");
        attendsService.checkSignSingOut();
    }

    @Scheduled(cron = "0 0 0 1 1 ?")
    private void process() {
        Integer sysYear = getSysYear();
        for (int i = 1; i <= 12; i++) {
            List<String> monthFullDay = DateUtils.getMonthFullDay(sysYear, i);
            monthFullDay.forEach(date -> {
                String trans = DateUtils.getHoliday(date);
                JSONObject jsonObject = JSONObject.parseObject(trans);
                Integer code = (Integer) jsonObject.get("code");
                if (code == 0) {
                    JSONObject type = (JSONObject) jsonObject.get("type");
                    Integer typeNum = (Integer) type.get("type");
                    boolean isWork = false;
                    if (typeNum == 0 || typeNum == 3) {
                        isWork = true;
                    }
                    AttendsDate attendsDate = new AttendsDate();
                    attendsDate.setAttendYear(String.valueOf(sysYear));
                    attendsDate.setAttendDate(date);
                    attendsDate.setIsWork(isWork ? 1 : 0);
                    attendsService.insertWoryDay(attendsDate);
                }
            });

        }
    }

    public static Integer getSysYear() {
        Calendar date = Calendar.getInstance();
        return Integer.valueOf(date.get(Calendar.YEAR));
    }


}
