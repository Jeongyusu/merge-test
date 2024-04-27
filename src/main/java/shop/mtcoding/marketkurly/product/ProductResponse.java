package shop.mtcoding.marketkurly.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.marketkurly.option.Option;

public class ProductResponse {

    public static Double starCountRound(Double starCount) {
        if (starCount > 4.75) {
            return 5.0;
        }
        if (4.75 >= starCount && starCount > 4.25) {
            return 4.5;
        }
        if (4.25 >= starCount && starCount > 3.75) {
            return 4.0;
        }
        if (3.75 >= starCount && starCount > 3.25) {
            return 3.5;
        }
        if (3.25 >= starCount && starCount > 2.75) {
            return 3.0;
        }
        if (2.75 >= starCount && starCount > 2.25) {
            return 2.5;
        }
        if (2.25 >= starCount && starCount > 1.75) {
            return 2.0;
        }
        if (1.75 >= starCount && starCount > 1.25) {
            return 1.5;
        }
        if (1.25 >= starCount && starCount > 0.75) {
            return 1.0;
        }
        if (0.75 >= starCount && starCount > 0.25) {
            return 0.5;
        }
        return 0.0;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductDetailDTO {
        private int productId;
        private String productName;
        private String productContent;
        private String productThumbnail;
        private Integer discountRate;
        private Integer discountedPrice;
        private Integer originPrice;
        private String productOrigin;
        private String productDetailImage;
        private String seller;
        private List<Option> options;

        public ProductDetailDTO(Product product) {
            this.productId = product.getId();
            this.productName = product.getProductName();
            this.productContent = product.getProductContent();
            this.productThumbnail = product.getProductThumbnail();
            this.discountRate = product.getDiscountRate();
            this.originPrice = product.getOptions().stream()
                    .map(Option::getOptionPrice)
                    .min(Integer::compareTo)
                    .orElse(0);
            this.discountedPrice = Math.round(originPrice * (100 - product.getDiscountRate()) / 1000) * 10;
            this.productOrigin = product.getProductOrigin();
            this.productDetailImage = product.getProductDetailPic();
            this.seller = product.getSeller().getUsername();
            this.options = product.getOptions();
        }
    }

    @ToString
    @Getter
    @NoArgsConstructor
    public static class ProductListDTO {
        List<ProductSummary> result = new ArrayList<>();
        private int totalCount;

        public ProductListDTO(int totalCount, List<ProductSummary> result) {
            this.totalCount = totalCount;
            this.result = result;
        }

    }

    @ToString
    @Getter
    public static class ProductSummary {
        Integer productId;
        String productThumnail;
        String productName;
        String sellerName;
        Integer originPrice;
        Integer discountRate;
        Integer discountedPrice;
        Integer categoryId;
        Double averageStarCount;

        public ProductSummary(Product product, Double averageStarCount, Option option) {
            this.productId = product.getId();
            this.productThumnail = product.getProductThumbnail();
            this.productName = product.getProductName();
            this.sellerName = product.getSeller().getUsername();
            this.originPrice = option.getOptionPrice();
            this.discountRate = product.getDiscountRate();
            this.discountedPrice = Math.round(originPrice * (100 - product.getDiscountRate()) / 1000) * 10;
            this.categoryId = product.getCategory().getId();
            this.averageStarCount = starCountRound(averageStarCount);
        }
    }

    @ToString
    @Getter
    public static class SellerProductListDTO {

        private List<SellerProductDTO> sellerProductDTOs;

        public SellerProductListDTO(List<Product> products) {
            this.sellerProductDTOs = products.stream().map(t -> new SellerProductDTO(t)).collect(Collectors.toList());
        }

        @ToString
        @Getter
        public static class SellerProductDTO {
            private int productId;
            private String productName;
            private Integer categoryId;
            private String categoryType;
            private Integer discountRate;
            private LocalDate discountExpiredAt;
            private LocalDate productUploadedAt;

            public SellerProductDTO(Product product) {
                this.productId = product.getId();
                this.productName = product.getProductName();
                this.categoryId = product.getCategory().getId();
                this.categoryType = product.getCategory().getCategoryType();
                this.discountExpiredAt = product.getDiscountExpiredAt();
                this.productUploadedAt = product.getProductUploadedAt();
                this.discountRate = product.getDiscountRate();
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ProductDetailListDTO {

        private Product product;
        private List<OptionDTO> optionDTOs;

        public ProductDetailListDTO(List<Option> Options) {
            this.product = Options.get(0).getProduct();
            this.optionDTOs = Options.stream().map(t -> new OptionDTO(t))
                    .collect(Collectors.toList());
        }

        @ToString
        @Setter
        @Getter
        public static class OptionDTO {
            private Integer optionId;
            private String optionName;
            private Integer optionPrice;
            private Integer optionStack;

            public OptionDTO(Option option) {
                this.optionId = option.getId();
                this.optionName = option.getOptionName();
                this.optionPrice = option.getOptionPrice();
                this.optionStack = option.getOptionStack();
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class ProductMainListsDTO {

        List<ProductStarMainDTO> productStarMainDTOs;
        List<ProductDiscountMainDTO> productDiscountMainDTOs;
        List<ProductRandomMainDTO> productRandomMainDTOs;

        public ProductMainListsDTO(List<ProductStarDTO> productStarDTOs,
                List<ProductDiscountDTO> productDiscountDTOs, List<ProductRandomDTO> productRandomDTOs) {
            this.productStarMainDTOs = productStarDTOs.stream()
                    .map(t -> new ProductStarMainDTO(t.getProduct(), t.getAvgStarCount()))
                    .collect(Collectors.toList());
            this.productDiscountMainDTOs = productDiscountDTOs.stream()
                    .map(t -> new ProductDiscountMainDTO(t.getProduct(), t.getAvgStarCount()))
                    .collect(Collectors.toList());
            this.productRandomMainDTOs = productRandomDTOs.stream()
                    .map(t -> new ProductRandomMainDTO(t.getProduct(), t.getAvgStarCount()))
                    .collect(Collectors.toList());
        }

        @ToString
        @Setter
        @Getter
        public static class ProductStarMainDTO {
            private Integer productId;
            private String sellerName;
            private String productName;
            private String productThumbnail;
            private Integer minOptionPrice;
            private Integer discountedminOptionPrice;
            private Integer discountRate;
            private Integer categoryId;
            private Double avgStarCount;

            public ProductStarMainDTO(Product product, Double starCount) {
                this.productId = product.getId();
                this.sellerName = product.getSeller().getUsername();
                this.productName = product.getProductName();
                this.productThumbnail = product.getProductThumbnail();
                this.minOptionPrice = product.getOptions().stream()
                        .mapToInt(Option::getOptionPrice)
                        .min()
                        .orElse(0);
                this.discountRate = product.getDiscountRate();
                this.discountedminOptionPrice = Math.round(minOptionPrice * (100 - discountRate) / 1000) * 10;
                this.categoryId = product.getCategory().getId();
                this.avgStarCount = starCountRound(starCount);
            }

        }

        @ToString
        @Setter
        @Getter
        public static class ProductDiscountMainDTO {
            private Integer productId;
            private String sellerName;
            private String productName;
            private String productThumbnail;
            private Integer minOptionPrice;
            private Integer discountedminOptionPrice;
            private Integer discountRate;
            private Integer categoryId;
            private Double avgStarCount;

            public ProductDiscountMainDTO(Product product, Double starCount) {
                this.productId = product.getId();
                this.sellerName = product.getSeller().getUsername();
                this.productName = product.getProductName();
                this.productThumbnail = product.getProductThumbnail();
                this.minOptionPrice = product.getOptions().stream()
                        .mapToInt(Option::getOptionPrice)
                        .min()
                        .orElse(0);
                this.discountRate = product.getDiscountRate();
                this.discountedminOptionPrice = Math.round(minOptionPrice * (100 - discountRate) / 1000) * 10;
                this.categoryId = product.getCategory().getId();
                this.avgStarCount = starCountRound(starCount);
            }
        }

        @ToString
        @Setter
        @Getter
        public static class ProductRandomMainDTO {
            private Integer productId;
            private String sellerName;
            private String productName;
            private String productThumbnail;
            private Integer minOptionPrice;
            private Integer discountedminOptionPrice;
            private Integer discountRate;
            private Integer categoryId;
            private Double avgStarCount;

            public ProductRandomMainDTO(Product product, Double starCount) {
                this.productId = product.getId();
                this.sellerName = product.getSeller().getUsername();
                this.productName = product.getProductName();
                this.productThumbnail = product.getProductThumbnail();
                this.minOptionPrice = product.getOptions().stream()
                        .mapToInt(Option::getOptionPrice)
                        .min()
                        .orElse(0);
                this.discountRate = product.getDiscountRate();
                this.discountedminOptionPrice = Math.round(minOptionPrice * (100 - discountRate) / 1000) * 10;
                this.categoryId = product.getCategory().getId();
                this.avgStarCount = starCountRound(starCount);
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class SearchListDTO {

        List<ProductSearchDTO> productSearchDTOS;

        public SearchListDTO(List<ProductStarDTO> productStarDTOs) {
            this.productSearchDTOS = productStarDTOs.stream()
                    .map(t -> new ProductSearchDTO(t.getProduct(), t.getAvgStarCount()))
                    .collect(Collectors.toList());
        }

        @ToString
        @Setter
        @Getter
        public static class ProductSearchDTO {
            private Integer productId;
            private String sellerName;
            private String productName;
            private String productThumbnail;
            private Integer minOptionPrice;
            private Integer discountedminOptionPrice;
            private Integer discountRate;
            private Integer categoryId;
            private Double avgStarCount;

            public ProductSearchDTO(Product product, Double starCount) {
                this.productId = product.getId();
                this.sellerName = product.getSeller().getUsername();
                this.productName = product.getProductName();
                this.productThumbnail = product.getProductThumbnail();
                this.minOptionPrice = product.getOptions().stream()
                        .mapToInt(Option::getOptionPrice)
                        .min()
                        .orElse(0);
                this.discountRate = product.getDiscountRate();
                this.discountedminOptionPrice = Math.round(minOptionPrice * (100 - discountRate) / 1000) * 10;
                this.categoryId = product.getCategory().getId();
                this.avgStarCount = starCountRound(starCount);
            }

        }

    }
}

@Getter
@Setter
@NoArgsConstructor
class ProductStarDTO {
    private Product product;
    private Double avgStarCount;

    public ProductStarDTO(Product product, Double avgStarCount) {
        this.product = product;
        this.avgStarCount = avgStarCount;
    }
}

@Getter
@Setter
@NoArgsConstructor
class ProductDiscountDTO {
    private Product product;
    private Double avgStarCount;

    public ProductDiscountDTO(Product product, Double avgStarCount) {
        this.product = product;
        this.avgStarCount = avgStarCount;
    }
}

@Getter
@Setter
@NoArgsConstructor
class ProductRandomDTO {
    private Product product;
    private Double avgStarCount;

    public ProductRandomDTO(Product product, Double avgStarCount) {
        this.product = product;
        this.avgStarCount = avgStarCount;
    }
}
