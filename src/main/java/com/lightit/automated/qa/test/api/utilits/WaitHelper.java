package com.lightit.automated.qa.test.api.utilits;

import lombok.SneakyThrows;

public class WaitHelper {

    @SneakyThrows
    public static void sleep(long millis){
        Thread.sleep(millis);
    }
}
