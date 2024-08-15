package com.joboffers.domain.offer;

import com.joboffers.domain.offer.dto.JobOfferResponse;
import com.joboffers.domain.offer.dto.OfferRequestDto;
import com.joboffers.domain.offer.dto.OfferResponseDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class OfferFacadeTest {
@Test
public void should_fetch_from_jobs_from_remote_and_save_all_offers_when_repository_is_empty(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration().offerFacadeForTests();
    assertThat(offerFacade.findAllOffers()).isEmpty();

    //When
    List<OfferResponseDto> result = offerFacade.fetchAllOffersAndSaveAllIfNotExist();

    //Then
    assertThat(result).hasSize(6);
}
@Test
public void should_save_only_2_offers_when_repository_had_4_added_with_offer_urls(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration(
            List.of(new JobOfferResponse("id", "id", "asds", "1"),
                    new JobOfferResponse("assd", "id", "asds", "2"),
                    new JobOfferResponse("asddd", "id", "asds", "3"),
                    new JobOfferResponse("asfd", "id", "asds", "4"),
                    new JobOfferResponse("Junior", "Comarch", "1000", "https://someurl.pl/5"),
                    new JobOfferResponse("Mid", "Finanteq", "2000", "https://someother.pl/6"))
    ).offerFacadeForTests();
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "1"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "2"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "3"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "4"));
    assertThat(offerFacade.findAllOffers()).hasSize(4);

    //When
    List<OfferResponseDto> response = offerFacade.fetchAllOffersAndSaveAllIfNotExist();

    //Then
    assertThat(List.of(
            response.get(0).offerUrl(),
            response.get(1).offerUrl()
    )).containsExactlyInAnyOrder("https://someurl.pl/5", "https://someother.pl/6");
}
@Test
public void should_save_4_offers_when_there_are_no_offers_in_database(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();

    //When
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "1"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "2"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "3"));
    offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "4"));

    //Then
    assertThat(offerFacade.findAllOffers()).hasSize(4);
}
@Test
public void should_find_offer_by_id_when_offer_was_saved(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
    OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "1"));

    //When
    OfferResponseDto offerById = offerFacade.findOfferById(offerResponseDto.id());

    //Then
    assertThat(offerById).isEqualTo(OfferResponseDto.builder()
            .id(offerResponseDto.id())
            .companyName("id")
            .position("asds")
            .salary("asdasd")
            .offerUrl("1")
            .build());
}
@Test
public void should_throw_not_found_exception_when_offer_not_found(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
    assertThat(offerFacade.findAllOffers().isEmpty());

    //When
    Throwable thrown = catchThrowable(() -> offerFacade.findOfferById("100"));

    //Then
    AssertionsForClassTypes.assertThat(thrown).isInstanceOf(OffferNotFoundException.class).hasMessage("Offer with id 100 not found");
}
@Test
public void should_throw_duplicate_key_exception_when_with_offer_url_exists(){
    //Given
    OfferFacade offerFacade = new OfferFacadeTestConfiguration(List.of()).offerFacadeForTests();
    OfferResponseDto offerResponseDto = offerFacade.saveOffer(new OfferRequestDto("id", "asds", "asdasd", "hello.pl"));
    String saveId = offerResponseDto.id();
    assertThat(offerFacade.findOfferById(saveId).id()).isEqualTo(saveId);

    //When
    Throwable thrown = catchThrowable(() -> offerFacade.saveOffer(new OfferRequestDto("cx", "vc", "xcv", "hello.pl")));

    //Then
    AssertionsForClassTypes.assertThat(thrown).isInstanceOf(OfferDuplicateException.class).hasMessage("Offer with offerUrl [hello.pl] already exists");
}

}