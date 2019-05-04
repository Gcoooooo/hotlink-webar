package com.hotlink.business.impl;

import com.hotlink.business.util.RedisUtil;
import com.hotlink.business.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("redisImpl")
@EnableCaching
public class RedisImpl {

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private RedisUtil redisUtil;

    public void testValueOption() {
        User userVo = new User();
        userVo.setAddress("上海");
        userVo.setName("jantent");
        userVo.setTel("12321312");
        valueOperations.set("User_info_" + 2,userVo);

        User user2 = (User) valueOperations.get("User_info_" + 2);
        System.out.println(user2.getAddress());
    }

    public void testClean() {
        redisUtil.del("User_info_" + 2);
    }
}
