package io.vertx.mario.handler;

import io.vertx.mario.helper.AppUserHelper;
import io.vertx.mario.helper.ControllerHelper;
import io.vertx.mario.helper.TemporaryLinkHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AppUserHandlerTest {
    @InjectMocks
    AppUserHandler handler;

    private ControllerHelper helper = new ControllerHelper();

    @Test
    public void shouldTestIndex() {
        helper.mockRoutingContext();
        handler.signUp(helper.routingContext);

        //Assert.assertEquals();
    }

}