package com.demo.parent.commondubboservice.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName demo
 * className BeanUtils
 *
 * @author yzh
 * @date 2020/3/25 1:28 下午
 */
public class BeanUtil {

    public static <T, F> List<F> copyListProperties(Class<F> target, List<T> sourceList) {
        if (!CollectionUtils.isEmpty(sourceList)) {
            List<F> targetLists = new ArrayList<>();
            for (T t : sourceList) {
                try {
                    F f = target.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(t, f);
                    targetLists.add(f);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return targetLists;
        } else {
            return null;
        }
    }}