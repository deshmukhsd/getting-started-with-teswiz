package com.znsio.e2e.screen.theapp;

import com.znsio.e2e.entities.Platform;
import com.znsio.e2e.runner.Runner;
import com.znsio.e2e.screen.android.theapp.ClipboardDemoScreenAndroid;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

import static com.znsio.e2e.runner.Runner.fetchDriver;
import static com.znsio.e2e.runner.Runner.fetchEyes;

public abstract class ClipboardDemoScreen {
    private static final String SCREEN_NAME = ClipboardDemoScreen.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);

    public static ClipboardDemoScreen get() {
        Driver driver = fetchDriver(Thread.currentThread()
                                          .getId());
        Platform platform = Runner.fetchPlatform(Thread.currentThread()
                                                       .getId());
        LOGGER.info(SCREEN_NAME + ": Driver type: " + driver.getType() + ": Platform: " + platform);
        Visual visually = fetchEyes(Thread.currentThread()
                                          .getId());

        switch(platform) {
            case android:
                return new ClipboardDemoScreenAndroid(driver, visually);
        }
        throw new NotImplementedException(SCREEN_NAME + " is not implemented in " + Runner.platform);
    }

    public abstract ClipboardDemoScreen setInClipboard(String content);

    public abstract boolean doesAddedContentExistInClipboard();
}
