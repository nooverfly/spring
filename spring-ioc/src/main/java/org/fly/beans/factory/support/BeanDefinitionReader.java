package org.fly.beans.factory.support;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by admin on 2017/8/21.
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    ClassLoader getBeanClassLoader();

    BeanNameGenerator getBeanNameGenerator();

    int loadBeanDefinitions(Resource var1) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(Resource... var1) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String var1) throws BeanDefinitionStoreException;

    int loadBeanDefinitions(String... var1) throws BeanDefinitionStoreException;
}
