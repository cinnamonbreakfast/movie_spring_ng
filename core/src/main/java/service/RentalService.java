package service;

import domain.Rental;

import java.io.IOException;

public interface RentalService extends BaseService<Long, Rental> {
    void addRental(Rental entity);
    void removeRental(Long ID);
    Long mostRentedMovie();
    Long mostLoyalCustomer();
}
