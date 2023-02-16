## **👋Introduction**

> 오픈소스 컨트리뷰터들을 위한 커뮤니티 op-in  을 소게합니다.
op-in은 오픈소스 기여자의 기여자를 위해 기여자에 의한 커뮤니티 서비스입니다
op-in 은 git-hub 의 래포지토리를 기반으로 POST, QnA 를 작성하고 소통하면서
오픈소스 기여에 조금더 활성화 해보고 싶어 만들어진 서비스입니다.
>



## ✨ Feature

### 로그인/로그아웃

### 토픽 선택

### 기여래포 추천

### 내 래포지토리 조회

### 포스트 작성

### QnA  작성

### 튜토리얼

## 🤳Developed by

|   **Name**   |                박성완                 |                정민지                |                  이동준                   |               조성욱                |                 김명진                  |               김창영                |
| :----------: | :-----------------------------------: | :----------------------------------: | :---------------------------------------: | :---------------------------------: | :-------------------------------------: | :---------------------------------: |
| **Position** |          PM<br>FrontEnd           |          Backend           |           BackEnd            |        Frontend         |            Backend<br>release            |         FrontEnd         |
|   **Git**    | [GitHub](https://github.com/swany0509) | [GitHub](https://github.com/jellyKKing) | [GitHub](https://github.com/Djunnni) | [GitHub](https://github.com/chodone) | [GitHub](https://github.com/ManduTheCat) | [GitHub](https://github.com/kcy0521) |

## 아키텍쳐

![img](mdIMG\최종본.png)

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

### 🖥Backend
```
├── auth
│   └───  인증 관련 서비스, 컨트롤러
├── batch
│   └───  스프링 배치 관련 패키지
├── config
├── constant
│     └─── GIT 요청 API
├── event
│   └───  이벤트 도메인 서비스, 컨트롤러
├── exception
│   └──  exceptionAdvice관련
├── member
│   └───  인증 관련 서비스, 컨트롤러
├── persistence
│   └───  엔티티, 래포지토리 인터페이스
├── repo
├── search
    └───  검색 관련 서비스, 컨트롤러
```

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
## BackEnd