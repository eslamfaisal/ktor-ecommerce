package site.bluethunder.routing

import com.papsign.ktor.openapigen.route.path.auth.delete
import site.bluethunder.controller.CartController
import site.bluethunder.models.PagingData
import site.bluethunder.models.cart.AddCart
import site.bluethunder.models.user.body.JwtTokenBody
import site.bluethunder.plugins.RoleManagement
import site.bluethunder.utils.ApiResponse
import site.bluethunder.utils.Response
import site.bluethunder.utils.authenticateWithJwt
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.path.auth.post
import com.papsign.ktor.openapigen.route.path.auth.principal
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import site.bluethunder.models.cart.DeleteProduct
import io.ktor.http.*

fun NormalOpenAPIRoute.cartRouting(cartController: CartController) {
    route("cart") {
        authenticateWithJwt(RoleManagement.USER.role) {
            route("/add").post<Unit, Response, AddCart, JwtTokenBody>(
                exampleRequest = AddCart(
                    "",
                    100f,
                    100f,
                    2,
                )
            ) { _, cartBody ->
                cartBody.validation()
                respond(ApiResponse.success(cartController.addToCart(principal().userId, cartBody), HttpStatusCode.OK))
            }
            route("/remove").delete<DeleteProduct, Response, JwtTokenBody> { params ->
                params.validation()
                respond(
                    ApiResponse.success(
                        cartController.removeCartItem(principal().userId, params), HttpStatusCode.OK
                    )
                )
            }
            get<PagingData, Response, JwtTokenBody> { pagingData ->
                pagingData.validation()
                respond(
                    ApiResponse.success(
                        cartController.getCartItems(principal().userId, pagingData),
                        HttpStatusCode.OK
                    )
                )
            }
            delete<DeleteProduct, Response, JwtTokenBody> { params ->
                params.validation()
                respond(
                    ApiResponse.success(
                        cartController.deleteCart(principal().userId), HttpStatusCode.OK
                    )
                )
            }
        }
    }
}