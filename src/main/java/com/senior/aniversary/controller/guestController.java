package com.senior.aniversary.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.senior.aniversary.entities.Guest;
import com.senior.aniversary.service.GuestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class guestController {
    
    @Autowired
    private GuestService service;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> all(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRegister,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateInncome,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String food,
            @RequestParam(required = false) String drink
    ) {
        Map<String,Object> response = service.all(name, type, dateRegister, dateInncome, quantity, food, drink);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/new")
    public ResponseEntity<Guest> postMethodName(@RequestBody Guest guest) {
        Guest create = service.newGuest(guest);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteGuest(@PathVariable Long id) {
        Map<String, Object> response = service.deleteGuest(id);
        return ResponseEntity.ok(response);
    }

    // UPDATE Guest por ID
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateGuest(
            @PathVariable Long id,
            @RequestBody Guest guestDetails) {
        Map<String, Object> response = service.updateGuest(id, guestDetails);
        return ResponseEntity.ok(response);
    }    
}
