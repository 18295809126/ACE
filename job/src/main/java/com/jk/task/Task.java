package com.jk.task;

import com.jk.model.User;
import com.jk.service.EmailThread;
import com.jk.service.UserService;
import com.jk.service.UserThead;
import com.jk.utils.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Task {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 19 20 * * ?")
    public void birthday(){
        //查询出需要发送短信的人  查询出今天过生日的总人数
       /* Integer page=0;
        Integer rows=1;*/
        //分页获取今天过生日人的信息(在短信平台创建一个生日祝福的模板，看是否能通过审核)
        List<User> userList = userService.getTodayBirthdayPeopleList();

        for (User user : userList) {
            //发送短信（线程池）
             ThreadPool.executor(new UserThead(userService,user));
            
            //发送邮件（线程池）
            ThreadPool.executor(new EmailThread(userService,user));

        }
    /*    ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setBirthday(new Date());
            user.setPhoneNumber("15001083641");
            user.setUserEmail("cuiliyingadmin@163.com");

            users.add(user);
        }*/
        System.out.println("定时任务启动了。。。。。");

    }

}
