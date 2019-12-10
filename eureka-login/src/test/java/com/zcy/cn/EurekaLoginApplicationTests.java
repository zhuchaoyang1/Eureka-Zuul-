package com.zcy.cn;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EurekaLoginApplicationTests {

    @Test
    public void testJodaTime() {
        log.info(DateTime.now().plusMinutes(30).toString("yyyyMMddHHmmssSSS"));
    }

}
