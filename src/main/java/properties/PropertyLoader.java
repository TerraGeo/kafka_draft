package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private String propertyFilePath;
    private final Properties props;

    public PropertyLoader(final String pathToFile) {
        this.propertyFilePath = pathToFile;
        props = loadProperties();
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            throw new RuntimeException("The provided path to file is invalid.");
        }
        return properties;
    }

    public Properties getProps() {
        return props;
    }
}
