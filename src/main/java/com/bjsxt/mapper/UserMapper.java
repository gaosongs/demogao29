package com.bjsxt.mapper;

import com.bjsxt.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //根据用户身份获取用户信息
    @Select("select * from t_user where uname=#{uname}")
    User selUserInfoMapper(String uname);
}
