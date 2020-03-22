package com.demo.parent.admin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * projectName demo
 * className BeanUtils
 *
 * @author yzh
 * @date 2020/3/20 4:06 下午
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        if(applicationContext==null){
            System.out.println("applicationContext是空的");
        }else{
            System.out.println("applicationContext不是空的");
        }
        return applicationContext.getBean(clazz);
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
