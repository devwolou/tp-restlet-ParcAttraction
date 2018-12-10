package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;


import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Client;
import org.inria.restlet.mta.backend.Navette;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing the users
 *
 * @author ctedeschi
 * @author msimonin
 *
 */

public class NavettesRessource extends ServerResource{

    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public NavettesRessource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    /**
     *
     * Returns the list of all the users
     *
     * @return  JSON representation of the users
     * @throws JSONException
     */
    @Get("json")
    public Representation getNavettes() throws JSONException
    {
        Navette[] navettes = backend_.getParc_().getNavettes();

        Collection<JSONObject> jsonUsers = new ArrayList<JSONObject>();

        for (int i=0; i< navettes.length; i++)
        {
            JSONObject current = new JSONObject();
            current.put("Navette :", navettes[i].getName());
            current.put("Etat :", navettes[i].getEtatNavette());

            jsonUsers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonUsers);
        return new JsonRepresentation(jsonArray);
    }
}
