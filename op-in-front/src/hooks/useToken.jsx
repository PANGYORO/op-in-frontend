import { useState, useEffect, useCallback } from 'react';
import { useCookies } from 'react-cookie';


function useToken() {

  const [cookies, setCookie, removeCookie] = useCookies();
  const [token, setToken] = useState();

  useEffect(() => {
    const _token = cookies
    setToken(_token);
  }, []);

  const saveToken = useCallback((token) => {
    // setCookie("token", JSON.stringify(token));
    // for (const key in token) {
    //   setCookie(key, token[key])
    // }
    
    setToken(token);
  }, []);

  const removeToken = useCallback(() => {
    removeCookie('accessToken')
    removeCookie('refreshToken')
    removeCookie('type')
    
  }, []);
  

  return { saveToken, token, removeToken };
}

export default useToken;