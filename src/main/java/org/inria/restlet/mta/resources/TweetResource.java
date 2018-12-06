package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TweetResource extends ServerResource {
    /** Backend.*/
    private Backend backend_;

    /** Utilisateur géré par cette resource.*/
    private User user_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    @Post("json")
    public Representation createTweet(JsonRepresentation representation)
            throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        User user_ = backend_.getDatabase().getUser(userId);

        JSONObject object = representation.getJsonObject();
        String title = object.getString("title");
        String body = object.getString("body");

        //Add tweet to Tweet Collection
        user_.addTweet(new Tweet(title,body,new Date()));

        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("LOG AJOUT", "Success");
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

    @Get("json")
    public Representation getTweets() throws Exception
    {

        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);

        List<Tweet> mesTweet_ = user_.getMesTweet();
        Collection<JSONObject> jsonTweets = new ArrayList<JSONObject>();

        for (Tweet t: mesTweet_){
            JSONObject current = new JSONObject();
            current.put("title",t.getTitle());
            current.put("body",t.getBody());
            current.put("date",t.getDate().toString());
            jsonTweets.add(current);
        }

        JSONArray jsonArray = new JSONArray(jsonTweets);
        return new JsonRepresentation(jsonArray);
    }
}
