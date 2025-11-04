package com.thegentle.oldhealth.Service.Impl;

import com.thegentle.oldhealth.Mapper.AdminMapper;
import com.thegentle.oldhealth.Service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class adminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Map<String, Integer> countAll() {

        return adminMapper.countAllData();
    }
}
