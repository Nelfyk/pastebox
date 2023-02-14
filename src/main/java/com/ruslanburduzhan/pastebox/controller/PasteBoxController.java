package com.ruslanburduzhan.pastebox.controller;

import com.ruslanburduzhan.pastebox.api.request.PasteBoxRequest;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxResponse;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxUrlResponse;
import com.ruslanburduzhan.pastebox.service.PasteBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService service;

    @GetMapping("/")
    public Collection<String> getAllPublic() {
        return Collections.emptyList();
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {

        PasteBoxResponse response;
        try{
            response = service.getByHash(hash);
        } catch(RuntimeException e){
            response = new PasteBoxResponse("not found",false);
        }
        return response;
    }

    @PostMapping("/")
    public PasteBoxUrlResponse addPaste(@RequestBody PasteBoxRequest request){
        return service.addPaste(request);
    }

}
