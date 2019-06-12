package com.whl.core.base.utils;

import com.whl.core.base.enums.AccessTokenInfoEnum;
import com.whl.core.base.enums.RefreshTokenInfoEnum;
import com.whl.core.base.enums.WHLTokenExceptionEnum;
import com.whl.core.base.exception.WHLTokenException;
import com.whl.core.base.model.AccessTokenInfoModel;
import com.whl.core.base.model.RefreshTokenInfoModel;
import io.jsonwebtoken.*;

import java.text.ParseException;
import java.util.Date;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET_KEY = "9C0Fvolu7JVODyV68Ewa39vXD9w3m0dV";
    private final static String REFRESH_TOKEN_SECRET_KEY = "0bpWKOz5T7JNBEPFGxxcrEBXsxAcrYdc";

    /**
     * 生成认证凭证
     *
     * @param accessTokenInfoModel
     * @return
     * @throws WHLTokenException
     */
    public static String createAccessToken(AccessTokenInfoModel accessTokenInfoModel) throws WHLTokenException {

        String jwt = null;
        try {
            jwt = Jwts.builder()
                    .claim(AccessTokenInfoEnum.USER_ID.getKey(), accessTokenInfoModel.getUserId().toString())
                    .claim(AccessTokenInfoEnum.USERNAME.getKey(), accessTokenInfoModel.getUsername())
                    .claim(AccessTokenInfoEnum.MOBILE.getKey(), accessTokenInfoModel.getMobile())
                    .claim(AccessTokenInfoEnum.ORG_ID.getKey(), accessTokenInfoModel.getOrgId().toString())
                    .claim(AccessTokenInfoEnum.ROLE_ID.getKey(), accessTokenInfoModel.getRoleUUID())
                    .claim(AccessTokenInfoEnum.IS_SUPERUSER.getKey(), accessTokenInfoModel.getSuperUser().toString())
                    .claim(AccessTokenInfoEnum.EXPIRED_AT.getKey(),
                            TimeUtils.dateToString(accessTokenInfoModel.getExpiredAt()))
                    .claim(AccessTokenInfoEnum.APP_TYPE.getKey(), accessTokenInfoModel.getAppType())
                    .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET_KEY).compact();
        } catch (Exception e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.CREATE_ACCESS_TOKEN_FAILED);
        }

        return jwt;
    }

    /**
     * 生成刷新凭证
     *
     * @param refreshTokenInfoModel
     * @return
     * @throws WHLTokenException
     */
    public static String createRefreshToken(RefreshTokenInfoModel refreshTokenInfoModel) throws WHLTokenException {

        String jwt = null;
        try {
            jwt = Jwts.builder()
                    .claim(RefreshTokenInfoEnum.USER_ID.getKey(), refreshTokenInfoModel.getUserId().toString())
                    .claim(RefreshTokenInfoEnum.EXPIRED_AT.getKey(),
                            TimeUtils.dateToString(refreshTokenInfoModel.getExpiredAt()))
                    .signWith(SignatureAlgorithm.HS256, REFRESH_TOKEN_SECRET_KEY).compact();
        } catch (Exception e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.CREATE_REFRESH_TOKEN_FAILED);
        }

        return jwt;
    }

    /**
     * 解析认证凭证信息
     *
     * @param accessToken
     * @return
     * @throws WHLTokenException
     */
    public static AccessTokenInfoModel getAccessTokenInfo(String accessToken) throws WHLTokenException {
        // 解析token
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET_KEY).parseClaimsJws(accessToken);
        } catch (JwtException e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.PARSE_ACCESS_TOKEN_FAILED);
        }

        // 将解析后的信息进行格式转换
        Claims claims = jws.getBody();

        Integer userId = null;
        String username = null;
        Integer orgId = null;
        String mobile = null;
        String roleUUID = null;
        Boolean superUser = null;
        Date expiredAt = null;
        String appType = null;

        try {
            userId = Integer.valueOf((String) claims.get(AccessTokenInfoEnum.USER_ID.getKey()));
            username = (String) claims.get(AccessTokenInfoEnum.USERNAME.getKey());
            orgId = Integer.valueOf((String) claims.get(AccessTokenInfoEnum.ORG_ID.getKey()));
            mobile = (String) claims.get(AccessTokenInfoEnum.MOBILE.getKey());
            roleUUID = (String) claims.get(AccessTokenInfoEnum.ROLE_ID.getKey());
            superUser = Boolean.valueOf((String) claims.get(AccessTokenInfoEnum.IS_SUPERUSER.getKey()));
            expiredAt = TimeUtils.stringToDate((String) claims.get(AccessTokenInfoEnum.EXPIRED_AT.getKey()));
            appType = (String) claims.get(AccessTokenInfoEnum.APP_TYPE.getKey());
        } catch (ParseException e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.INVALID_ACCESS_TOKEN_PARAM);
        }

        // 生成model
        AccessTokenInfoModel accessTokenInfoModel = new AccessTokenInfoModel();
        accessTokenInfoModel.setUserId(userId);
        accessTokenInfoModel.setUsername(username);
        accessTokenInfoModel.setOrgId(orgId);
        accessTokenInfoModel.setMobile(mobile);
        accessTokenInfoModel.setRoleUUID(roleUUID);
        accessTokenInfoModel.setSuperUser(superUser);
        accessTokenInfoModel.setExpiredAt(expiredAt);
        accessTokenInfoModel.setAppType(appType);

        return accessTokenInfoModel;
    }

    /**
     * 解析刷新凭证信息
     *
     * @param refreshToken
     * @return
     * @throws WHLTokenException
     */
    public static RefreshTokenInfoModel getRefreshTokenInfo(String refreshToken) throws WHLTokenException {
        // 解析token
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parser().setSigningKey(REFRESH_TOKEN_SECRET_KEY).parseClaimsJws(refreshToken);
        } catch (JwtException e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.PARSE_REFRESH_TOKEN_FAILED);
        }

        // 将解析后的信息进行格式转换
        Claims claims = jws.getBody();

        Integer userId = null;
        Date expiredAt = null;

        try {
            userId = Integer.valueOf((String) claims.get(AccessTokenInfoEnum.USER_ID.getKey()));
            expiredAt = TimeUtils.stringToDate((String) claims.get(AccessTokenInfoEnum.EXPIRED_AT.getKey()));
        } catch (ParseException e) {
            throw new WHLTokenException(WHLTokenExceptionEnum.INVALID_REFRESH_TOKEN_PARAM);
        }

        // 生成model
        RefreshTokenInfoModel refreshTokenInfoModel = new RefreshTokenInfoModel();
        refreshTokenInfoModel.setUserId(userId);
        refreshTokenInfoModel.setExpiredAt(expiredAt);

        return refreshTokenInfoModel;
    }
}
