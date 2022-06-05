package com.epam.esm.controllers;

import com.epam.esm.DTO.TagDTO;
import com.epam.esm.services.tag.TagServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Tag controller
 */

@RestController
@RequestMapping(value = "api/v1/tags")
public class TagController {

    private final TagServiceImpl tagServiceImpl;

    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    /**
     * Create Tag
     *
     * @param tagDTO -> Tag DTO
     * @return Response DTO
     */

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TagDTO tagDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagServiceImpl.create(tagDTO));
    }

    /**
     * Update Tag
     *
     * @param id     - ID
     * @param tagDTO - Tag DTO
     * @return Response DTO
     */

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody TagDTO tagDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(tagServiceImpl.update(id, tagDTO));
    }

    /**
     * Delete Tag
     *
     * @param id - ID
     * @return Response DTO
     */

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tagServiceImpl.delete(id));
    }

    /**
     * Get Tag
     *
     * @param id - ID
     * @return Response DTO
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tagServiceImpl.get(id));
    }

    /**
     * Get all Tag
     *
     * @return Response DTO
     */

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tagServiceImpl.getAll());
    }

}
