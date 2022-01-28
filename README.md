# 목차
- [프로젝트 설명](#프로젝트-설명)
- [설계](#설계)
- [트러블 슈팅](#트러블슈팅)
- [관리](#관리)

<br>

## 프로젝트 설명

<p align="center">
<img src="https://user-images.githubusercontent.com/83000829/151493682-27175da2-d3e9-4e8b-9d88-9543292211e5.gif" width=700 />
</p>

<p align="center">
<img width="700" alt="1" src="https://user-images.githubusercontent.com/83000829/151493955-13594125-26f6-42b8-b676-bf76b1c01755.png">
</p>

- 온라인 독서모임 플랫폼
  
<br>

## 설계


<br>

## 트러블슈팅
다음은 프로젝트를 진행하면서 고민했던 부분입니다.
- 응답으로 entity를 넘길 시 무한 참조가 일어날 수 있다는 것을 알았습니다. 응답 데이터를 어떻게 보내야 할 지 고민하다가 ResponseDto를 따로 만들어서 해결했습니다.
- 파일업로드에서 PK를 문자 포함해서 자동 증가하도록 개발해야하는부분을 고민했습니다. Hibernate에서는 GeneratedValue로 어떤식으로 자동 증가할지 선언을 해주는데 자동키 생성 전략을 직접 만들어서 해결하였습니다.

