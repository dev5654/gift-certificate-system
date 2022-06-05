package com.epam.esm.controllers;

import com.epam.esm.DTO.GiftCertificateDTO;
import com.epam.esm.DTO.response.ResponseDTO;
import com.epam.esm.services.gift_certificate.GiftCertificateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Muslim Aktamov
 * @project Rest API basic
 * Gift certificate controller
 */

@RestController
@RequestMapping("/api/v1/certificates")
public class GiftCertificateController {

    private final GiftCertificateServiceImpl giftCertificateServiceImpl;

    public GiftCertificateController(GiftCertificateServiceImpl giftCertificateServiceImpl) {
        this.giftCertificateServiceImpl = giftCertificateServiceImpl;
    }

    /**
     * Create Gift Certificate
     * @param giftCertificateDTO -> Gift Certificate DTO
     * @return Response DTO
     */

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(giftCertificateServiceImpl.create(giftCertificateDTO));
    }

    /**
     * Update Gift Certificate
     * @param id - ID
     * @param giftCertificateDTO - Gift Certificate DTO
     * @return Response DTO
     */

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable UUID id, @RequestBody GiftCertificateDTO giftCertificateDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(giftCertificateServiceImpl.update(id, giftCertificateDTO));
    }

    /**
     * Delete Gift Certificate
     * @param id - ID
     * @return Response DTO
     */

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(giftCertificateServiceImpl.delete(id));
    }

    /**
     * Get Gift Certificate
     * @param id - ID
     * @return Response DTO
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(giftCertificateServiceImpl.get(id));
    }

    /**
     * Get all Gift Certificate
     * @return Response DTO
     */

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(giftCertificateServiceImpl.getAll());
    }

    /**
     * Filter Gift Certificate
     * @param name - name
     * @param description - description
     * @param tag - tag
     * @param sortParams - sort parameters
     * @return Response DTO
     */

    @GetMapping("/filter")
    public ResponseEntity<ResponseDTO> findCertificates(@RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "description", required = false) String description,
                                                  @RequestParam(value = "tag", required = false) String tag,
                                                  @RequestParam(value = "sortParams", required = false) String sortParams) {
        ResponseDTO responseDTO = giftCertificateServiceImpl.getWithParams(name, description, tag, sortParams);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);
    }

}
