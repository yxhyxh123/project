package com.demo.parent.admin.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * projectName demo
 * className PermsMap
 * 将配置文件信息的内容转化为List<Map<String, String>>，
 * 注入到ShiroConfig中
 * @author yzh
 * @date 2020/3/18 4:21 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "permission-config")
public class PermsMap {

    private List<Map<String,String>> perms;

}
