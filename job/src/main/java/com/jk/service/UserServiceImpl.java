package com.jk.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jk.mapper.UserMapper;
import com.jk.model.User;
import com.jk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getTodayBirthdayPeopleList() {
       // page=(page-1)*rows;
        return userMapper.getTodayBirthdayPeopleList();
    }

    /**
     * 给用户发送短信
     * @param user
     */
    @Override
    public void sendMsg(User user) {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("accountSid", CommoBean.ACCOUNTSID);
        params.put("templateid",CommoBean.TEMPLATEID);
        int random = (int)((Math.random()* 1000000)+100000);
        params.put("param",random);
        params.put("to",user.getPhoneNumber());
        params.put("timestamp", TimeUtil.dateTostr(new Date(),"yyyyMMddHHmmss"));
        params.put("sig", Md5Util.getMd532(params.get("accountSid").toString()+CommoBean.tokey+params.get("timestamp").toString()));
        HttpClient.post(CommoBean.SMS_URL,params);
    }

    /**
     * 发送邮箱
     * @param user
     */
    @Override
    public void sendEmail(User user) {
        try {
            //EmailUtil.sendHtmlMail(user.getUserEmail(),"哈哈","666");
            ArrayList<File> files = new ArrayList<File>();

            for (int i = 0; i < 5; i++) {
                files.add(new File("C:\\Users\\xzkp\\Pictures\\Saved Pictures\\Sonder2.jpg"));
            }
            EmailUtil.sendHtmlAndFailMail(user.getUserEmail(),"又一年","生日快乐",files);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
