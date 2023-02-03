import { atom } from "recoil"

const localStorageEffect =
  (key) =>
  ({ setSelf, onSet }) => {
    const savedValue = localStorage.getItem(key);
    // console.log(savedValue);
    if (savedValue != null) {
      setSelf(JSON.parse(savedValue));
    }

    onSet((newValue) => {
      // newValue 값의 길이가 0일 때
      // userlist에 대한 값을 삭제해주면 된다.
        const confirm = newValue.length === 0;
        confirm ? localStorage.removeItem(key) : localStorage.setItem(key, JSON.stringify(newValue));
    });  
  };



export const userStored = atom({
  key: "userStored",
  default: [],
  effects: [localStorageEffect("userStored")],
})