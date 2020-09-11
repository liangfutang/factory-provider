package com.zjut.factory.provider.service.impl;

import com.zjut.factory.provider.dataobject.SysRoleDo;
import com.zjut.factory.provider.mapper.RoleMapper;
import com.zjut.factory.provider.service.RoleBaseService;
import com.zjut.spring.boot.jdbc.extend.service.BaseServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Log
public class RoleBaseServiceImpl extends BaseServiceImpl<RoleMapper, SysRoleDo> implements RoleBaseService {

    @Autowired
    @Lazy
    private RoleBaseService roleBaseService;

    @Autowired
    @Lazy
    ApplicationContext applicationContext;

    @Override
    public void getRole() {
        List<SysRoleDo> roles = baseMapper.selectByMap(new HashMap<String, Object>(1) {{
            put("role_id", 1);
        }});
        System.out.println("查询结果:" + roles);
    }
}
