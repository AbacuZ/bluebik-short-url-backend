package com.bluebik.shorturl.service;

import com.bluebik.shorturl.dao.jpa.ShorturlRepository;
import org.springframework.stereotype.Service;
import com.bluebik.shorturl.domain.SuccessResponse;
import com.bluebik.shorturl.domain.ShortUrl;
import com.bluebik.shorturl.exception.URLNotFoundException;
import com.bluebik.shorturl.exception.URLValidException;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class ShortUrlService {

    @Autowired
    private ShorturlRepository shorturlRepository;
    
    public SuccessResponse findAll() {
        List<ShortUrl> shortUrlResult = shorturlRepository.findAll();
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, shortUrlResult);
    }
    
    public SuccessResponse createURL(ShortUrl url) {
        UrlValidator validator = new UrlValidator(new String[]{"http", "https"});

        if (!validator.isValid(url.getUrl())) {
            throw new URLValidException("Invalid URL");
        }
        
        String id = Hashing.murmur3_32().hashString(url.getUrl(), Charset.defaultCharset()).toString();
        
        ShortUrl shorturlResult;
        ShortUrl shortUrlFound = shorturlRepository.findById(id).get();
        if (shortUrlFound == null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ShortUrl shorturl = new ShortUrl();
            shorturl.setId(id);
            shorturl.setStaticClick(0);
            shorturl.setUrl(url.getUrl());
            shorturl.setCreatedDatetime(timestamp);
            shorturlResult = shorturlRepository.save(shorturl);
        } else {
            shorturlResult = shortUrlFound;
        }
        
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, shorturlResult);
    }
    
    public String getURL(String id) {
        ShortUrl shortUrl = shorturlRepository.findById(id).get();
        
        if (shortUrl == null) {
            throw new URLNotFoundException("URL Not Found");
        }
        
        return shortUrl.getUrl();
    }
    
    public void updateStaticClick(String id) {
        shorturlRepository.updateClick(id);
    }
}
