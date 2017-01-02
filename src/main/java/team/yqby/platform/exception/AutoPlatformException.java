package team.yqby.platform.exception;


import lombok.Getter;
import team.yqby.platform.common.emodel.ServiceErrorCode;

public class AutoPlatformException extends RuntimeException {

	private static final long serialVersionUID = 1165876351848409310L;

	@Getter
    private String code;

    @Getter
    private String resMessage;

    public AutoPlatformException(String code) {
        this.code = code;
    }

    public AutoPlatformException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public AutoPlatformException(ServiceErrorCode serviceErrorCode) {
        super(serviceErrorCode.getResDesc());
        this.code = serviceErrorCode.getResCode();
    }

    public AutoPlatformException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AutoPlatformException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }


}
