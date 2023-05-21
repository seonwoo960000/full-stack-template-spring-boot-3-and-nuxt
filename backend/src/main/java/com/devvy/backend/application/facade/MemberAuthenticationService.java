package com.devvy.backend.application.facade;

import com.devvy.backend.application.dto.MemberAuthenticationDto;
import com.devvy.backend.common.enums.AuthorizationType;

public interface MemberAuthenticationService {

    MemberAuthenticationDto findOneWithAuthoritiesByLogin(String login, AuthorizationType authorizationType);
}
