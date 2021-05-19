package learn.inventory.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class PlatformFee {

    @Getter
    @Setter
    private int platformFeeId;

    @Getter
    @Setter
    private BigDecimal feeAmount;

    @Getter
    @Setter
    private String platform;

    @Getter
    @Setter
    private String feeType;
}
