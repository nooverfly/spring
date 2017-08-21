package org.fly.beans.factory;

import org.fly.beans.BeansException;

/**
 * Created by admin on 2017/8/21.
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;


}
