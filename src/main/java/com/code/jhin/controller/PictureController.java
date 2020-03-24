package com.code.jhin.controller;

import com.code.jhin.model.product.Picture;
import com.code.jhin.playLoad.response.ApiResponse;
import com.code.jhin.service.productService.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
