import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const userInfo = atom({
  key: "userInfo",
  default: {
    nickname: "",
    email: "",
    img_url: "",
    logined: false,
  },
  effects_UNSTABLE: [persistAtom],
});
