package org.openqa.selenium.remote.html5;

import org.openqa.selenium.remote.ExecuteMethod;

/**
 * Compatibility stub.
 * Appium 9.4.0 was compiled against an older Selenium that included this class.
 * It was removed from Selenium before 4.26, but AppiumDriver still holds a field
 * of this type. Providing this stub on the classpath satisfies the JVM class-loader
 * without requiring a Selenium downgrade. The methods are never invoked in our tests
 * because we do not use the HTML5 geolocation API.
 */
public class RemoteLocationContext {

    public RemoteLocationContext(ExecuteMethod executeMethod) {
        // stub — geolocation not used in these tests
    }
}
