package dev.mvc;

import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.jul.LogManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class EmbeddedTomcatApplication {

    public static void main(String[] args) throws Exception {
        configureJulEncoding();

        // JUL → Log4j2 리다이렉트 초기화
        LogManager.getLogManager();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // 필수 호출
        tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        System.out.println("Tomcat started on port 8080...");
        tomcat.start();
        tomcat.getServer().await();
    }

    public static void configureJulEncoding() throws UnsupportedEncodingException {
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            handler.setEncoding("UTF-8");
        }
    }
}
