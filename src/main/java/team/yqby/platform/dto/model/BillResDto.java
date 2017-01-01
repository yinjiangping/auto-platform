package team.yqby.platform.dto.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * <p>
 * 账单信息DTO
 * </p>
 * User：jumping Date： 2016/10/20 0020 Version：1.0
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillResDto {

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 账单号
     */
    private String barcode;

    /**
     * 账期
     */
    private String mqbilldate;

    /**
     * 状态
     */
    private String status;
}
