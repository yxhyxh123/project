package com.demo.parent.commondubboservice.util;

import com.demo.parent.commondubboservice.bean.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * projectName demo
 * className PageUtil
 * description 分页数据封装
 *
 * @author yzh
 * @date 2020/3/25 3:22 下午
 */
public class PageUtil {

    public static<T> void buildListPage(Integer pageNum, Integer pageSize, List<T> lists, PageInfo<T> page,int total){
        int pages =1;
        page.setData(lists);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        if(!CollectionUtils.isEmpty(lists)){
            page.setSize(lists.size());
            page.setTotal(total);
        }else{
            page.setSize(0);
        }
        if(pageNum <=0 || total == 0){
            page.setPages(pages);
        }else if(total%pageNum == 0){
            page.setPages(total/pageNum);
        }else{
            page.setPages((total/pageNum)+1);
        }
    }
}
