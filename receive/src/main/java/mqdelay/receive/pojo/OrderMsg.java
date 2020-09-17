package mqdelay.receive.pojo;

import java.util.List;

//发送的取消订单信息
public class OrderMsg {
    //用户id
    private int userId;
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    //订单sn
    private String orderSn;
    public String getOrderSn() {
        return this.orderSn;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

}