package com.example.Java_Jpa.controller;

import com.example.Java_Jpa.dto.HardwareDTO;
import com.example.Java_Jpa.service.HardwareService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware")
@AllArgsConstructor
public class HardwareController {

    private HardwareService hardwareService;

    @GetMapping
    public ResponseEntity<List<HardwareDTO>> getAllHardware() {
        return ResponseEntity.ok(hardwareService.getAllHardware().stream().toList());
    }

    @GetMapping("/{hardwareName}")
    public ResponseEntity<List<HardwareDTO>> filterHardwareByName(@PathVariable String hardwareName) {
        return ResponseEntity.ok(hardwareService.getHardwareByName(hardwareName).stream().toList());
    }

//    @GetMapping("/desc/{articleName}")
//    public ResponseEntity<List<ArticleDTO>> filterArticlesByDescription(@PathVariable String articleName) {
//        return ResponseEntity.ok(articleService.getArticlesByDescription(articleName).stream().toList());
//    }

    @PostMapping("/new")
    public ResponseEntity<?> saveNewHardware(@Valid @RequestBody HardwareDTO hardwareDTO) {
        HardwareDTO savedHardwareDTO = hardwareService.saveNewHardware(hardwareDTO);
        return ResponseEntity.ok(savedHardwareDTO);
    }

    @PutMapping("{hardwareId}")
    public ResponseEntity<HardwareDTO> updateHardware(@Valid @RequestBody HardwareDTO hardwareDTO, @PathVariable Integer hardwareId) {
        if (hardwareService.hardwareByIdExists(hardwareId)) {
            hardwareService.updateHardware(hardwareDTO, hardwareId);
            return ResponseEntity.ok(hardwareDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{hardwareId}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer hardwareId) {
        if (hardwareService.hardwareByIdExists(hardwareId)) {
            boolean result = hardwareService.deleteHardwareById(hardwareId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
