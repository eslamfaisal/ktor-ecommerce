package site.bluethunder.entities.orders

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.product.ProductTable
import org.jetbrains.exposed.dao.id.EntityID

object OrderItemTable : BaseIntIdTable("order_items") {
    val orderId = reference("order_id", OrdersTable.id)
    val productId = reference("product_id", ProductTable.id)
    val totalPrice = float("total_price")
    val singlePrice = float("single_price")
    val quantity = integer("quantity")
}

class OrderItemEntity(id: EntityID<String>) : BaseIntEntity(id, OrderItemTable) {
    companion object : BaseIntEntityClass<OrderItemEntity>(OrderItemTable)

    var orderId by OrderItemTable.orderId
    var productId by OrderItemTable.productId
    var totalPrice by OrderItemTable.totalPrice
    var singlePrice by OrderItemTable.singlePrice
    var quantity by OrderItemTable.quantity
}