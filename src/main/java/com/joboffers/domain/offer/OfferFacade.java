package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

public class OfferFacade {
    private final OfferRepository offerRepository;
    private final OfferService offerService;

    @Cacheable(cacheNames = "jobOffers")
    public  List<OfferResponseDto> findAllOffers(){
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .collect(Collectors.toList());
    }
    public List<OfferResponseDto> fetchAllOffersAndSaveAllIfNotExist(){
        return offerService.fetchAllOffersAndSaveAllIfNotExists()
                .stream()
                .map(OfferMapper::mapFromOfferToOfferDto)
                .toList();
    }
    public OfferResponseDto findOfferById(String id){
        return offerRepository.findById(id)
                .map(OfferMapper::mapFromOfferToOfferDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }
    public OfferResponseDto saveOffer(OfferRequestDto offerDto){
        final  Offer offer = OfferMapper.mapFromOfferDtoToOffer(offerDto);
        final Offer save = offerRepository.save(offer);
        return OfferMapper.mapFromOfferToOfferDto(save);
    }
}
