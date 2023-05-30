package com.devvy.backend.infrastructure.security;

import static com.devvy.backend.common.constants.Urls.OAUTH2_BASE_URL;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final HashMap<String, String> oauth2RedirectUrls;

    public CustomOauth2UserService(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        oauth2RedirectUrls = new HashMap<>();
        Iterable<ClientRegistration> clientRegistrations = null;
        var resolvableType = ResolvableType.forInstance(clientRegistrationRepository).as(Iterable.class);

        if (resolvableType != ResolvableType.NONE &&
            ClientRegistration.class.isAssignableFrom(resolvableType.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        assert clientRegistrations != null;
        clientRegistrations.forEach(clientRegistration -> {
            oauth2RedirectUrls.put(clientRegistration.getClientName(),
                                   OAUTH2_BASE_URL + "/" + clientRegistration.getRegistrationId());
        });
    }

    public Map<String, String> getOAuth2RedirectUrls() {
        return oauth2RedirectUrls;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        System.out.println("Oauth2 loading user");
        return user;
    }
}
