package shop.mtcoding.marketkurly.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.marketkurly._core.utils.ApiUtils;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping("/api/categorys")
    public ResponseEntity<?> 모든카테고리찾기() {
        List<Category> categorys = categoryService.모든카테고리찾기();
        return ResponseEntity.ok().body(ApiUtils.success(categorys));
    }

}
