package team.yqby.platform.Servlet;

import com.google.common.base.Joiner;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import team.yqby.platform.common.constant.SystemConstant;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * </p>
 * User：jumping Date： 2017/3/16 0016 Version：1.0
 */
@Slf4j
@WebServlet(urlPatterns="/servlet/qrCode", description="二维码servlet")
public class QrCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String KEY = "keyCode";
    private static final String SIZE = "msize";
    private static final String IMAGETYPE = "JPEG";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String keyCode = request.getParameter(KEY);
        String params = Joiner.on("&").join(
                Joiner.on("=").join("appid", SystemConstant.APP_ID),
                Joiner.on("=").join("redirect_uri", SystemConstant.homeUrl),
                Joiner.on("=").join("response_type", "code"),
                Joiner.on("=").join("scope", "snsapi_base"),
                Joiner.on("=").join("state", keyCode)).concat("#wechat_redirect");
        String createQrCodeUrl = Joiner.on("?").join(SystemConstant.WeChatUrl, params);
        log.info("渠道ID：{}，创建二维码地址：{}", keyCode, createQrCodeUrl);

        if (keyCode != null && !"".equals(keyCode)) {
            ServletOutputStream stream = null;
            try {
                int size = 129;
                String mSize = request.getParameter(SIZE);
                if (mSize != null && !"".equals(mSize.trim())) {
                    try {
                        size = Integer.valueOf(mSize);
                    } catch (NumberFormatException e) {
                        e.getStackTrace();
                    }
                }
                stream = response.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(createQrCodeUrl, BarcodeFormat.QR_CODE, size, size);
                MatrixToImageWriter.writeToStream(m, IMAGETYPE, stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }
}