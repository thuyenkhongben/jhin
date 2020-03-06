package com.code.jhin.repository.productRepository;

import com.code.jhin.model.product.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture , Long> {
    List<Picture> findAllByProduct(Long id);
}
