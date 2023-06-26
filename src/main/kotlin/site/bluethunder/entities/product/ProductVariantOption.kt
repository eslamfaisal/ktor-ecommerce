package site.bluethunder.entities.product

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.product.defaultproductcategory.ProductCategoryTable
import org.jetbrains.exposed.dao.id.EntityID

object ProductVariantOptionTable : BaseIntIdTable("product_variant_option") {
    val productVariantId = ProductCategoryTable.reference("product_variant_id", ProductVariantTable.id)
    val name = text("name")
}

class ProductVariantOptionEntity(id: EntityID<String>) : BaseIntEntity(id, ProductVariantOptionTable) {
    companion object : BaseIntEntityClass<ProductVariantOptionEntity>(ProductVariantOptionTable)

    var productVariantId by ProductVariantOptionTable.productVariantId
    var name by ProductVariantTable.name
}