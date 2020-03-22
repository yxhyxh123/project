package com.demo.parent.userdubboserver.dao;

import org.apache.ibatis.jdbc.SQL;

/**
 * projectName demo
 * className UserSqlProvider
 * description TODO
 *
 * @author yzh
 * @date 2020/3/18 2:40 下午
 */
public class UserSqlProvider {

    public static String queryListByUserId(final String id){
        return new SQL(){{
            SELECT("ID,ACCOUNT");
            FROM("SYSTEM.MEMBER");
            WHERE("MEMBER.ID = #{id}");
        }}.toString();
    }
}
