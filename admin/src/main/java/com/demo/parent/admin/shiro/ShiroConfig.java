package com.demo.parent.admin.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.demo.parent.admin.bean.PermsMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * projectName demo
 * className ShiroConfig*
 * @author yzh
 * @date 2020/3/18 4:16 下午
 */
@Configuration
public class ShiroConfig {

    @Autowired
    PermsMap permsMap;

    @Bean
    public UserRealm UserRealm(){
        return new UserRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(new UserRealm());
        return securityManager;
    }

    /**
     * @description 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     * @return
     * @author  yzh
     * @date    2020/3/18
     */
    @Bean
    public ShiroFilterFactoryBean webFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);
        //配置退出过滤器logout，由shiro实现
        filterChainMap.put("/logout","logout");
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
        filterChainMap.put("/login","anon");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //动态权限注入
        List<Map<String,String>> perms = permsMap.getPerms();
        perms.forEach(perm->filterChainMap.put(perm.get("url"),perm.get("permission")));

        filterChainMap.put("/**", "authc");

        //设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/login");
        System.out.println(filterChainMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 处理未授权的异常，返回自定义的错误页面（403）
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /*未授权处理页*/
        properties.setProperty("UnauthorizedException", "403.html");
        resolver.setExceptionMappings(properties);
        return resolver;
    }


    /**
     * 用于thymeleaf模板使用shiro标签
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
