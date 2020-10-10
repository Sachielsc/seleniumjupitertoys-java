package com.planittesting.automation.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.reflections.Reflections;

public class DriverFactory {

    private boolean isHeadless;
    private URL gridUrl;

    public DriverFactory setHeadless(boolean isHeadless) {
        this.isHeadless = isHeadless;
        return this;
    }

    public DriverFactory setGridUrl(String gridUrl) {
        if (!StringUtils.isBlank(gridUrl)) {
            try {
                this.gridUrl = new URL(gridUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public WebDriver getInstance(String browser) throws ReflectiveOperationException {
        List<Class<? extends Driver>> drivers = new ArrayList<>(
                new Reflections(Driver.class.getPackageName()).getSubTypesOf(Driver.class));
        return drivers.stream().filter(d -> d.getSimpleName().toLowerCase().contains(browser.toLowerCase()))
                .findFirst().orElseThrow(() -> new RuntimeException(browser + " is not a supported browser"))
                .getConstructor().newInstance().executeHeadless(isHeadless).withGridUrl(gridUrl).getDriver();
    }

}