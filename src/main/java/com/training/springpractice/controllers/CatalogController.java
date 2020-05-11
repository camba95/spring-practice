package com.training.springpractice.controllers;

import com.training.springpractice.models.Catalog;
import com.training.springpractice.models.CatalogBody;
import com.training.springpractice.repositories.CatalogRepository;
import com.training.springpractice.services.CatalogService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/catalog-entry")
public class CatalogController {

    @Autowired
    private CatalogService service;

    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CatalogBody body) {
        try {
            Catalog entity = convertToEntity(body);
            Catalog createdCatalog = service.create(entity);
            CatalogBody response = convertToDto(createdCatalog);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            RuntimeException apiError = new RuntimeException(e.getMessage());
            return new ResponseEntity(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @Autowired
    public void setMapper( ModelMapper mapper) {
        this.mapper = mapper;
        this.mapper.addMappings(new PropertyMap<CatalogBody, Catalog>() {
            @Override
            protected void configure() {
                map().setMovieId(source.getMovie_id());
                map().setPriceId(source.getPrice_id());
            }
        });
        this.mapper.addMappings(new PropertyMap<Catalog, CatalogBody>() {
            @Override
            protected void configure() {
                map().setMovie_id(source.getMovie().getId());
                map().setPrice_id(source.getPrice().getId());
            }
        });
    }

    private Catalog convertToEntity(CatalogBody body) {
        return mapper.map(body, Catalog.class);
    }

    private CatalogBody convertToDto(Catalog catalog) {
        return mapper.map(catalog, CatalogBody.class);
    }
}
