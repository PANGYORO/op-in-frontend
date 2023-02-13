import { atom } from "recoil";

export const menuState = atom({
  key: "menuState",
  default: "dashboard",
});

export const repoMenuState = atom({
  key: "repoMenuState",
  default: "myrepo",
});
