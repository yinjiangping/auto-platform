package team.yqby.platform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.servlet.MultipartConfigElement;

/**
 * Application 应用启动入口
 *
 * @author jumping
 * @version 1.0.0 createTime: 16/10/16
 */
@Slf4j
@EnableWebSocket
@SpringBootApplication(exclude = {SpringDataWebAutoConfiguration.class, FallbackWebSecurityAutoConfiguration.class, SpringBootWebSecurityConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
