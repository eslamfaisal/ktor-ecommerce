package site.bluethunder.controller

import site.bluethunder.entities.orders.OrderEntity
import site.bluethunder.entities.orders.OrderItemTable
import site.bluethunder.entities.orders.OrdersTable
import site.bluethunder.entities.user.UserTable
import site.bluethunder.models.order.AddOrder
import site.bluethunder.models.order.UpdateOrder
import site.bluethunder.utils.extension.orderStatusCode
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction

class OrderController {
    fun createOrder(userId: String, addOrder: AddOrder) = transaction {
        OrderEntity.new {
            this.userId = EntityID(userId, UserTable)
            this.quantity = addOrder.quantity
            this.shippingCharge = addOrder.shippingCharge
            this.subTotal = addOrder.subTotal
            this.total = addOrder.total
        }
        OrderItemTable.batchInsert(addOrder.orderItems) {
            this[OrderItemTable.id] = it.orderId
            this[OrderItemTable.productId] = it.productId
            this[OrderItemTable.singlePrice] = it.singlePrice
            this[OrderItemTable.totalPrice] = it.totalPrice
            this[OrderItemTable.quantity] = it.quantity
        }
    }

    fun updateOrder(userId: String, updateOrder: UpdateOrder) = transaction {
        val orderExist =
            OrderEntity.find { OrdersTable.userId eq userId and (OrdersTable.id eq updateOrder.orderId) }.toList()
                .singleOrNull()
        orderExist?.apply {
            this.status = updateOrder.orderStatus
            this.statusCode = updateOrder.orderStatus.orderStatusCode()
        }
    }
}