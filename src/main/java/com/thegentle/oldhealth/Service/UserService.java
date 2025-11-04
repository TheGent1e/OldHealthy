package com.thegentle.oldhealth.Service;

import com.thegentle.oldhealth.pojo.User.User;
import com.thegentle.oldhealth.pojo.User.UserBasicInfo;

public interface UserService {
    //登录
    UserBasicInfo login(User user);
    //注册
    User register(User user);

    User getUserBasicInfo(User user);
}
