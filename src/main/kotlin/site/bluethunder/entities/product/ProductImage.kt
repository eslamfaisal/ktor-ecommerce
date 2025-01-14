package site.bluethunder.entities.product

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import org.jetbrains.exposed.dao.id.EntityID

object ProductImage : BaseIntIdTable("product_image") {
    val productId = text("product_id").references(ProductTable.id).nullable()
    val imageUrl = text("image_url") // multiple image will be saved comma seperated string
}

class ProductImageEntity(id: EntityID<String>) : BaseIntEntity(id, ProductImage) {
    companion object : BaseIntEntityClass<ProductImageEntity>(ProductImage)

    var productId by ProductImage.productId
    var imageUrl by ProductImage.imageUrl
    fun response() = ImageUrl(id.value, imageUrl)
}

data class ImageUrl(
    val id: String,
    val imageUrl: String,
)