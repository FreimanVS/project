package com.freimanvs.company.rest;

import com.freimanvs.company.rest.beans.CalculateAnnuityBeanImpl;
import com.freimanvs.company.rest.beans.interfaces.CalculateAnnuityBean;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "Annuity Payment Service Swagger-generated API", produces = MediaType.TEXT_HTML)
@Path("/bank/v2/calculations")
public class AnnuityPaymentService implements Calculator {

//    @EJB
//    @Inject
    private CalculateAnnuityBean calculateAnnuityBean = CDI.current().select(CalculateAnnuityBeanImpl.class).get();

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

        return Response.status(200).entity(calculateAnnuityBean.calculate(t, kr, st)).build();
    }
}
