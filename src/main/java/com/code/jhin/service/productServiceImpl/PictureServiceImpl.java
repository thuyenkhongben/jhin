package com.code.jhin.service.productServiceImpl;

import com.code.jhin.model.product.Picture;
import com.code.jhin.repository.productRepository.PictureRepository;
import com.code.jhin.repository.productRepository.ProductRepository;
import com.code.jhin.service.productService.PictureService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureRepository pictureRepository;

    @Override
    public List<Picture> findByAllProductId(Long id) {
        return pictureRepository.findAllByProduct(id);
    }

    @Override
    public List<Picture> findAllPicture() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findByIdPicture(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void remove(Long id) {
        pictureRepository.deleteById(id);
    }
}
