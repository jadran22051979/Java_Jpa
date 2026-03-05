package com.example.Java_Jpa.service;

import com.example.Java_Jpa.dto.HardwareDTO;
import com.example.Java_Jpa.dto.SearchHardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();

    List<HardwareDTO> getHardwareByName(String hardwareName);

    HardwareDTO saveNewHardware(HardwareDTO hardware);

    List<HardwareDTO> filterByParameters(SearchHardwareDTO searchHardwareDTO);

    Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id);

    boolean hardwareByIdExists(Integer id);

    boolean deleteHardwareById(Integer id);
}
