package com.freimanvs.company.rest;

import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Differential Payment Service Swagger-generated API", produces = MediaType.TEXT_HTML)
@Path("/bank/v1/calculations")
public class DifferentialPaymentService implements Calculator {

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

        st = st / 100 / 12;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            double answer = kr / t + kr * (t - i + 1) * st / t;
            result.append(String.format("<div>%10.4f / %d + %10.4f * (%d - %d + 1) * %10.4f / %d = %10.1f</div>",
                    kr, t, kr, t, i, st, t, answer));
        }
        return Response.status(200).entity(result.toString()).build();
    }
}
