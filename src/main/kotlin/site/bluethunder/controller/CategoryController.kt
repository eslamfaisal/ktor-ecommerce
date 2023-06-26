package site.bluethunder.controller

import site.bluethunder.entities.category.CategoryEntity
import site.bluethunder.entities.category.CategoryTable
import site.bluethunder.models.PagingData
import site.bluethunder.models.category.AddCategory
import site.bluethunder.models.category.DeleteCategory
import site.bluethunder.models.category.UpdateCategory
import site.bluethunder.utils.CommonException
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryController {
    fun createCategory(addCategory: AddCategory) = transaction {
        val categoryExist =
            CategoryEntity.find { CategoryTable.categoryName eq addCategory.categoryName }.toList().singleOrNull()
        return@transaction if (categoryExist == null) {
            CategoryEntity.new {
                categoryName = addCategory.categoryName
            }.categoryResponse()
        } else {
            throw CommonException("${addCategory.categoryName} already exist")
        }
    }

    fun getCategory(paging: PagingData) = transaction {
        val categories = CategoryEntity.all().limit(paging.limit, paging.offset)
        return@transaction categories.map {
            it.categoryResponse()
        }
    }

    fun updateCategory(updateCategory: UpdateCategory) = transaction {
        val categoryExist =
            CategoryEntity.find { CategoryTable.id eq updateCategory.categoryId }.toList().singleOrNull()
        categoryExist?.let {
            it.categoryName = updateCategory.categoryName
            // return category response
            it.categoryResponse()
        } ?: run {
            throw CommonException("Category not  exist")
        }
    }

    fun deleteCategory(deleteCategory: DeleteCategory) = transaction {
        val categoryExist =
            CategoryEntity.find { CategoryTable.id eq deleteCategory.categoryId }.toList().singleOrNull()
        categoryExist?.delete()
    }
}