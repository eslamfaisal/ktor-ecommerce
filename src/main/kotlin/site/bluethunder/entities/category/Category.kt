package site.bluethunder.entities.category

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import org.jetbrains.exposed.dao.id.EntityID

object CategoryTable : BaseIntIdTable("category") {
    val categoryName = text("category_name")
    val image = text("image").nullable()
}

class CategoryEntity(id: EntityID<String>) : BaseIntEntity(id, CategoryTable) {
    companion object : BaseIntEntityClass<CategoryEntity>(CategoryTable)

    var categoryName by CategoryTable.categoryName
    private val subCategories by SubCategoryEntity referrersOn SubCategoryTable.categoryId
    var image by CategoryTable.image
    fun categoryResponse() =
        CategoryResponse(id.value, categoryName, subCategories.map { it.subCategoryResponse() }, image)
}

data class CategoryResponse(
    val id: String,
    val categoryName: String,
    val subCategories: List<SubCategoryResponse>,
    val image: String?
)