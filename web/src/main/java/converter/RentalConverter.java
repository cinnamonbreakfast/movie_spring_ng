package converter;

import domain.Rental;
import dto.RentalDTO;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDTO> {
    @Override
    public Rental convertDtoToModel(RentalDTO dto) {
        Rental rental = Rental.builder()
                .client(dto.getClient())
                .movie(dto.getMovie())
                .build();
        rental.setId(dto.getId());
        return rental;
    }

    @Override
    public RentalDTO convertModelToDto(Rental rental) {
        RentalDTO dto = RentalDTO.builder()
                .client(rental.getClient())
                .movie(rental.getMovie())
                .build();
        dto.setId(rental.getId());
        return dto;
    }
}
