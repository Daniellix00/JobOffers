package com.joboffers.domain.offer;

public class OffferNotFoundException extends  RuntimeException{
    private final String offerId;

    public OffferNotFoundException( String offerId) {
        super(String.format("Offer with id %s not found", offerId));
        this.offerId = offerId;
    }
}
