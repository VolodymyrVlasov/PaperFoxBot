package models.shop;

import models.utils.services.deliveries.DeliveryMethod;
import models.utils.services.mailServices.MailService;
import models.utils.services.mailServices.MailStates;

import java.util.GregorianCalendar;

public class Order {
    private String orderId;
    private ShoppingCart shoppingCart;
    private DeliveryMethod deliveryMethod;
    private OrderStates orderStatus;
    private GregorianCalendar orderDate;

    public Order(ShoppingCart shoppingCart) {
        deliveryMethod = DeliveryMethod.PICK_UP;
        initParams(shoppingCart);
    }

    public Order(ShoppingCart shoppingCart, DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        initParams(shoppingCart);
    }

    private void initParams(ShoppingCart shoppingCart) {
        orderStatus = OrderStates.NEW;
        orderDate = new GregorianCalendar();
        this.shoppingCart = shoppingCart;
        orderId = shoppingCart.getOrderId();
    }

    public MailStates handleOrder() {
        OrderList.getInstance().addOrder(this);
        if (new MailService().sendEmail(this) == MailStates.OK) {
            return MailStates.OK;
        }
        return MailStates.ERROR;
    }

    public String getOrderId() {
        return orderId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public OrderStates getOrderStatus() {
        return orderStatus;
    }

    public GregorianCalendar getOrderDate() {
        return orderDate;
    }


}
