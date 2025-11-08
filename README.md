## AWS EKS 환경에 springboot 운영서버 배포

springboot 기반 MSA 서비스를 AWS EKS환경에 배포하는 모든 과정 정리


노션 링크 : https://dynamic-cent-685.notion.site/AWS-EKS-spring-224ed6ed7f7c80dd965dea307a167cb4

### 섹션1. spring build 및 docker 기초

1. 스프링부트 애플리케이션 gradle build 로 bootjar 생성
2. Dockerfile 을 이용해 애플리케이션을 도커이미지로 빌드및 이미지 실행
3. docker-compose.yml 파일로 애플리케이션, mysql, redis를 한번에 실행및 연동
4. dockerHub에 이미지 업로드, 다운로드

### 섹션2. 쿠버네티스 개요 및 실습환경 세팅

1. 쿠버네티스 개요, 요소별 설명

### 섹션3. AWS핵심요소

1. 생략

### 섹션4.쿠버네티스 실습 환경 세팅

1. 가비아에서 도메인구매 ([kevin1983.shop](http://kevin1983.shop/))
2. 구매한 도메인 aws Route53에 호스팅등록
3. 가비아에서 도메인의 네임서버를 route53의 네임서버로 변경함.
4. EKS 클러스터 생성, 노드그룹 생성
5. 로컬에서 eks클러스터에 접근하기 위해 aws-cli, kubectl 설치, 클러스터 접근 세팅

### 섹션5. 쿠버네티스 주요 요소 실습

1. namespace, pod, service, deployment 개요
2. ingress설명, ingress-controller설치(로드밸런서 자동생성)
3. route53에 레코드추가, 레코드와 로드밸런서 연결
4. 추가한 레코드URL을 통해 로드밸런서->ingress->service->pod 로 접근확인.

### 섹션6. EKS를 활용한 spring 서버 배포

1. RDS 생성, ECR repo생성, redis service 구동
2. 로컬에서 ecr로그인
3. 로컬에서 도커이미지 build, ecr로 이미지 push 실행
4. 클러스터에 secret 생성 (DB url, username, password 저장)
5. secret에 입력한 값은 환경변수로 deployment env로 전달 -> 소스의 application.yml으로 전달된다.
6. eks 노드의 os 매니피스트와 도커이미지의 매니피스트 불일치로 인한 파드실행 오류 해결 (buildx)
7. cert-manager를 사용하여 도메인에 https 인증서 발급받기, 적용
8. github action으로 CI/CD 구축 (도커이미지 build, ecr 도커이미지 push, deploy restart.)

### 섹션7. 오토스케일,ARGOCD, 프로메테우스/그라파나 ⇒ 오토스케일은 pass

1. 오토스케일 생략
2. argoCD 개념, 적용 (github의 yml 설정과 실제 클러스터의 동기화)
3. 리소스부족에 대응하여 노드그룹 -> 노드수 확장
4. port-foward 을 통한 로컬 argoCD 웹ui 접근, ingress,https 로 웹ui 외부접근 허용
5. 프로메테우스,그라파나 적용

### 섹션8. EKS를 활용한 spring MSA 서버 배포

1. msa 구조 (member, order, product 서비스 분리)
2. spring cloud api gateway를 활용하여 분기, ingress를 활용하여 분기 개념
3. kafka, zookeeper 서비스 띄우기.
4. msa 구조에서 cicd 적용.
