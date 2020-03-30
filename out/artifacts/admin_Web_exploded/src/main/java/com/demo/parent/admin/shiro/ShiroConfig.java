package com.demo.parent.admin.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.demo.parent.admin.bean.PermsMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
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
        // 自定义缓存实现 使用redis
       // securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
       // securityManager.setSessionManager(SessionManager());
        // 使用记住我
        //securityManager.setRememberMeManager(rememberMeManager());
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
        filterChainMap.put("/demo/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/res/**", "anon");
        filterChainMap.put("/scripts/**", "anon");
        filterChainMap.put("/style/**", "anon");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/loginH");

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

    /**
     * redisManager
     *
     * @return
     */

    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("39.105.102.33");
        redisManager.setPort(6379);
        // 配置过期时间
        redisManager.setExpire(1800);
        return redisManager;
    }

    /**
     * cacheManager
     *
     * @return
     */

    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * redisSessionDAO
     */

    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * sessionManager
     */

    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * cookie对象;
     * @return
     */

    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间30天,单位秒;
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // cookieRememberMeManager.setCipherKey用来设置加密的Key,参数类型byte[],字节数组长度要求16
        // cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCipherKey("ZHANGXIAOHEI_CAT".getBytes());
        return cookieRememberMeManager;
    }


}
