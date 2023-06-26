package site.bluethunder.entities.user

import site.bluethunder.entities.base.BaseIntEntity
import site.bluethunder.entities.base.BaseIntEntityClass
import site.bluethunder.entities.base.BaseIntIdTable
import org.jetbrains.exposed.dao.id.EntityID

object UserTypeTable : BaseIntIdTable("user_type") {
    val userType = text("user_type")
}

class UserTypeEntity(id: EntityID<String>) : BaseIntEntity(id, UserTypeTable)  {
    companion object : BaseIntEntityClass< UserTypeEntity>(UserTypeTable)
    var userType by UserTypeTable.userType
}
