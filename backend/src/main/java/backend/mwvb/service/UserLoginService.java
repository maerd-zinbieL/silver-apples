package backend.mwvb.service;

import backend.mwvb.entity.LoginInfo;
import backend.mwvb.exception.UserLoginException;
import backend.mwvb.util.Response;

import java.util.Map;

public interface UserLoginService {
    String USER_AUTH_JWT_SUBJECT = "USER-AUTH";
    String USER_AUTH_JWT_CLAIMS_KEY = "USER-ID";
    Response<Map<String, String>> login(LoginInfo loginInfo) throws UserLoginException;
}
