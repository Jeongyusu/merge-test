package shop.mtcoding.marketkurly.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJPARepository categoryRepository;

    public List<Category> 모든카테고리찾기() {
        List<Category> categorys = categoryRepository.findAll();
        return categorys;
    }
}
