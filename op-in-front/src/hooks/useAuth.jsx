import { useEffect, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import { useRecoilState } from "recoil";

import jwt_decode from "jwt-decode";

import { userInfo, DEFAULT_USERINFO } from "@recoil/user/atoms";
import { menuState, repoMenuState } from "@recoil/sidebar/atoms";
import http from "@api/http";
import { useToast } from "@hooks/useToast";

function useAuth() {
  const [cookie, setCookie, removeCookie] = useCookies();
  const [user, setUser] = useRecoilState(userInfo);
  const [menu, setMenu] = useRecoilState(menuState);
  const [repoMenu, setRepoMenu] = useRecoilState(repoMenuState);

  const navigate = useNavigate();
  const { setToast } = useToast();

  const logout = useCallback(() => {
    _logout();
    _removeToken();
    setUser(DEFAULT_USERINFO);
    setToast({ message: "로그아웃 성공!" });
    _goHome();
  }, []);

  const login = useCallback(
    (_token) => {
      const token = cookie.accessToken || _token;
      // console.debug("@login", token);
      if (token) {
        const decodeAccessTokenUserInfo = jwt_decode(token);
        _getUserInfo(({ data }) => {
          setUser((prev) => ({
            ...prev,
            id: data.id,
            nickname: data.nickname,
            email: data.email || decodeAccessTokenUserInfo.email,
            img_url: data.avataUrl,
            role: data.role,
            githubSync: data.githubSync,
            githubId: data.githubId,
            logined: true,
          }));
          setToast({ message: "로그인 성공!" });
          _goHome();
        });
      }
    },
    [cookie.accessToken]
  );

  useEffect(() => {
    if (cookie.accessToken && cookie.refreshToken) {
      const decodeAccessTokenUserInfo = jwt_decode(cookie.accessToken);
      const decodeRefreshTokenUserInfo = jwt_decode(cookie.refreshToken);

      if (!_isExpiredToken(decodeAccessTokenUserInfo.exp)) {
        _getUserInfo(({ data }) => {
          setUser((prev) => ({
            ...prev,
            id: data.id,
            nickname: data.nickname,
            email: data.email || decodeAccessTokenUserInfo.email,
            img_url: data.avataUrl,
            role: data.role,
            githubSync: data.githubSync,
            githubId: data.githubId,
            logined: true,
          }));
        });
      } else if (
        _isExpiredToken(decodeAccessTokenUserInfo.exp) &&
        _isExpiredToken(decodeRefreshTokenUserInfo.exp)
      ) {
        _removeToken();
        // 둘 다 만료가 됐으면 로그아웃 시키기
        setUser(DEFAULT_USERINFO);
        setToast({ message: "인증이 만료되었습니다. 다시 로그인 해주세요!" });
        navigate("/signin");
      }
    }
  }, [cookie.accessToken, cookie.refreshToken]);

  const _checkLogin = useCallback(() => {
    if (cookie.accessToken) {
      const decodeAccessTokenUserInfo = jwt_decode(cookie.accessToken);
      if (!_isExpiredToken(decodeAccessTokenUserInfo.exp)) {
        _getUserInfo(({ data }) => {
          setUser((prev) => ({
            ...prev,
            id: data.id,
            nickname: data.nickname,
            email: data.email || decodeAccessTokenUserInfo.email,
            img_url: data.avataUrl,
            role: data.role,
            githubSync: data.githubSync,
            githubId: data.githubId,
            logined: true,
          }));
        });
      }
    }
  }, [cookie.accessToken]);

  const _isExpiredToken = (exp) => {
    return exp * 1000 < Date.now();
  };

  const _removeToken = useCallback(() => {
    removeCookie("accessToken", { path: "/" });
    removeCookie("refreshToken", { path: "/" });
    removeCookie("type", { path: "/" });
  }, []);

  const _goHome = useCallback(() => {
    setMenu("dashboard");
    setRepoMenu("myrepo");
    navigate("/");
  }, []);

  const _logout = useCallback(() => {
    http.post("auth/logout");
  }, []);

  const _getUserInfo = useCallback((callback = () => {}) => {
    http
      .get(`member`)
      .then(callback)
      .catch((e) => {
        console.error("[useAuth ERROR]", e);
      });
  }, []);

  return {
    logout,
    login,
    hasAuth: user.logined,
    auth: user,
  };
}

export default useAuth;
