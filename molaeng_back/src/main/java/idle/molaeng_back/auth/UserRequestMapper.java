package idle.molaeng_back.auth;

import lombok.var;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public UserDto getUser(OAuth2User oAuth2User) {

        var attributes = oAuth2User.getAttributes();
        return UserDto.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .build();
    }
}
