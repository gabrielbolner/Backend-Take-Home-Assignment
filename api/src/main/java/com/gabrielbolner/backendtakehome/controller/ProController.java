package com.gabrielbolner.backendtakehome.controller;

import com.gabrielbolner.backendtakehome.controller.request.ProRequest;
import com.gabrielbolner.backendtakehome.controller.response.ProResponse;
import com.gabrielbolner.backendtakehome.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pros")
public class ProController {

    @Autowired
    private ProService proService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyAsPro(@RequestBody ProRequest proRequest) {
        try {
            ProResponse proResponse = proService.applyAsPro(proRequest);
            return ResponseEntity.ok(proResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
