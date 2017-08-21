package org.fly.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Set;

/**
 * Created by admin on 2017/8/21.
 */
public abstract class AbstractBeanDefinitionReader implements EnvironmentCapable, BeanDefinitionReader {

    protected final Log logger = LogFactory.getLog(this.getClass());
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;
    private ClassLoader beanClassLoader;
    private Environment environment;
    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this.registry = registry;
        if(this.registry instanceof ResourceLoader) {
            this.resourceLoader = (ResourceLoader)this.registry;
        } else {
            this.resourceLoader = new PathMatchingResourcePatternResolver();
        }

        if(this.registry instanceof EnvironmentCapable) {
            this.environment = ((EnvironmentCapable)this.registry).getEnvironment();
        } else {
            this.environment = new StandardEnvironment();
        }

    }

    public final BeanDefinitionRegistry getBeanFactory() {
        return this.registry;
    }

    public final BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
        this.beanNameGenerator = (BeanNameGenerator)(beanNameGenerator != null?beanNameGenerator:new DefaultBeanNameGenerator());
    }

    public BeanNameGenerator getBeanNameGenerator() {
        return this.beanNameGenerator;
    }

    public int loadBeanDefinitions(Resource... resources) throws BeanDefinitionStoreException {
        Assert.notNull(resources, "Resource array must not be null");
        int counter = 0;
        Resource[] var3 = resources;
        int var4 = resources.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Resource resource = var3[var5];
            counter += this.loadBeanDefinitions((Resource)resource);
        }

        return counter;
    }

    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
        return this.loadBeanDefinitions(location, (Set)null);
    }

    public int loadBeanDefinitions(String location, Set<Resource> actualResources) throws BeanDefinitionStoreException {
        ResourceLoader resourceLoader = this.getResourceLoader();
        if(resourceLoader == null) {
            throw new BeanDefinitionStoreException("Cannot import bean definitions from location [" + location + "]: no ResourceLoader available");
        } else {
            int loadCount;
            if(!(resourceLoader instanceof ResourcePatternResolver)) {
                Resource resource = resourceLoader.getResource(location);
                loadCount = this.loadBeanDefinitions((Resource)resource);
                if(actualResources != null) {
                    actualResources.add(resource);
                }

                if(this.logger.isDebugEnabled()) {
                    this.logger.debug("Loaded " + loadCount + " bean definitions from location [" + location + "]");
                }

                return loadCount;
            } else {
                try {
                    Resource[] resources = ((ResourcePatternResolver)resourceLoader).getResources(location);
                    loadCount = this.loadBeanDefinitions(resources);
                    if(actualResources != null) {
                        Resource[] var6 = resources;
                        int var7 = resources.length;

                        for(int var8 = 0; var8 < var7; ++var8) {
                            Resource resource = var6[var8];
                            actualResources.add(resource);
                        }
                    }

                    if(this.logger.isDebugEnabled()) {
                        this.logger.debug("Loaded " + loadCount + " bean definitions from location pattern [" + location + "]");
                    }

                    return loadCount;
                } catch (IOException var10) {
                    throw new BeanDefinitionStoreException("Could not resolve bean definition resource pattern [" + location + "]", var10);
                }
            }
        }
    }

    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
        Assert.notNull(locations, "Location array must not be null");
        int counter = 0;
        String[] var3 = locations;
        int var4 = locations.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String location = var3[var5];
            counter += this.loadBeanDefinitions(location);
        }

        return counter;
    }
}
