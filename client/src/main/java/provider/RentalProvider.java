package provider;

import dto.RentalDTO;
import dto.RentalsDTO;

public interface RentalProvider extends Provider<RentalDTO> {
    RentalsDTO getAll();
    RentalsDTO getAllSortedAscendingByFields(String fields);
    RentalsDTO getAllSortedDescendingByFields(String fields);
}
