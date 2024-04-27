package shop.mtcoding.marketkurly.cart;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception404;
import shop.mtcoding.marketkurly.address.Address;
import shop.mtcoding.marketkurly.address.AddressJPARepository;
import shop.mtcoding.marketkurly.cart.CartRequest.CartDeleteListDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.CartSaveDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.SelectedCartListDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.SelectedCartProductDTO;
import shop.mtcoding.marketkurly.cart.CartRequest.SelectedOptionDTO;
import shop.mtcoding.marketkurly.option.Option;
import shop.mtcoding.marketkurly.option.OptionJPARepository;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.user.UserJPARepository;
import shop.mtcoding.marketkurly.usercoupon.UserCoupon;
import shop.mtcoding.marketkurly.usercoupon.UserCouponJPARepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartJPARepository cartJPARepository;
    private final OptionJPARepository optionJPARepository;
    private final AddressJPARepository addressJPARepository;
    private final UserCouponJPARepository userCouponJPARepository;
    private final UserJPARepository userJPARepository;

    public CartResponse.FindAllDTO 장바구니목록조회(Integer memberId) {

        Address address = addressJPARepository.기본배송지찾기(memberId).get();
        List<Cart> carts = cartJPARepository.findByUser_Id(memberId);
        return new CartResponse.FindAllDTO(carts, address);
    }

    public void 장바구니주문검증(SelectedCartListDTO selectedCartListDTO, Integer userId) {

        List<SelectedCartProductDTO> selectedCartProducts = selectedCartListDTO.getSelectedCartProducts();
        Optional<UserCoupon> usercouponOP = userCouponJPARepository.findById(selectedCartListDTO.getUserCouponId());
        Integer couponReduceAmount = 0;
        Integer DBPrice = 0;
        Integer DBDeliveryFee = 3500;
        if (usercouponOP.isEmpty()) {
            throw new Exception404("유효하지 않은 쿠폰입니다.");
        }
        if (usercouponOP.get().getUser().getId() != userId) {
            throw new Exception404("유효하지 않은 쿠폰입니다.");
        } else {
            couponReduceAmount = usercouponOP.get().getCoupon().getReduceAmount();
        }

        for (SelectedCartProductDTO selectedCartProductDTO : selectedCartProducts) {
            Option option = optionJPARepository.findById(selectedCartProductDTO.getOptionId()).get();
            Integer optionPrice = option.getOptionPrice();
            Integer optionQuantity = selectedCartProductDTO.getOptionQuantity();
            Integer discountRate = option.getProduct().getDiscountRate();
            Integer optionDiscountedPrice = (Math.round(optionPrice * (100 - discountRate) / 1000) * 10)
                    * optionQuantity;

            DBPrice = DBPrice + optionDiscountedPrice;
        }

        if (DBPrice >= 20000) {
            DBDeliveryFee = 0;
        }

        System.out.println("DBTotalPrice : " + (DBPrice + DBDeliveryFee - couponReduceAmount));
        System.out.println("CartTotalPrice: " + selectedCartListDTO.getFinalPrice());
        Boolean isSameprice = ((DBPrice + DBDeliveryFee - couponReduceAmount) == selectedCartListDTO.getFinalPrice());

        if (!isSameprice) {
            throw new Exception404("요청금액이 맞지 않습니다.");
        }
    }

    public void 장바구니담기(CartSaveDTO cartSaveDTO, Integer userId) {
        List<SelectedOptionDTO> SelectedOptionDTOs = cartSaveDTO.getSelectedOptionDTOs();
        for (SelectedOptionDTO selectedOptionDTO : SelectedOptionDTOs) {

            Option option = optionJPARepository.findById(selectedOptionDTO.getOptionId()).get();
            User user = userJPARepository.findById(userId).get();
            Cart cart = Cart.builder()
                    .option(option)
                    .optionQuantity(selectedOptionDTO.getOptionQuantity())
                    .user(user)
                    .build();

            cartJPARepository.save(cart);
        }
    }

    @Transactional
    public void 장바구니삭제(Integer cartId) {
        cartJPARepository.deleteById(cartId);
    }

    @Transactional
    public void 장바구니선택삭제(CartDeleteListDTO cartDeleteListDTO, Integer userId) {
        for (Integer cartId : cartDeleteListDTO.getCartDeleteList()) {
            cartJPARepository.deleteByIdAndUserId(cartId, userId);
        }
    }
}
