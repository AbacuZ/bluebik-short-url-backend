package com.bluebik.shorturl.controller;

import com.bluebik.shorturl.service.ShortUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bluebik.shorturl.domain.ShortUrl;

@RestController
@RequestMapping("/api/v1/rest/url")
@Api(tags = {"Short Url Controller"})
public class ShortUrlController {
    
    @Autowired
    private ShortUrlService shorturlService;
    
    @PostMapping
    @ApiOperation(value = "Create short url", notes = "")
    public ResponseEntity createURL(
            @ApiParam(value = "url", required = true) 
            @RequestBody ShortUrl url) {
        return new ResponseEntity<>(shorturlService.createURL(url), HttpStatus.OK);
    }
}
