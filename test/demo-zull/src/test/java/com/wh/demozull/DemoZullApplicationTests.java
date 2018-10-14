package com.wh.demozull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoZullApplicationTests {
    @Test
    public void contextLoads() {
        String url="/api-a/a";
        String[] strings=url.split("/");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(i+": "+strings[i]);
        }
    }

}
