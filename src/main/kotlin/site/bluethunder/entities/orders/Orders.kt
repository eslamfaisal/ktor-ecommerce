package site.bluethunder.entities.orders

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.user.UserTable
import org.jetbrains.exposed.dao.id.EntityID

object OrdersTable : BaseIntIdTable("orders") {
    val userId = reference("user_id", UserTable.id)
    val paymentId = varchar("payment_id", 50).nullable()
    val paymentType = varchar("payment_type", 50).nullable()
    val quantity = integer("quantity")
    val subTotal = float("sub_total")
    val total = float("total")
    val shippingCharge = float("shopping_charge")
    val vat = float("vat").nullable()
    val cancelOrder = bool("cancel_order").default(false)
    val coupon = varchar("coupon", 50).nullable()
    val status = varchar("status", 30).default("pending")
    val statusCode = integer("status_code").default(0)
}

class OrderEntity(id: EntityID<String>) : BaseIntEntity(id, OrdersTable) {
    companion object : BaseIntEntityClass<OrderEntity>(OrdersTable)

    var userId by OrdersTable.userId
    var paymentId by OrdersTable.paymentId
    var paymentType by OrdersTable.paymentType
    var quantity by OrdersTable.quantity
    var subTotal by OrdersTable.subTotal
    var total by OrdersTable.total
    var shippingCharge by OrdersTable.shippingCharge
    var vat by OrdersTable.vat
    var cancelOrder by OrdersTable.cancelOrder
    var coupon by OrdersTable.coupon
    var status by OrdersTable.status
    var statusCode by OrdersTable.statusCode
}