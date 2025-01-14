package site.bluethunder.models.product.request

import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class AddCategoryBody(val userType: String, val categoryName: String) {
    fun validation() {
        validate(this) {
            validate(AddCategoryBody::userType).isNotNull()
            validate(AddCategoryBody::categoryName).isNotNull()
        }
    }
}