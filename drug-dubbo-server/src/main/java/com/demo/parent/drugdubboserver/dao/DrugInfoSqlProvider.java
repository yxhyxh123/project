package com.demo.parent.drugdubboserver.dao;

import org.apache.ibatis.jdbc.SQL;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;

/**
 * projectName demo
 * className DrugInfoSqlProvider
 * description TODO
 *
 * @author yzh
 * @date 2020/3/26 9:46 下午
 */
public class DrugInfoSqlProvider {

    private static String DRUG_INFO_TABLE = "SYSTEM.DRUG_INFO";


    public static String queryAllCount(){
        return new SQL(){{
            SELECT("COUNT(ID)");
            FROM(DRUG_INFO_TABLE);
        }}.toString();
    }

    public static String queryStockList(){
        return new SQL(){{
            SELECT("ID,GENERIC_NAME,STOCK");
            FROM(DRUG_INFO_TABLE);
            WHERE("STATUS = 1");
        }}.toString();
    }
}
