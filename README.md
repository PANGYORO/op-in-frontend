## **👋Introduction**

> 오픈소스 컨트리뷰터들을 위한 커뮤니티 op-in 을 소개합니다. <br/>
> op-in은 오픈소스 기여자의 기여자를 위해 기여자에 의한 커뮤니티 서비스입니다.<br/>
> github 의 래포지토리를 기반으로 POST, QnA 를 작성하고 소통하면서 오픈소스 기여에 조금더 활성화 해보고 싶어 만들어진 서비스입니다.
>



## ✨ Feature

### 로그인/로그아웃
<img src="https://user-images.githubusercontent.com/49237205/220337067-3ea1dbc6-e3bf-42e9-a76e-abae5fb8a8e4.gif" width="640" height="360" alt="github_login"/>

### 내 레포지토리 조회 / 깃헙 레포지토리 추천
<img src="https://user-images.githubusercontent.com/49237205/220339833-8fb0ab82-7a78-496a-a65e-8197ca7b7f3a.gif"  width="640" height="360" 
 alt="github_recommand" />

1. SpringBatch를 통해 가져온 데이터를 이용해 레포지토리를 보여줍니다.
2. 유저가 등록한 태그와 유사도 검사를 통해 추천해줍니다.

### 마이페이지
<img src="https://user-images.githubusercontent.com/49237205/220340778-00b7337e-7fcc-4fb1-9caa-79d60f12f1e8.gif"  width="640" height="360" 
 alt="github_mypage" />

### 튜토리얼
<img src="https://user-images.githubusercontent.com/49237205/220342362-96086c94-41b2-46c6-9d0c-3088c11db111.gif"  width="640" height="360" 
 alt="github_tutorial" />

### Post
<img src="https://user-images.githubusercontent.com/49237205/220343684-04aba1d7-6738-485c-845f-911111709b96.gif"  width="640" height="360" 
 alt="github_post" />

### QnA
<img src="https://user-images.githubusercontent.com/49237205/220344358-f87bc931-99a8-4c58-9833-cdb5339ccde8.gif"  width="640" height="360" 
 alt="github_qna" />

## 🤳Developed by

|   **Name**   |                박성완                 |                정민지                |                  이동준                   |               조성욱                |                 김명진                  |               김창영                |
| :----------: | :-----------------------------------: | :----------------------------------: | :---------------------------------------: | :---------------------------------: | :-------------------------------------: | :---------------------------------: |
| **Position** |          PM<br>FrontEnd           |          Backend           |           BackEnd<br>FrontEnd           |        Frontend         |            Backend<br>release            |         FrontEnd         |
|   **Git**    | [GitHub](https://github.com/swany0509) | [GitHub](https://github.com/jellyKKing) | [GitHub](https://github.com/Djunnni) | [GitHub](https://github.com/chodone) | [GitHub](https://github.com/ManduTheCat) | [GitHub](https://github.com/kcy0521) |


## 아키텍쳐
![arch](https://user-images.githubusercontent.com/49237205/220345261-c4fafe1e-4e6e-4fce-a94f-a79c1ea53ac7.png)

## 📚기술 스택
| Tech         | Stack                                  |
| ------------ | -------------------------------------- |
| **Language** | Java, JavaScript                       |
| **Backend**  | Spring Boot, JPA, Spring Security, JWT, Spring Batch |
| **Frontend** | React, Tailwind, Recoil                 |
| **Database** | Mysql                                |
| **Server**   | AWS EC2, Nginx, S3.                         |
| **DevOps**   | Git, Docker, Jenkins, SonarQube                           |
## 📂Package Structure

### 🎨Frontend
```
├── api
│   └── http.js
├── assets
├── components
│   ├── edu
│   ├── event
│   ├── modals
│   ├── repository
│   └── user
├── constants
├── hooks
├── index.css
├── main.jsx
├── pages
│   ├── dashboard
│   ├── education
│   │   ├── documents
│   │   └── tutorial
│   ├── repository
│   │   ├── Recommand
│   │   ├── following
│   │   └── main
│   ├── search
│   └── user
└── recoil
```

### 🔥Convention
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
