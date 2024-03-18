package com.ssafy.user.infrastructure.oauthprovider;

import static com.ssafy.user.common.CustomExceptionStatus.NOT_SUPPORTED_OAUTH_SERVICE;

import com.ssafy.user.exception.AuthException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OauthProviders {

    private final List<OauthProvider> providers;

    public OauthProviders(final List<OauthProvider> providers) {
        this.providers = providers;
    }

    public OauthProvider mapping(final String providerName) {
        return providers.stream()
                .filter(provider -> provider.is(providerName))
                .findFirst()
                .orElseThrow(() -> new AuthException(NOT_SUPPORTED_OAUTH_SERVICE));
    }
}
