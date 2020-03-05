package com.code.jhin.service.productService;

import com.code.jhin.model.product.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureService {
   List<Picture> findByAllProductId(Long id);

    List<Picture> findAllPicture();

    Optional<Picture> findByIdPicture(Long id);

    void save(Picture picture);

    void remove( Long id);
}
