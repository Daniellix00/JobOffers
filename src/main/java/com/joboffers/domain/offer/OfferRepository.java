package com.joboffers.domain.offer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository {

    boolean existByOfferUrl(String offerUrl);
    Optional<Offer>findByOfferUrl(String offerUrl);
    List<Offer> findAll();
    List<Offer> saveAll(List<Offer> offers);
    Optional<Offer> findById(String id);
    Offer save(Offer offer);

}
