import { toast } from 'react-toastify';

export function useToast() {
  

  const setToast = (data) => {
    toast(data.message, { position: 'bottom-right' })

  };

  return {setToast}
}