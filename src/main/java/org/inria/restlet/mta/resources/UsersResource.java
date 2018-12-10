package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;


import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.backend.Client;
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
public class UsersResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public UsersResource()
    {
        super();
        backend_ = (Backend) getApplication().getContext().getAttributes()
            .get("backend");
    }

    /**
     *
     * Returns the list of all the users
     *
     * @return  JSON representation of the users
     * @throws JSONException
     */
    @Get("json")
    public Representation getUsers() throws JSONException
    {
        Client[] users = backend_.getParc_().getClients();

        Collection<JSONObject> jsonUsers = new ArrayList<JSONObject>();

        for (int i=0; i< users.length; i++)
        {
            JSONObject current = new JSONObject();
            current.put("Client :", users[i].getName());
            current.put("Etat :", users[i].getEtat());

            jsonUsers.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonUsers);
        return new JsonRepresentation(jsonArray);
    }

/*    @Post("json")
    public Representation createUser(JsonRepresentation representation)
        throws Exception
    {
        JSONObject object = representation.getJsonObject();
        String name = object.getString("name");
        int age = object.getInt("age");

        // Save the user
        User user = backend_.getDatabase().createUser(name, age);

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("name", user.getName());
        resultObject.put("age", user.getAge());
        resultObject.put("id", user.getId());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }
*/
}
