package com.bluebik.shorturl.service;

import com.bluebik.shorturl.dao.jpa.ShorturlRepository;
import org.springframework.stereotype.Service;
import com.bluebik.shorturl.domain.SuccessResponse;
import com.bluebik.shorturl.domain.ShortUrl;
import com.bluebik.shorturl.exception.URLValidException;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class ShortUrlService {

    @Autowired
    private ShorturlRepository shorturlRepository;
    
    public SuccessResponse createURL(ShortUrl url) {
        UrlValidator validator = new UrlValidator(new String[]{"http", "https"});

        if (!validator.isValid(url.getUrl())) {
            throw new URLValidException("Invalid URL");
        }
        
        String id = Hashing.murmur3_32().hashString(url.getUrl(), Charset.defaultCharset()).toString();
        ShortUrl shorturl = new ShortUrl();
        shorturl.setId(id);
        shorturl.setStaticClick(0);
        shorturl.setUrl(url.getUrl());
        ShortUrl shorturlResult = shorturlRepository.save(shorturl);
        
        List<String> list = Arrays.asList();
        HttpStatus status = HttpStatus.OK;
        return new SuccessResponse(status.getReasonPhrase(), status.value(), list, shorturlResult);
    }
}