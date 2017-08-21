package org.fly.core.io;

import org.junit.Test;

/**
 * Created by overfly on 2017/8/21.
 */
public class ClassPathResourceTest {

    @Test
    public void testCPR(){
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }

}
