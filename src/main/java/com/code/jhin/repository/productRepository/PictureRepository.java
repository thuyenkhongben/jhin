package com.code.jhin.repository.productRepository;

import com.code.jhin.model.product.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface PictureRepository extends JpaRepository<Picture, Long> {


    @Modifying
    @Query(value = "DELETE FROM picture  where product_id =?1" , nativeQuery = true)
    void findAllByProduct(Long id);
}
