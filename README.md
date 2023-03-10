## **๐Introduction**

> ์คํ์์ค ์ปจํธ๋ฆฌ๋ทฐํฐ๋ค์ ์ํ ์ปค๋ฎค๋ํฐ op-in ์ ์๊ฐํฉ๋๋ค. <br/>
> op-in์ ์คํ์์ค ๊ธฐ์ฌ์์ ๊ธฐ์ฌ์๋ฅผ ์ํด ๊ธฐ์ฌ์์ ์ํ ์ปค๋ฎค๋ํฐ ์๋น์ค์๋๋ค.<br/>
> github ์ ๋ํฌ์งํ ๋ฆฌ๋ฅผ ๊ธฐ๋ฐ์ผ๋ก POST, QnA ๋ฅผ ์์ฑํ๊ณ  ์ํตํ๋ฉด์ ์คํ์์ค ๊ธฐ์ฌ์ ์กฐ๊ธ๋ ํ์ฑํ ํด๋ณด๊ณ  ์ถ์ด ๋ง๋ค์ด์ง ์๋น์ค์๋๋ค.
>



## โจ Feature

### ๋ก๊ทธ์ธ/๋ก๊ทธ์์
<img src="https://user-images.githubusercontent.com/49237205/220337067-3ea1dbc6-e3bf-42e9-a76e-abae5fb8a8e4.gif" width="640" height="360" alt="github_login"/>

### ๋ด ๋ ํฌ์งํ ๋ฆฌ ์กฐํ / ๊นํ ๋ ํฌ์งํ ๋ฆฌ ์ถ์ฒ
<img src="https://user-images.githubusercontent.com/49237205/220339833-8fb0ab82-7a78-496a-a65e-8197ca7b7f3a.gif"  width="640" height="360" 
 alt="github_recommand" />

1. SpringBatch๋ฅผ ํตํด ๊ฐ์ ธ์จ ๋ฐ์ดํฐ๋ฅผ ์ด์ฉํด ๋ ํฌ์งํ ๋ฆฌ๋ฅผ ๋ณด์ฌ์ค๋๋ค.
2. ์ ์ ๊ฐ ๋ฑ๋กํ ํ๊ทธ์ ์ ์ฌ๋ ๊ฒ์ฌ๋ฅผ ํตํด ์ถ์ฒํด์ค๋๋ค.

### ๋ง์ดํ์ด์ง
<img src="https://user-images.githubusercontent.com/49237205/220340778-00b7337e-7fcc-4fb1-9caa-79d60f12f1e8.gif"  width="640" height="360" 
 alt="github_mypage" />

### ํํ ๋ฆฌ์ผ
<img src="https://user-images.githubusercontent.com/49237205/220342362-96086c94-41b2-46c6-9d0c-3088c11db111.gif"  width="640" height="360" 
 alt="github_tutorial" />

### Post
<img src="https://user-images.githubusercontent.com/49237205/220343684-04aba1d7-6738-485c-845f-911111709b96.gif"  width="640" height="360" 
 alt="github_post" />

### QnA
<img src="https://user-images.githubusercontent.com/49237205/220344358-f87bc931-99a8-4c58-9833-cdb5339ccde8.gif"  width="640" height="360" 
 alt="github_qna" />

## ๐คณDeveloped by

|   **Name**   |                ๋ฐ์ฑ์                 |                ์ ๋ฏผ์ง                |                  ์ด๋์ค                   |               ์กฐ์ฑ์ฑ                |                 ๊น๋ช์ง                  |               ๊น์ฐฝ์                |
| :----------: | :-----------------------------------: | :----------------------------------: | :---------------------------------------: | :---------------------------------: | :-------------------------------------: | :---------------------------------: |
| **Position** |          PM<br>FrontEnd           |          Backend           |           BackEnd<br>FrontEnd           |        Frontend         |            Backend<br>release            |         FrontEnd         |
|   **Git**    | [GitHub](https://github.com/swany0509) | [GitHub](https://github.com/jellyKKing) | [GitHub](https://github.com/Djunnni) | [GitHub](https://github.com/chodone) | [GitHub](https://github.com/ManduTheCat) | [GitHub](https://github.com/kcy0521) |


## ์ํคํ์ณ
![arch](https://user-images.githubusercontent.com/49237205/220345261-c4fafe1e-4e6e-4fce-a94f-a79c1ea53ac7.png)

## ๐๊ธฐ์  ์คํ
| Tech         | Stack                                  |
| ------------ | -------------------------------------- |
| **Language** | Java, JavaScript                       |
| **Backend**  | Spring Boot, JPA, Spring Security, JWT, Spring Batch |
| **Frontend** | React, Tailwind, Recoil                 |
| **Database** | Mysql                                |
| **Server**   | AWS EC2, Nginx, S3.                         |
| **DevOps**   | Git, Docker, Jenkins, SonarQube                           |
## ๐Package Structure

### ๐จFrontend
```
โโโ api
โย ย  โโโ http.js
โโโ assets
โโโ components
โย ย  โโโ edu
โย ย  โโโ event
โย ย  โโโ modals
โย ย  โโโ repository
โย ย  โโโ user
โโโ constants
โโโ hooks
โโโ index.css
โโโ main.jsx
โโโ pages
โย ย  โโโ dashboard
โย ย  โโโ education
โย ย  โย ย  โโโ documents
โย ย  โย ย  โโโ tutorial
โย ย  โโโ repository
โย ย  โย ย  โโโ Recommand
โย ย  โย ย  โโโ following
โย ย  โย ย  โโโ main
โย ย  โโโ search
โย ย  โโโ user
โโโ recoil
```

### ๐ฅConvention
## Front
### ESLInt
```javascript
module.exports = {
    "env": {
        "browser": true,
        "es2021": true,
        "node": true
    },
    "extends": [
        "eslint:recommended",
        "plugin:react/recommended"
    ],
    "overrides": [
    ],
    "parserOptions": {
        "ecmaVersion": "latest",
        "sourceType": "module"
    },
    "plugins": [
        "react"
    ],
    "rules": {
        "react/prop-types": "off",
    },
}
```
### prettier
```javascript
{
  "tabWidth": 2,
  "semi": true,
  "trailingComma": "all",
  "printWidth": 80
}
```
