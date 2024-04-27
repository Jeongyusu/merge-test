package shop.mtcoding.marketkurly.address;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.marketkurly.address.AddressRequest.AddressSaveReqDTO;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.user.UserJPARepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressJPARepository addressJPARepository;
    private final UserJPARepository userJPARepository;

    public List<Address> 모든주소찾기(Integer userId) {
        log.info("addressService 호출됨");
        List<Address> addresses = addressJPARepository.findByUserId(userId);
        return addresses;
    }

    @Transactional
    public Address 배송지저장(AddressSaveReqDTO addressSaveReqDTO, Integer userId) {

        User user = userJPARepository.findById(userId).get();
        Address address = Address.builder()
                .destination(addressSaveReqDTO.getDestination())
                .destinationDetail(addressSaveReqDTO.getDestinationDetail())
                .receiverName(addressSaveReqDTO.getReceiverName())
                .receiverTel(addressSaveReqDTO.getReceiverTel())
                .receiverTel(addressSaveReqDTO.getReceiverTel())
                .isDefaultAddress(addressSaveReqDTO.getIsDefaultAddress())
                .user(user)
                .build();
        if (addressSaveReqDTO.getIsDefaultAddress() == true) {
            addressJPARepository.updateDefaultAddress();
        }
        Address result = addressJPARepository.save(address);
        return result;
    }

    // @Transactional
    // public void 배송지수정(AddressSaveReqDTO addressSaveReqDTO) {

    // Integer sessionUserId = 1;
    // Address address = addressRepository.findById(id);
    // if (boardOP.isPresent()) {

    // // 더티 체킹
    // Board board = boardOP.get();
    // board.setTitle(updateDTO.getTitle());
    // board.setContent(updateDTO.getContent());
    // boardRepository.save(board); // 있어도되고 없어도됨 (@Transactional flush)
    // } else {
    // throw new MyException(id + "번 페이지를 찾을수 없어요");
    // }
    // }
}
