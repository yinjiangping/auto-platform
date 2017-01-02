package team.yqby.platform.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.manager.SpringContextHelper;
import team.yqby.platform.service.PrepaidCardPayService;

import java.io.IOException;

@Slf4j
public class MsgWebSocketHandler extends TextWebSocketHandler {
    private final String tableFilePath = PublicConfig.BILL_FILE_PATH;

    private static long count = 0;

    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        String fileName = message.getPayload();
        if (StringUtils.isEmpty(fileName)) return;
        log.info("SOCKET用户请求文件名:{},当前用户总数:{}", fileName, ++count);
        try {
            PrepaidCardPayService prepaidCardPayService = (PrepaidCardPayService) SpringContextHelper.getBean("prepaidCardPayService");
            prepaidCardPayService.batchDo(tableFilePath + fileName, session);

        } catch (IOException e) {
            log.error("handleTextMessage IOException,error,", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        ++count;
        super.afterConnectionClosed(session, status);
    }
}
