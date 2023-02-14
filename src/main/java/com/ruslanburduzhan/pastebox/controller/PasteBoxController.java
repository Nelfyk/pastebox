package com.ruslanburduzhan.pastebox.controller;

import com.ruslanburduzhan.pastebox.api.request.PasteBoxRequest;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxResponse;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxUrlResponse;
import com.ruslanburduzhan.pastebox.service.PasteBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService service;

    @GetMapping("/")
    public List<PasteBoxResponse> getAllPublic() {
        return service.getAllPublic();
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash) {

        PasteBoxResponse response;
        try {
            response = service.getByHash(hash);
        } catch (RuntimeException e) {
            response = new PasteBoxResponse("not found", false, "not found");
        }
        return response;
    }

    @PostMapping("/")
    public PasteBoxUrlResponse addPaste(@RequestBody PasteBoxRequest request) {
        return service.addPaste(request);
    }

}
