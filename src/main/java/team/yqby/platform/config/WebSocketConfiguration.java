package team.yqby.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import team.yqby.platform.handle.MsgWebSocketHandler;

/**
 * <p>
 * </p>
 * User：jumping Date： 2016/12/30 0030 Version：1.0
 */
@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MsgWebSocketHandler(), "/webSocket").setAllowedOrigins("*");
    }
}
