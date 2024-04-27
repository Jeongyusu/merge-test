package shop.mtcoding.marketkurly.user;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception400;
import shop.mtcoding.marketkurly._core.utils.JwtTokenUtils;
import shop.mtcoding.marketkurly.user.UserRequest.LoginDTO;
import shop.mtcoding.marketkurly.user.UserRequest.SellerJoinDTO;
import shop.mtcoding.marketkurly.user.UserRequest.UpdateCheckDTO;
import shop.mtcoding.marketkurly.user.UserRequest.UserFindUsernameDTO;
import shop.mtcoding.marketkurly.user.UserRequest.UserUpdateDTO;
import shop.mtcoding.marketkurly.user.UserResponse.TokenDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserJPARepository userJPARepository;

    @Transactional
    public void 회원가입(UserRequest.UserJoinDTO userJoinDTO) {

        System.out.println("서비스 진입가능?");
        System.out.println("userId에는 머가있지? " + userJoinDTO.getUserId());
        // 1. 회원가입시 아이디 중복 체크
        User user = userJPARepository.findByUserId(userJoinDTO.getUserId());
        if (user != null) {
            throw new Exception400("중복된 아이디입니다.");
        }

        String encPassword = BCrypt.hashpw(userJoinDTO.getUserPassword(), BCrypt.gensalt());
        System.out.println("회원가입 1 ");
        userJoinDTO.setUserPassword(encPassword);
        // 2. 디비 저장
        User userPS = userJPARepository.save(userJoinDTO.toEntity());
        System.out.println("회원가입 2 userPS : " + userPS);

    }

    // 아이디 중복체크 클릭시 중복체크
    public void 중복확인(String userLoginId) {
        User optUser = userJPARepository.findByUserId(userLoginId);
        if (optUser != null) {
            throw new Exception400("중복된 아이디입니다.");
        }
    }

    public TokenDTO 로그인(LoginDTO loginDTO) {

        String encPassword = BCrypt.hashpw(loginDTO.getUserPassword(), BCrypt.gensalt());
        System.out.println(encPassword);
        User user = userJPARepository.findByUserId(loginDTO.getUserId());
        if (user == null) {
            System.out.println("로그인 서비스 : 존재하지 않는 아이디 입니다");
            throw new Exception400("존재하지 않는 아이디 입니다.");
        }
        if (!BCrypt.checkpw(loginDTO.getUserPassword(), user.getUserPassword())) {
            System.out.println("로그인 서비스 : 비밀번호가 일치하지 않습니다.");
            throw new Exception400("비밀번호가 일치하지 않습니다.");
        }

        System.out.println("로그인 서비스 : 성공");
        // hideUserPS(user) << 비밀번호 null처리하는 메서드
        // 토큰 or 리턴값에 비밀번호 들어갈 필요없음 << hideUserPS로 null처리
        User userPS = UserResponse.UserPSDTO.hideUserPS(user);
        return new UserResponse.TokenDTO(JwtTokenUtils.create(userPS), userPS);
    }

    //
    public String 아이디찾기(UserFindUsernameDTO requestDTO) {
        Optional<User> optUser = userJPARepository.findByUsernameAndUserEmail(requestDTO.getUsername(),
                requestDTO.getUserEmail());

        if (optUser.isPresent()) {
            return optUser.get().getUserId();
        } else {
            return null;
        }
    }

    public UserResponse.LoginDTO 로그인정보조회(String userId) {
        User userDTO = userJPARepository.findByUserId(userId);
        return new UserResponse.LoginDTO(userDTO);
    }

    public void 판매자회원가입(SellerJoinDTO sellerJoinDTO) {

        String encPassword = BCrypt.hashpw(sellerJoinDTO.getUserPassword(), BCrypt.gensalt());
        System.out.println("회원가입 1 ");
        sellerJoinDTO.setUserPassword(encPassword);
        User user = User.builder()
                .userId(sellerJoinDTO.getUserId())
                .username(sellerJoinDTO.getUsername())
                .userEmail(sellerJoinDTO.getUserEmail())
                .userPassword(encPassword)
                .role(Role.SELLER)
                .build();

        userJPARepository.save(user);
    }

    @Transactional
    public User 회원정보수정(UserUpdateDTO userUpdateDTO, Integer userId) {

        String encPassword = BCrypt.hashpw(userUpdateDTO.getUserPassword(), BCrypt.gensalt());
        userUpdateDTO.setUserPassword(encPassword);
        userUpdateDTO.setId(userId);
        User user = userJPARepository.save(userUpdateDTO.toIdEntity());
        return user;
    }

    public User 회원수정체크(UpdateCheckDTO updateCheckDTO) {

        User user = userJPARepository.findByUserId(updateCheckDTO.getUserId());
        if (user == null) {
            throw new Exception400("존재하지 않는 아이디 입니다.");
        }
        if (!BCrypt.checkpw(updateCheckDTO.getUserPassword(), user.getUserPassword())) {
            throw new Exception400("비밀번호가 일치하지 않습니다.");
        }

        // hideUserPS(user) << 비밀번호 null처리하는 메서드
        // 토큰 or 리턴값에 비밀번호 들어갈 필요없음 << hideUserPS로 null처리
        User userPS = UserResponse.UserPSDTO.hideUserPS(user);
        return userPS;
    }

}