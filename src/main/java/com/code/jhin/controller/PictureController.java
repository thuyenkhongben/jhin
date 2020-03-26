package com.code.jhin.controller;

import com.code.jhin.model.product.Picture;
import com.code.jhin.playLoad.response.ApiResponse;
import com.code.jhin.service.productService.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth/picture")
public class PictureController {
    @Autowired
    PictureService pictureService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPicture(@RequestBody Picture picture) {
        try {
            pictureService.save(picture);
            return new  ResponseEntity <ApiResponse> (
                    new ApiResponse( true , " create Picture successfully !" , null) ,
            HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<Picture>> listPicture() {
        List<Picture> pictures  = pictureService.findAllPicture();
        if (pictures.isEmpty()) {
            return new ResponseEntity<List<Picture>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Picture>>(pictures,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPicture ( @PathVariable Long id) {
        Optional<Picture> picture  = pictureService.findByIdPicture(id);

        if (picture.isPresent()) {
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse( false , "Fail. Not found data" , null), HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(true, "successfully" , picture), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Picture> updatePicture ( @PathVariable Long id , @RequestBody Picture picture) {
        Optional<Picture> picture1 = pictureService.findByIdPicture(id);

        if (picture1.isPresent()) {
            picture1.get().setId(picture.getId());
            picture1.get().setNamePicture(picture.getNamePicture());
            picture1.get().setProduct(picture.getProduct());


            pictureService.save(picture1.get());

            return new  ResponseEntity<Picture> (
                    picture1.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Picture>(
                 HttpStatus.NOT_FOUND);
    }
}
