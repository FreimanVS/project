package com.freimanvs.company.rest;

import com.freimanvs.company.rest.beans.interfaces.CalculateDifferentialBean;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Differential Payment Service Swagger-generated API", produces = MediaType.TEXT_HTML)
@Path("/bank/v1/calculations")
public class DifferentialPaymentService implements Calculator {

    @EJB
    CalculateDifferentialBean calculateDifferentialBean;

    @ApiOperation(value = "calcuate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
	@Path("/1")
    @Override
    public Response calculate(@ApiParam(value = "количество периодов оплаты", required = true)
                                  @FormParam("t") int t,
                              @ApiParam(value = "сумма кредита", required = true)
                                  @FormParam("kr") double kr,
                              @ApiParam(value = "процентная ставка, начисляемая на задолженность за период", required = true)
                                  @FormParam("st") double st) {


        return Response.status(200).entity(calculateDifferentialBean.calculate(t, kr, st)).build();
    }
}
