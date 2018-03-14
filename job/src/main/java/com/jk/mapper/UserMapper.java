package com.jk.mapper;

import com.jk.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user t WHERE DATE_FORMAT(t.birthday,'%m-%d') = DATE_FORMAT(NOW(),'%m-%d')" )
    List<User> getTodayBirthdayPeopleList();

}
