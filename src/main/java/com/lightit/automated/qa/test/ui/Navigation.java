package com.lightit.automated.qa.test.ui;

public class Navigation {

    public <T> T navigatesTo(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
