package com.code.jhin.service.productServiceImpl;

import com.code.jhin.model.product.Picture;
import com.code.jhin.repository.productRepository.PictureRepository;
import com.code.jhin.service.productService.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureRepository pictureRepository;

    @Override
    public void findByAllProductId(Long id) {
         pictureRepository.findAllByProduct(id);
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
