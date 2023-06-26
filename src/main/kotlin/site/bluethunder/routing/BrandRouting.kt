package site.bluethunder.routing

import site.bluethunder.controller.BrandController
import site.bluethunder.models.PagingData
import site.bluethunder.models.bands.AddBrand
import site.bluethunder.models.bands.DeleteBrand
import site.bluethunder.models.bands.UpdateBrand
import site.bluethunder.models.user.body.JwtTokenBody
import site.bluethunder.plugins.RoleManagement
import site.bluethunder.utils.ApiResponse
import site.bluethunder.utils.Response
import site.bluethunder.utils.authenticateWithJwt
import com.papsign.ktor.openapigen.route.path.auth.delete
import com.papsign.ktor.openapigen.route.path.auth.get
import com.papsign.ktor.openapigen.route.path.auth.post
import com.papsign.ktor.openapigen.route.path.auth.put
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route
import io.ktor.http.*

fun NormalOpenAPIRoute.brandRouting(brandController: BrandController) {
    route("brand") {
        authenticateWithJwt(RoleManagement.SELLER.role) {
            post<Unit, Response, AddBrand, JwtTokenBody>(
                exampleRequest = AddBrand(
                    brandName = "Easy"
                )
            ) { _, brandBody ->
                brandBody.validation()
                respond(
                    ApiResponse.success(
                        brandController.createBrand(brandBody), HttpStatusCode.OK
                    )
                )
            }
            get<PagingData, Response, JwtTokenBody> { pagingData ->
                pagingData.validation()
                respond(ApiResponse.success(brandController.getBrand(pagingData), HttpStatusCode.OK))
            }
            put<UpdateBrand, Response, Unit, JwtTokenBody> { updateParams, _ ->
                updateParams.validation()
                respond(ApiResponse.success(brandController.updateBrand(updateParams), HttpStatusCode.OK))
            }
            delete<DeleteBrand, Response, JwtTokenBody> { deleteParams ->
                deleteParams.validation()
                respond(ApiResponse.success(brandController.deleteBrand(deleteParams), HttpStatusCode.OK))
            }
        }
    }
}