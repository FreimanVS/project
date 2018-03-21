package com.freimanvs.company.rest;

import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Annuity Payment Service Swagger-generated API", produces = MediaType.TEXT_HTML)
@Path("/bank/v2")
public class AnnuityPaymentService implements Calculator {

    @ApiOperation(value = "calcuate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    @Override
    public Response calculate(@ApiParam(value = "количество периодов оплаты", required = true)
                                @FormParam("t") int t,
                              @ApiParam(value = "сумма кредита", required = true)
                                @FormParam("kr") double kr,
                              @ApiParam(value = "процентная ставка, начисляемая на задолженность за период", required = true)
                                @FormParam("st") double st) {

        st = st / 100 / 12;
        double pl = kr * st / (1 - 1 / Math.pow ((1 + st), t));
        String result = String.format("<div>%10.4f * %10.4f / (1 - 1 / (1 + %10.4f) ^ %d) = %10.0f</div>",
                kr, st, st, t, pl);

        return Response.status(200).entity(result).build();
    }
}
