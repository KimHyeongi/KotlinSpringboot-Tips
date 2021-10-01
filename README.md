# Kotlin + Spring Boot 샘플 프로젝트

## jdk11 설치
Open JDK 11 : https://openjdk.java.net/install

## Docker설치
https://hub.docker.com/editions/community/docker-ce-desktop-mac
(개인계정가입 - Optional)

## Docker 실행
```shell
$> docker-compose up -d
```

## Docker 데몬 정상확인
```shell
$> docker ps -a
```


# 각 모듈 설명

## 비서버 모듈
| 모듈   |      설명      |
|----------|:-------------:|
| domain-model |  전체 레이어 데이터  |
| domain-service |  데이터 관리 모듈 ( Entity, Repository, Service ) |

## 서버 모듈
| 모듈   |      설명      |  서버 |
|----------|:-------------:|------:|
| app-api |  모바일앱웹 제공 API  | https:// |
| internal-api |  내부 제공 API  | https:// |