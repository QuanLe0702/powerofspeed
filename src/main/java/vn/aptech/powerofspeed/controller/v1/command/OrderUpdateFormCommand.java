package vn.aptech.powerofspeed.controller.v1.command;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.aptech.powerofspeed.model.order.*;

@Data
@Accessors(chain = true)
public class OrderUpdateFormCommand {
    private Long orderId;
    private StatusType newStatus;
    // other fields needed for updating an order
}
