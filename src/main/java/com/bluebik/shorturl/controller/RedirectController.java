package com.bluebik.shorturl.controller;

import com.bluebik.shorturl.service.ShortUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
@Api(tags = {"Redirect Controller"})
public class RedirectController {

    @Autowired
    private ShortUrlService shortUrlservice;
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Redirect url by id", notes = "")
    public void redirect(
            @ApiParam(value = "id", required = true)
            @PathVariable String id,
            HttpServletResponse response
    ) throws IOException {
        String url = shortUrlservice.getURL(id);
        shortUrlservice.updateStaticClick(id);
        response.sendRedirect(url);
    }
}
