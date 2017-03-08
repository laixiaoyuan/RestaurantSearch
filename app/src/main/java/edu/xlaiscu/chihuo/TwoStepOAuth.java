package edu.xlaiscu.chihuo;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Created by Lexie on 3/7/17.
 */

public class TwoStepOAuth extends DefaultApi10a{
    @Override
    public String getAccessTokenEndpoint() {return null;}

    @Override
    public String getAuthorizationUrl(Token unused) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }
}
