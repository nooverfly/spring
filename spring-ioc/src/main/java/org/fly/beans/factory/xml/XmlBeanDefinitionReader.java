package org.fly.beans.factory.xml;

import org.fly.beans.factory.support.AbstractBeanDefinitionReader;
import org.fly.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;

/**
 * Created by admin on 2017/8/21.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        super(registry);

    }

    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return 0;
    }
}
