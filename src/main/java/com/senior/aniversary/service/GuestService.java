package com.senior.aniversary.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senior.aniversary.entities.Guest;
import com.senior.aniversary.repositories.GuestRepository;

@Service
public class GuestService {

    @Autowired
    private GuestRepository repository;
    
    // @Transactional(readOnly = true)
    // public Map<String, Object> all(){
    //     Map<String,Object> response = new LinkedHashMap<>();
    //     response.put("status","sucess");
    //     List<Guest> data = repository.findAll();
    //     List<Object> guests = new ArrayList<>();
    //     data.stream().forEach(t -> {
    //         guests.add(t);
    //     });
    //     response.put("data", guests);
    //     return response;
    // }

    @Transactional(readOnly = true)
    public Map<String, Object> all(
            String name,
            String type,
            LocalDate dateRegister,
            LocalDateTime dateInncome,
            Integer quantity,
            String food,
            String drink) {

        Map<String,Object> response = new LinkedHashMap<>();

        Specification<Guest> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (type != null && !type.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), type));
        }
        if (dateRegister != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dateRegister"), dateRegister));
        }
        if (dateInncome != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("dateInncome"), dateInncome));
        }
        if (quantity != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("quantity"), quantity));
        }
        if (food != null && !food.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("food"), food));
        }
        if (drink != null && !drink.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("drink"), drink));
        }

        List<Guest> data = repository.findAll(spec);

        response.put("status","success");
        response.put("data", data);

        return response;
    }


    @Transactional
    public Guest newGuest(Guest guest){
        return repository.save(guest);
    }

    @Transactional
    public Map<String, Object> deleteGuest(Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (repository.existsById(id)) {
            repository.deleteById(id);
            response.put("status", "success");
            response.put("message", "Guest eliminado correctamente");
        } else {
            response.put("status", "error");
            response.put("message", "Guest con id " + id + " no encontrado");
        }
        return response;
    }    

    @Transactional
    public Map<String, Object> updateGuest(Long id, Guest guestDetails) {
        Map<String, Object> response = new LinkedHashMap<>();
        
        return repository.findById(id).map(guest -> {
            // 🔹 actualizar campos (ajusta a lo que tengas en tu entidad Guest)
            guest.setName(guestDetails.getName());
            guest.setType(guestDetails.getType());
            guest.setDateRegister(guestDetails.getDateRegister());
            guest.setDateInncome(guestDetails.getDateInncome());
            guest.setQuantity(guestDetails.getQuantity());
            guest.setFood(guestDetails.getFood());
            guest.setDrink(guestDetails.getDrink());

            Guest updated = repository.save(guest);
            
            response.put("status", "success");
            response.put("data", updated);
            return response;
        }).orElseGet(() -> {
            response.put("status", "error");
            response.put("message", "Guest con id " + id + " no encontrado");
            return response;
        });
    }
}
