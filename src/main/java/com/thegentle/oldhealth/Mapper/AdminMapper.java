package com.thegentle.oldhealth.Mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

/**
 * 管理员相关数据统计 Mapper 接口
 * 新手提示：@Mapper 注解是MyBatis的核心注解，告诉Spring这是一个Mapper接口，会自动生成实现类
 */
@Mapper
public interface AdminMapper {

    /**
     * 统计各表核心数据：员工数、部门数、用户数、AI咨询条数
     * @return Map集合，key对应表名相关标识，value对应统计数量（Integer类型）
     * 新手提示：返回值用Map<String, Integer>，能灵活存放多个不同维度的统计结果，不用单独创建实体类
     */
    Map<String, Integer> countAllData(); // 方法名修改为countAllData，更贴合功能（原countUser只体现用户数，不全面）
}