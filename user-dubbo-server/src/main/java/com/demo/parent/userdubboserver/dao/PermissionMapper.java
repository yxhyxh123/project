package com.demo.parent.userdubboserver.dao;

import com.demo.parent.userdubboserver.entity.PermissionEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * projectName demo
 * interfaceName PermissionMapper
 *
 * @author yzh
 * @date 2020/3/19 10:05 上午
 */
@Repository
public interface PermissionMapper extends Mapper<PermissionEntity> {
}
