package site.bluethunder.routing

import com.papsign.ktor.openapigen.route.path.auth.post
import com.papsign.ktor.openapigen.route.path.auth.principal
import com.papsign.ktor.openapigen.route.path.auth.put
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import site.bluethunder.controller.OrderController
import site.bluethunder.models.order.AddOrder
import site.bluethunder.models.order.UpdateOrder
import site.bluethunder.models.orderitem.OrderItem
import site.bluethunder.models.user.body.JwtTokenBody
import site.bluethunder.plugins.RoleManagement
import site.bluethunder.utils.ApiResponse
import site.bluethunder.utils.Response
import site.bluethunder.utils.authenticateWithJwt
import io.ktor.http.*

fun NormalOpenAPIRoute.orderRouting(orderController: OrderController) {
    route("order") {
        authenticateWithJwt(RoleManagement.USER.role) {
            post<Unit, Response, AddOrder, JwtTokenBody>(
                exampleRequest = AddOrder(
                    1,
                    100f,
                    100f,
                    2f,
                    orderStatus = "pending",
                    mutableListOf(OrderItem("orderId", "productId", 100f, 100f, 1)),
                )
            ) { _, orderBody ->
                orderBody.validation()
                respond(
                    ApiResponse.success(
                        orderController.createOrder(principal().userId, orderBody), HttpStatusCode.OK
                    )
                )
            }
            put<UpdateOrder, Response, Unit, JwtTokenBody> { updateParams, _ ->
                updateParams.validation()
                respond(
                    ApiResponse.success(
                        orderController.updateOrder(principal().userId, updateParams), HttpStatusCode.OK
                    )
                )
            }
        }
    }
}