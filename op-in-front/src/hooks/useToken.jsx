import { useState, useEffect, useCallback } from 'react';

function useToken() {

  const [token, setToken] = useState();

  useEffect(() => {
    const _token = JSON.parse(localStorage.getItem("token"));
    setToken(_token);
  }, []);

  const saveToken = useCallback((token) => {
    localStorage.setItem("token", JSON.stringify(token));
    setToken(token);
  }, []);

  return { saveToken, token };
}

export default useToken;