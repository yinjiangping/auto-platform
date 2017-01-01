package team.yqby.platform.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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

        String filePath = message.getPayload();
        if (StringUtils.isEmpty(filePath)) return;
        log.info("用户请求数据:{},你是第{}位访客", filePath, ++count);
        try {
            PrepaidCardPayService prepaidCardPayService = (PrepaidCardPayService) SpringContextHelper.getBean("prepaidCardPayService");
            prepaidCardPayService.batchDo(tableFilePath + filePath, session);
            session.sendMessage(new TextMessage("success"));
        } catch (IOException e) {
            log.error("handleTextMessage IOException,error,", e);
        }
    }
}
