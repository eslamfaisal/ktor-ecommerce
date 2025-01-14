package site.bluethunder.models.user.body

import site.bluethunder.plugins.RoleManagement
import org.valiktor.functions.*
import org.valiktor.validate

data class LoginBody(val email: String, val password: String, val userType: String) {
    fun validation() {
        validate(this) {
            validate(LoginBody::email).isNotNull().isEmail()
            validate(LoginBody::password).isNotNull().hasSize(4, 10)
            validate(LoginBody::userType).isNotNull().isIn(RoleManagement.ADMIN.role, RoleManagement.USER.role, RoleManagement.SELLER.role)
        }
    }
}
