package site.bluethunder.entities.product

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.user.UserTable
import org.jetbrains.exposed.dao.id.EntityID

object WishListTable : BaseIntIdTable("wishlist") {
    val userId = text("user_id").references(UserTable.id)
    val productId = text("product_id").references(ProductTable.id)
}

class WishListEntity(id: EntityID<String>) : BaseIntEntity(id, WishListTable) {
    companion object : BaseIntEntityClass<WishListEntity>(WishListTable)

    var productId by StockTable.productId
    var shopId by StockTable.shopId
    var quantity by StockTable.quantity
}