package idle.molaeng_back.auth.controller;


import idle.molaeng_back.auth.model.OAuthTokenDto;
import idle.molaeng_back.auth.model.OAuthUserRes;
import idle.molaeng_back.auth.service.OAuthService;
import idle.molaeng_back.global.util.RedisUtil;
import idle.molaeng_back.user.model.User;
import idle.molaeng_back.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private final OAuthService oAuthService;

    @Autowired
    private final UserService userService;


    @Autowired
    private final RedisUtil redisUtil;

    public static final long REFRESH_TOKEN_VALIDITY = 3 * 24 * 60 * 60L;


    @GetMapping("/kakaoLogin")
    public ResponseEntity kakaoLogin(@RequestParam String code, HttpSession httpSession) throws IOException{
        HashMap<String, Object> result = new HashMap<>(); // (userInfo + OAuthToken)
        HashMap<String, Object> response = new HashMap<>(); // response 값
        OAuthUserRes userInfo; // kakao에서 받아올 userInfo
        OAuthTokenDto oAuthToken; // Token 저장
        User user;
        RedirectAttributes redirectAttributes = null;


        try {
            // kakao에서 token 발급 받음
            oAuthToken = oAuthService.getAccessToken(code);

            // 발급 받은 token으로 바로 userInfo 받아온다.
            userInfo = oAuthService.getUserInfo(oAuthToken.getAccessToken());

            // 만약 아직 회원이 아니라면 DB에 정보를 저장한다.
            if(!userService.isMember(userInfo.getUuid())){
                // userImg까지 저장해야 한다.
                user = userService.joinUser(userInfo.getNickname(), userInfo.getUuid());
                log.info("회원가입 완료" + user);
            }else{
                // 회원이라면 정보만 받아온다.
                user = userService.findByUuid(userInfo.getUuid());
                log.info("이미 가입한 회원입니다."+user);
            }


            // client에는 userInfo + OAuth token을 넘겨주고, Redis에 refresh token을 저장한다.
            // 앞으로 인증이 필요한 분에서는 header에 token을 담아서 전송할 것이다.
            // front에서는 처음 로그인 시에 userInfo를 store에 저장하고, token도 저장해서 만료 전까지 계속 사용한다.


            // refresh token redis에 저장
            redisUtil.setDataExpire(String.valueOf(user.getUserId()), oAuthToken.getRefreshToken(), REFRESH_TOKEN_VALIDITY);
            log.info("redis 저장 성공!");

            result.put("userInfo", userInfo);
            result.put("Token", oAuthToken);

            response.put("message", "success");
            response.put("result", result);

            log.info("@@@@@@2데이터 확인" + response);


            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            result.put("result", code);
            result.put("message", "카카오 소셜 로그인 실패");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    // logout 하면서 token도 같이 삭제한다(Kakao 공식 문서 참고)
    // Front에서는 Store 값에 있던 것들을 다 날린다.
    @GetMapping("/kakaoLogout")
    public ResponseEntity kakaoLogout(@RequestParam long uuid){
        HashMap<String, Object> result = new HashMap<>();

        try {
            oAuthService.kakaoLogout(uuid);
            result.put("result", uuid);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.put("result", uuid);
            result.put("message", "카카오 로그아웃 오류 발생");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
