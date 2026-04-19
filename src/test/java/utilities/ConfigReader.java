package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads non-sensitive configuration from config.properties.
 * Sensitive values (credentials) must be supplied via environment variables
 * or JVM system properties (-Dkey=value); they are never read from the file.
 */
public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found on the test classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Returns a configuration value. Resolution order:
     * 1. Environment variable (key uppercased, dots replaced by underscores)
     * 2. JVM system property (-Dkey=value)
     * 3. config.properties default
     */
    public static String get(String key) {
        String envKey = key.toUpperCase().replace('.', '_');
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isEmpty()) {
            return sysProp;
        }
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Configuration key not found: " + key);
        }
        return value;
    }

    /**
     * Reads a sensitive credential exclusively from an environment variable or
     * JVM system property. Throws if neither is set, to prevent tests from
     * running with empty credentials.
     */
    public static String getCredential(String envVarName) {
        String envValue = System.getenv(envVarName);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        String sysProp = System.getProperty(envVarName);
        if (sysProp != null && !sysProp.isEmpty()) {
            return sysProp;
        }
        throw new RuntimeException(
            "Required credential not found. Set the environment variable: " + envVarName);
    }
}
