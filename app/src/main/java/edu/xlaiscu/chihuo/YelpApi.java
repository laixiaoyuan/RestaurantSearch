package edu.xlaiscu.chihuo;

import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Created by Lexie on 3/7/17.
 */

public class YelpApi {
    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search";
    private static final String CONSUMER_KEY = "rL5wyAXccx5XU-jLe02oIw";
    private static final String CONSUMER_SECRET = "YvQpF0rK1mHf0zgfqTk7yAOvaXQ";
    private static final String TOKEN = "G5H7midhonTaBaU36-9JdkIZTFTy5_tz";
    private static final String TOKEN_SECRET = "Mc4pwN_FatkQs-lPwFysnjAYiK4";

    private OAuthService service;
    private Token accessToken;

    public YelpApi() {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
        this.accessToken = new Token(TOKEN, TOKEN_SECRET);
    }
    public String searchForBusinessesByLocation(String term, String location, int searchLimit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(searchLimit));
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        Log.i("message", response.getBody());
        return response.getBody();


    }



}
