package dev.JpDev17.movieflix.mapper;

import dev.JpDev17.movieflix.entities.Category;
import dev.JpDev17.movieflix.request.CategoryRequest;
import dev.JpDev17.movieflix.response.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        return category;
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
