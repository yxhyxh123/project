package com.demo.parent.commondubboservice.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * projectName demo
 * className PageInfo
 * 分页信息
 * @author yzh
 * @date 2020/3/25 10:54 上午
 */
@Data
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = -8971103865880701480L;
    /**
     * 每页的数量
     */
    private int pageNum = 1;
    /**
     * 当前页数量
     */
    private int pageSize = 10;
    /**
     * 页面尺寸
     */
    private int size;

    /*由于startRow和endRow不常用，这里说个具体的用法
    可以在页面中"显示startRow到endRow 共size条数据"*/

    /**
     *当前页面第一个元素在数据库中的行号
     */
    private int startRow;
    /**当前页面最后一个元素在数据库中的行号*/
    private int endRow;
    /**总记录数*/
    private long total;
    /**总页数*/
    private int pages;
    /**结果集*/
    private List<T> data;

    /**第一页*/
    private int firstPage;
    /**前一页*/
    private int prePage;

    /**是否为第一页*/
    private boolean isFirstPage = false;
    /**是否为最后一页*/
    private boolean isLastPage = false;
    /**是否有前一页*/
    private boolean hasPreviousPage = false;
    /**是否有下一页*/
    private boolean hasNextPage = false;
    /**导航页码数*/
    private int navigatePages;
    /**所有导航页号*/
    private int[] navigatePageNums;
}
