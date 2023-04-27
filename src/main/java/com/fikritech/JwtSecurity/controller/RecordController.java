package com.fikritech.JwtSecurity.controller;

import com.fikritech.JwtSecurity.dto.RecordRequest;
import com.fikritech.JwtSecurity.entity.Record;
import com.fikritech.JwtSecurity.entity.User;
import com.fikritech.JwtSecurity.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService service;

    @GetMapping("/getall")
    public ResponseEntity<List<Record>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllByCreatedBy(user.getEmail()));
    }

    @PostMapping("/save")
    public ResponseEntity<Record> save(@RequestBody RecordRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request, user.getEmail()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Record> updateById(@RequestBody RecordRequest request, @PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateById(request, id, user.getEmail()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteById(id, user.getEmail()));
    }

}
