package shop.mtcoding.marketkurly.waitingproduct;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.errors.exception.Exception500;
import shop.mtcoding.marketkurly._core.vo.MyPath;
import shop.mtcoding.marketkurly.category.Category;
import shop.mtcoding.marketkurly.category.CategoryJPARepository;
import shop.mtcoding.marketkurly.option.Option;
import shop.mtcoding.marketkurly.option.OptionJPARepository;
import shop.mtcoding.marketkurly.product.ProdcutJPARepository;
import shop.mtcoding.marketkurly.product.Product;
import shop.mtcoding.marketkurly.user.User;
import shop.mtcoding.marketkurly.user.UserJPARepository;
import shop.mtcoding.marketkurly.waitingoption.WaitingOption;
import shop.mtcoding.marketkurly.waitingoption.WaitingOptionJPARepository;
import shop.mtcoding.marketkurly.waitingoption.WaitingOptionRequest.WOptionSaveDTO;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProductRequest.WProductDTO;
import shop.mtcoding.marketkurly.waitingproduct.WaitingProductResponse.WaitingProductListDTO;

@Service
@RequiredArgsConstructor
public class WaitingProductService {

    private final CategoryJPARepository categoryJPARepository;
    private final WaitingProductJPARepository waitingProductJPARepository;
    private final WaitingOptionJPARepository waitingOptionJPARepository;
    private final UserJPARepository userJPARepository;
    private final ProdcutJPARepository prodcutJPARepository;
    private final OptionJPARepository optionJPARepository;

    @Transactional
    public void 상품승인요청(WProductDTO wProductDTO, Integer userId) {

        UUID uuid = UUID.randomUUID();
        String ProductThumbnailName = uuid + "_" + wProductDTO.getProductThumbnail().getOriginalFilename();
        String ProductDetailPicName = uuid + "_" + wProductDTO.getProductDetailPic().getOriginalFilename();

        // 자바에서 받은 파일을 저장할 경로를 상대경로로 지정(IMG_PATH의 ./는 현재위치를 의미)
        // 현재 경로 Desktop/WS/workspace/project/blogV2
        // 배포 시 해당 실행 파일 경로에 images 폴더가 필요함
        Path ProductThumbnailPath = Paths.get(MyPath.WAITINGPRODUCTTHUMB_PATH + ProductThumbnailName);
        Path ProductDetailPicPath = Paths.get(MyPath.WAITINGPRODUCTDETAIL_PATH + ProductDetailPicName);

        // 파일 쓰기 write(경로, 바이트파일)
        try {
            Files.write(ProductThumbnailPath, wProductDTO.getProductThumbnail().getBytes());
            Files.write(ProductDetailPicPath, wProductDTO.getProductDetailPic().getBytes());
        } catch (Exception e) {
            throw new Exception500("사진 저장 실패");
        }

        Category category = categoryJPARepository.findById(wProductDTO.getCategoryId()).get();
        User user = userJPARepository.findById(userId).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate wDiscountExpiredAt = LocalDate.parse(wProductDTO.getDiscountExpiredAt(), formatter);

        WaitingProduct waitingProduct = WaitingProduct.builder()
                .wProductName(wProductDTO.getProductName())
                .wProductThumbnail((MyPath.WAITINGPRODUCTTHUMB_PATH + ProductThumbnailName).substring(1))
                .wProductDetailPic((MyPath.WAITINGPRODUCTDETAIL_PATH + ProductDetailPicName).substring(1))
                .wProductContent(wProductDTO.getProductContent())
                .wDiscountRate(wProductDTO.getDiscountRate())
                .wDiscountExpiredAt(wDiscountExpiredAt)
                .wProductUploadedAt(new Timestamp(new Date().getTime()))
                .category(category)
                .seller(user)
                .state("대기중")
                .build();

        waitingProductJPARepository.save(waitingProduct);

        ObjectMapper objectMapper = new ObjectMapper();
        List<WOptionSaveDTO> optionDTOList;

        try {
            optionDTOList = objectMapper.readValue(wProductDTO.getOptionList(),
                    new TypeReference<List<WOptionSaveDTO>>() {
                    });
        } catch (JsonProcessingException e) {
            throw new Exception500("WOptionSaveDTO Json파싱 실패");
        }

        for (WOptionSaveDTO wOptionSaveDTO : optionDTOList) {

            WaitingOption waitingOption = WaitingOption.builder()
                    .wOptionName(wOptionSaveDTO.getOptionName())
                    .wOptionPrice(wOptionSaveDTO.getOptionPrice())
                    .wOptionStack(wOptionSaveDTO.getOptionStack())
                    .waitingProduct(waitingProduct)
                    .build();

            waitingOptionJPARepository.save(waitingOption);
        }

    }

    public WaitingProductListDTO 대기상품목록(Integer userId) {
        List<WaitingProduct> waitingProducts = waitingProductJPARepository.findBySellerId(userId);
        return new WaitingProductListDTO(waitingProducts);
    }

    public List<WaitingProduct> 대기상품전체() {
        String state = "대기중";
        List<WaitingProduct> waitingProducts = waitingProductJPARepository.findByState(state);
        return waitingProducts;
    }

    @Transactional
    public void 상품승인(Integer wProductId) {

        WaitingProduct waitingProduct = waitingProductJPARepository.findById(wProductId).get();

        Integer wthumbIndex = waitingProduct.getWProductThumbnail().lastIndexOf("/") + 1;
        Integer wDetailInedex = waitingProduct.getWProductDetailPic().lastIndexOf("/") + 1;

        String wthumbFileName = waitingProduct.getWProductThumbnail().substring(wthumbIndex);
        String wDetailFileName = waitingProduct.getWProductDetailPic().substring(wDetailInedex);

        Path ProductThumbnailPath = Paths.get("." + waitingProduct.getWProductThumbnail());
        Path ProductDetailPicPath = Paths.get("." + waitingProduct.getWProductDetailPic());

        Path newThumbnailPath = Paths.get(MyPath.PRODUCTTHUMB_PATH + wthumbFileName);
        Path newDetailPicPath = Paths.get(MyPath.PRODUCTDETAIL_PATH + wDetailFileName);

        try {
            // 원본 파일을 대상 파일로 복사
            Files.copy(ProductThumbnailPath, newThumbnailPath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(ProductDetailPicPath, newDetailPicPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new Exception500("사진 이동 및 삭제 실패");
        }

        Product product = Product.builder()
                .productName(waitingProduct.getWProductName())
                .productContent(waitingProduct.getWProductContent())
                .discountExpiredAt(waitingProduct.getWDiscountExpiredAt())
                .discountRate(waitingProduct.getWDiscountRate())
                .category(waitingProduct.getCategory())
                .seller(waitingProduct.getSeller())
                .productThumbnail((MyPath.PRODUCTTHUMB_PATH + wthumbFileName).substring(1))
                .productDetailPic((MyPath.PRODUCTDETAIL_PATH + wDetailFileName).substring(1))
                .productUploadedAt(waitingProduct.getWProductUploadedAt().toLocalDateTime().toLocalDate())
                .productOrigin("상품설명/상세정보 참고")
                .build();

        prodcutJPARepository.save(product);
        List<WaitingOption> waitingOptions = waitingOptionJPARepository.findByWaitingProductId(wProductId);

        for (WaitingOption waitingOption : waitingOptions) {
            Option option = Option.builder()
                    .optionName(waitingOption.getWOptionName())
                    .optionStack(waitingOption.getWOptionStack())
                    .optionPrice(waitingOption.getWOptionPrice())
                    .product(product)
                    .build();
            optionJPARepository.save(option);
            waitingOptionJPARepository.delete(waitingOption);
        }

        waitingProductJPARepository.deleteById(wProductId);

    }

    @Transactional
    public void 상품거절(Integer wProductId) {

        waitingProductJPARepository.rejectProduct(wProductId);
    }
}
