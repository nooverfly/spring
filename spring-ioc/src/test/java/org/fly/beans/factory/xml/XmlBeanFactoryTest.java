package org.fly.beans.factory.xml;

import org.fly.core.io.ClassPathResource;
import org.junit.Test;

/**
 * Created by overfly on 2017/8/21.
 */
public class XmlBeanFactoryTest {

    @Test
    public void testXmlBeanFactory(){
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        MyXmlBeanDefinitionReader xmlBeanDefinitionReader = new MyXmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("ok");
    }
}
