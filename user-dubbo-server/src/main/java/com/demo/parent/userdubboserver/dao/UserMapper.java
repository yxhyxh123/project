package com.demo.parent.userdubboserver.dao;

import com.demo.parent.userdubboserver.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * projectName demo
 * interfaceName UserMapper
 *
 * @author yzh
 * @date 2020/3/18 1:06 下午
 */
@Repository
public interface UserMapper extends Mapper<UserEntity> {

    /**
     * @description 根据实体属性进行查询，查询条件用等号。
     * @return List<UserEntity>
     * @author  yzh
     * @date    2020/3/18
     */
    @SelectProvider(type = UserSqlProvider.class,method = "queryListByUserId")
    @Results(id = "userMap",value = {
            @Result(column = "ID",property = "userId"),
            @Result(column = "ACCOUNT",property = "userAccount")
    })
    List<UserEntity>  queryListByUserId(@Param("id")String userId);
}
