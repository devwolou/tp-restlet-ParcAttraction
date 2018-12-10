package org.inria.restlet.mta.application;


import org.inria.restlet.mta.resources.UsersResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author msimonin
 *
 */
public class MyParcApplication extends Application
{

    public MyParcApplication(Context context)
    {
        super(context);
    }

    @Override
    public Restlet createInboundRoot()
    {
        Router router = new Router(getContext());
        router.attach("/clients", UsersResource.class);


        router.attach("/navettes", UsersResource.class);


        return router;


    }
}
