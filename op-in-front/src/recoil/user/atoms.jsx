import { atom } from "recoil"

export const userInfo = atom({
  key: "userInfo",
  default: {
    nickname: '',
    email: '',
    img_url: '',
    logined: false,
  }
});