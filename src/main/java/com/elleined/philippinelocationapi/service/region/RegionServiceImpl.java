package com.elleined.philippinelocationapi.service.region;

import com.elleined.philippinelocationapi.dto.RegionDTO;
import com.elleined.philippinelocationapi.exception.AlreadyExistsException;
import com.elleined.philippinelocationapi.exception.ResourceNotFoundException;
import com.elleined.philippinelocationapi.mapper.RegionMapper;
import com.elleined.philippinelocationapi.model.region.Region;
import com.elleined.philippinelocationapi.repository.region.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public List<Region> saveAll(List<RegionDTO> regionDTOS) throws AlreadyExistsException {
        if (regionDTOS.stream().anyMatch(this::isAlreadyExists)) throw new AlreadyExistsException("Cannot save all regions! because one of the provided region id already exists!");

        List<Region> regions = regionDTOS.stream()
                .map(regionMapper::toEntity)
                .toList();
        regionRepository.saveAll(regions);
        log.debug("Saving all regions success...");
        return regions;
    }

    @Override
    public Region getById(int id) throws ResourceNotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Region with id of " + id + " does not exists!"));
    }

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll().stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .toList();
    }

    @Override
    public boolean isAlreadyExists(RegionDTO regionDTO) {
        return regionRepository.existsById(regionDTO.getId());
    }

    @Override
    public List<Region> searchByLocationName(String locationName) {
        return regionRepository.searchByLocationName(locationName).stream()
                .sorted(Comparator.comparing(Region::getLocationName))
                .toList();
    }
}
