package com.thegentle.oldhealth.Service;

import java.util.Map;

public interface AdminService {
    //统计用户数 员工数 部门数量 总服务数
    public Map<String,Integer> countAll();
}
