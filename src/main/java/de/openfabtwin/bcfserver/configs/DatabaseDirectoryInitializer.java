package de.openfabtwin.bcfserver.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DatabaseDirectoryInitializer implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        String baseDir = System.getProperty("user.dir");
        String dataFolder = baseDir + File.separator + "data";

        File folder = new File(dataFolder);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("[SQLite] Created data folder: " + dataFolder);
            } else {
                System.err.println("[SQLite] FAILED to create folder: " + dataFolder);
            }
        } else {
            System.out.println("[SQLite] Data folder exists: " + dataFolder);
        }
    }
}


