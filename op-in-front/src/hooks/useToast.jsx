import  { useCallback}  from 'react';
import { toast } from 'react-toastify';

export function useToast() {
  

  const setToast = (data) => {
    toast(data.message, { position: 'bottom-center' })

  };

  return {setToast}
}