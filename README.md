![image 109](https://github.com/Jeongyusu/market-kurly-server/assets/98313279/3ee4d3d5-5817-49aa-9269-3a5560f07b04)
# 📌 마켓컬리 클론코딩
## 💻 프로젝트 5조
+ 정유수(팀장)
+ 윤지환
+ 이은지
+ 김대현
+ 김하율
  
## 🔗 기술스택
### Backend  
[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1699941393734?alt=media&token=d230a868-64f1-433b-8ad0-6de2b3258c44)](https://github.com/msdio/stackticon)

### Collaboration tools
[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1699941657108?alt=media&token=f9366757-a334-48ca-936a-2cea3d95b44f)](https://github.com/msdio/stackticon)

### DataBase
[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1699941741948?alt=media&token=7ec6f7e8-ca8a-48d7-93a0-907d8044efa6)](https://github.com/msdio/stackticon)


## DB, 인증/권한 검사
* DB
- DB설계시 일반회원(구매자), 판매자, 관리자가 요구하는 필드가 유사하기 때문에 유저엔티티를 분리하지 않았음 (Role 속성으로 구분)
- 엔티티의 속성을 정의하는 방식으로 Enum과 코드테이블이 있음
- Gender와 Role은 거의 변화하지 않는 속성이기 때문에 Enum으로 정의
- Category는 추가와 변동의 가능성이 존재하므로 테이블로 정의 하였음
- 주로 JPA Repository인터페이스의 기본 메소드 사용
- 기본 메소드로 요청되지않는 쿼리문은 JPQL(객체지향쿼리)로 요청

* 스프링 인증 권한
- JWT
- 스프링 진입 전에 필터로 인증 검사
- 컨트롤러 진입 전에 인터셉터로 권한 검사
- 특정 페이지 ex)로그인 의 경우 예외적으로 접근을 허용하는 로직을 작성

* 스프링 클래스
- DI : 필드 주입 vs 생성자 주입(RequiredArgsConstructor)
- Response, Request DTO 클래스를 분리
- RestController와 Controller를 분리해서 Web과, api요청을 따로 관리했음
- 상품 요청은 FORMDATA를 이용해서 전송, @ModelAttribute라는 객체를 이용해서 사진파일과 OptionList를 받았음

## 📓 기능설명
식품 판매를 전문으로 하는 온라인 쇼핑몰 사이트로써 이 페이지를 클론 코딩했습니다.

#### 회원 기능 
* 로그인 & 회원가입
* 개인 정보 수정
* 아이디 & 비밀번호 찾기

#### 주문 / 결제 기능
* 장바구니 담기 & 해제
* 쿠폰 적용 & 해제
* 주문 & 주문 취소
* 결제 & 환불
* 배송지 관리

#### 상품 기능
* 상품 검색
* 상품 필터별 정렬
* 상품 후기 쓰기 & 수정 & 삭제
* 상품 문의

#### 마이페이지 기능
* 주문 내역
* 쿠폰 내역

#### 고객센터 기능
* 자주 묻는 질문
* 1:1 문의

#### 판매자 & 관리자 사이트
* 판매 물품 등록( 판매자 )
* 판매자 회원가입
* 판매 물품 등록 승인 ( 관리자 )
* 공지사항 ( 관리자 )

## 모델링 연관관계
![image](https://github.com/Jeongyusu/market-kurly-server/assets/98313279/4ec2d52c-61a5-48c0-afa1-d53060abb6c4)

## 보완할 점 
* OAuth 사용한 카카오 로그인하기
* 문의, 검색하기
* 필터 정렬하기
* 푸쉬 알림 설정

## 느낀 점
* 프로젝트를 통해 배웠던 내용으로 기능을 구현할 때, 응용하는 게 어려웠던 거 같고 기초적인 부분을 더욱 더 보완해야겠다고 생각했다.
* 요구사항에 대한 분석이 약간 부족했다고 느꼈습니다.  초반설계할때 이부분을 보완한다면 화면도 더 정교하게 분석할수있고 그러면 프론트와 문서로 소통하는것도 더 편하게 할수있겠다고 생각했습니다.
* 실제로 알고 있다고 생각한 개념도 구체적 사례에 적용해보니 정확히 알지 못하는 경우가 많았습니다. 내가 뭘 모르는 지 많이 알게된 좋은 기회였습니다.
* 개인 역량이 부족하여 팀에 많은 도움이 되지 못해 아쉽습니다. 다음 프로젝트에서는 더욱 더 발전된 모습으로 임하겠습니다.

