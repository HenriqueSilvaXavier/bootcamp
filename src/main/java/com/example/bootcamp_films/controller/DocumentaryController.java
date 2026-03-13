package com.example.bootcamp_films.controller;

import com.example.bootcamp_films.entity.Documentary;
import com.example.bootcamp_films.service.DocumentaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentaries")
@CrossOrigin("*")
public class DocumentaryController extends BaseController<Documentary> {

    public DocumentaryController(DocumentaryService service) {
        super(service);
    }
}