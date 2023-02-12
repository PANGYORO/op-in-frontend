import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const DEFAULT_USERINFO = {
  nickname: "",
  email: "",
  img_url: "",
  logined: false,
};

export const userInfo = atom({
  key: "userInfo",
  default: Object.assign(DEFAULT_USERINFO),
  effects_UNSTABLE: [persistAtom],
});

