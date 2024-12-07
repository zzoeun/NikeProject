package com.github.supercodingspringproject.service;

import com.github.supercodingspringproject.repository.sneaker.Sneaker;
import com.github.supercodingspringproject.repository.sneaker.SneakerRepository;
import com.github.supercodingspringproject.repository.sneakerModelTraits.SneakerModelTrait;
import com.github.supercodingspringproject.repository.sneakerModelTraits.SneakerTrait;
import com.github.supercodingspringproject.service.exceptions.NotFoundException;
import com.github.supercodingspringproject.service.mapper.InventoryMapper;
import com.github.supercodingspringproject.web.dto.SimpleSneakerInfo;
import com.github.supercodingspringproject.web.dto.SneakerInfo;
import com.github.supercodingspringproject.web.dto.SneakerInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSneakerItemService {

    private final SneakerRepository sneakerRepository;

    public Page<SimpleSneakerInfo> findPopularSimpleSneakerInfo(Pageable pageable) {
        Page<Sneaker> sneakers = sneakerRepository.findAll(pageable);
        return sneakers.map(sneaker ->
                                    new SimpleSneakerInfo(
                                            sneaker.getId(),
                                            sneaker.getSneakerModelTraits()
                                                   .stream()
                                                   .map(SneakerModelTrait::getTrait)
                                                   .map(SneakerTrait::getDescriptions)
                                                   .collect(Collectors.toList())
                                    ));
    }

    public SneakerInfo findSpecificModelInfo(Integer modelId) {
        Sneaker sneakerFound = sneakerRepository.findById(modelId)
                                                .orElseThrow(() -> new NotFoundException(
                                                        "model ID인 '" + modelId + "' 를 찾을 수 없습니다."));

        List<String> descriptions = sneakerFound.getSneakerModelTraits()
                                                .stream()
                                                .map(SneakerModelTrait::getTrait)
                                                .map(SneakerTrait::getDescriptions)
                                                .collect(Collectors.toList());
        Double price = sneakerFound.getPrice();
        List<SneakerInventory> sneakerInventories = sneakerFound.getKoreaInventories()
                                                                .stream()
                                                                .map(InventoryMapper.INSTANCE::koreaInventoryToSneakerInventory)
                                                                .collect(Collectors.toList());

        return SneakerInfo.builder()
                          .modelId(modelId)
                          .price(price)
                          .sneakerInventories(sneakerInventories)
                          .modelTraits(descriptions)
                          .build();
    }
}
