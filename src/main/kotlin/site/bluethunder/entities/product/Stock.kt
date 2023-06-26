package site.bluethunder.entities.product

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.shop.ShopTable
import org.jetbrains.exposed.dao.id.EntityID

object StockTable : BaseIntIdTable("stock") {
    val productId = text("product_id").references(ProductTable.id)
    val shopId = text("shop_id").references(ShopTable.id)
    val quantity = integer("quantity")
}

class StockEntity(id: EntityID<String>) : BaseIntEntity(id, StockTable) {
    companion object : BaseIntEntityClass<StockEntity>(StockTable)

    var productId by StockTable.productId
    var shopId by StockTable.shopId
    var quantity by StockTable.quantity
}
