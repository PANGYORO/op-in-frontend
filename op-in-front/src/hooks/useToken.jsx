import { useState, useEffect, useCallback } from "react";
import { useCookies } from "react-cookie";

function useToken() {
  const [cookies, _, removeCookie] = useCookies();
  const [token, setToken] = useState();

  useEffect(() => {
    const _token = cookies;
    setToken(_token);
  }, []);

  const saveToken = useCallback((token) => {
    setToken(token);
  }, []);

  const removeToken = useCallback(() => {
    removeCookie("accessToken", { path: "/" });
    removeCookie("refreshToken", { path: "/" });
    removeCookie("type", { path: "/" });
  }, []);

  return { saveToken, token, removeToken };
}

export default useToken;

