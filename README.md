# 목차
- [사용 기술](#사용-기술)
- [프로젝트 설명](#프로젝트-설명)
- [설계](#설계)
- [트러블슈팅](#트러블슈팅)

<br>

## 사용 기술

<a><img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=Hibernate&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white"/></a>
<a><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/></a>

<br>

## 프로젝트 설명


[프로젝트 저장소](https://github.com/Unicon-Sophist)

- 온라인 독서모임 플랫폼
  - WebRTC를 사용하여 모임 사람끼리 화상으로 독서모임을 가질 수 있다.

<br>

## 설계

- 프로젝트 구조
<p align="center">
<img src="https://user-images.githubusercontent.com/83000829/152116668-74a02cdc-a8c9-4e5c-b004-0c2786af7eb7.png" width=600 />
</p>

<br>

- ERD
<p align="center">
<img src="https://user-images.githubusercontent.com/83000829/152105709-28e3fdd7-95df-422b-8f53-5b6a6a57a24d.png" width=600 />
</p>

<br>

- 주로 맡은 부분
<p align="center">
<img src="https://user-images.githubusercontent.com/83000829/152113616-9b87f423-e8da-4203-b3a4-965998fa42b7.png" />
</p>

<br>

## 트러블슈팅

프로젝트를 진행하면서 고민했던 부분

- 응답으로 entity를 넘길 시 무한 참조가 일어날 수 있다는 것을 알았습니다. 응답 데이터를 어떻게 보내야 할 지 고민하다가 ResponseDto를 따로 만들어서 해결
- 파일업로드에서 PK를 문자 포함해서 자동 증가하도록 개발해야하는부분을 고민. Hibernate에서는 GeneratedValue로 어떤식으로 자동 증가할지 선언을 해주는데 자동키 생성 전략을 만들어서 해결
