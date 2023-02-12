import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const DEFAULT_USERINFO = {
  id: null,
  role: null,
  githubSync: null,
  githubId: null,
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

