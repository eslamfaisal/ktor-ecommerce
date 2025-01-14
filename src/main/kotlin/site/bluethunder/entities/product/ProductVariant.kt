package site.bluethunder.entities.product

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import site.bluethunder.entities.product.defaultproductcategory.ProductCategoryTable
import org.jetbrains.exposed.dao.id.EntityID

object ProductVariantTable : BaseIntIdTable("product_variant") {
    val productId = ProductCategoryTable.reference("product_id", ProductTable.id)
    val name = text("name")
}

class ProductVariantEntity(id: EntityID<String>) : BaseIntEntity(id, ProductVariantTable) {
    companion object : BaseIntEntityClass<ProductVariantEntity>(ProductVariantTable)

    var productId by ProductVariantTable.productId
    var name by ProductVariantTable.name
    fun response() = ProductVariant(id.value, name)
}

data class ProductVariant(val id: String, val variantName: String)