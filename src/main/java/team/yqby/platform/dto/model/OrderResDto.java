package team.yqby.platform.dto.model;

import lombok.*;

/**
 * <p>
 *      创建订单返回DTO
 * </p>
 * User：jumping Date： 2016/10/20 0020 ProjectName:settlement Version：1.0
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {

    private String msgSeq;

    private String orderIDType;

    private String orderID;

    private String orderType;

    private String orderFrom;

    private String amount_1;

    private String createDateTime;

    private String status;

    private String merchantID;

    private String print;

    private String amount_2;

    private String billdate;

    private String deviceNo;

}
