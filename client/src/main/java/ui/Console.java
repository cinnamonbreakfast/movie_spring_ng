package ui;

import dto.ClientDTO;
import dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import provider.ClientProvider;
import provider.MovieProvider;
import provider.RentalProvider;

import java.util.Scanner;

@Component
public class Console {
    private ClientProvider clientProvider;
    private MovieProvider movieProvider;
    private RentalProvider rentalProvider;

    private static Scanner keyboard = new Scanner(System.in);

    @Autowired
    public Console(ClientProvider clientProvider, MovieProvider movieProvider, RentalProvider rentalProvider) {
        this.clientProvider = clientProvider;
        this.movieProvider = movieProvider;
        this.rentalProvider = rentalProvider;
    }

    public void printMenu()
    {
        System.out.println();
        System.out.println("--- Movie rental ---");

        System.out.println();

        System.out.println("1  : Print Clients");
        System.out.println("2  : Add Clients");
        System.out.println("3  : Update Clients");
        System.out.println("4  : Remove Clients");
        System.out.println("5  : Sort Clients ASC by");
        System.out.println("6  : Sort Clients DESC by");
        System.out.println("7  : Filter Clients by First name");
        System.out.println("8  : Filter Clients by Last name");
        System.out.println("9  : Filter Clients by Age");

        System.out.println();

        System.out.println("10 : Print Movies");
        System.out.println("11 : Add Movie");
        System.out.println("12 : Update Movie");
        System.out.println("13 : Remove Movie");
        System.out.println("14 : Sort Movies ASC by");
        System.out.println("15 : Sort Movies DESC by");
        System.out.println("16 : Filter Movies by Title");
        System.out.println("17 : Filter Movies by Description");
        System.out.println("18 : Filter Movies by Price");
        System.out.println("19 : Filter Movies by Rating");

        System.out.println();
        System.out.println("0 : Exit");
        System.out.println();
    }

    public void run(){
        loopMenu();
    }

    public void clientsShow()
    {
        clientProvider.getAll().getClients().forEach(System.out::println);
    }

    public void clientAdd()
    {
        keyboard.nextLine();
        System.out.println("First name:");
        String firstName = keyboard.nextLine();

        System.out.println("Last name:");
        String secondName = keyboard.nextLine();

        System.out.println("Job:");
        String job = keyboard.nextLine();

        System.out.println("Age:");
        int age = keyboard.nextInt();

        ClientDTO clientDTO = new ClientDTO(firstName, secondName, job, age);
        clientProvider.save(clientDTO);

        System.out.println();
        System.out.println("Client successfully added!");
        System.out.println();
    }

    public void clientRemove()
    {
        keyboard.nextLine();
        System.out.println("Client ID:");
        Long ID = keyboard.nextLong();

        clientProvider.delete(ID);

        System.out.println();
        System.out.println("Client removed successfully!");
        System.out.println();
    }

    public void clientUpdate()
    {
        keyboard.nextLine();
        System.out.println("Client ID:");
        Long ID = keyboard.nextLong();

        keyboard.nextLine();
        System.out.println("First name:");
        String firstName = keyboard.nextLine();

        System.out.println("Last name:");
        String secondName = keyboard.nextLine();

        System.out.println("Job:");
        String job = keyboard.nextLine();

        System.out.println("Age:");
        int age = keyboard.nextInt();

        ClientDTO clientDTO = new ClientDTO(firstName, secondName, job, age);
        clientDTO.setId(ID);
        clientProvider.update(clientDTO);

        System.out.println();
        System.out.println("Client successfully updated!");
        System.out.println();
    }

    public void clientASCBy()
    {
        keyboard.nextLine();
        String field = keyboard.nextLine();

        System.out.println();
        clientProvider.getAllSortedAscendingByFields(field).getClients().forEach(System.out::println);
        System.out.println();
    }

    public void clientDESCBy()
    {
        keyboard.nextLine();
        System.out.println("Field:");
        String field = keyboard.nextLine();

        System.out.println();
        clientProvider.getAllSortedDescendingByFields(field).getClients().forEach(System.out::println);
        System.out.println();
    }

    public void clientFilterFirstName()
    {
        keyboard.nextLine();
        System.out.println("First name:");
        String firstName = keyboard.nextLine();

        System.out.println();
        clientProvider.filterClientsByFirstName(firstName).getClients().forEach(System.out::println);
        System.out.println();
    }

    public void clientFilterSecondName()
    {
        keyboard.nextLine();
        System.out.println("Last name:");
        String secondName = keyboard.nextLine();

        System.out.println();
        clientProvider.filterClientsBySecondName(secondName).getClients().forEach(System.out::println);
        System.out.println();
    }

    public void clientFilterAge()
    {
        keyboard.nextLine();
        System.out.println("Age:");
        int age = keyboard.nextInt();

        System.out.println();
        clientProvider.filterClientsByAge(age).getClients().forEach(System.out::println);
        System.out.println();
    }

    public void moviesShow()
    {
        movieProvider.getAll().getMovies().forEach(System.out::println);
    }

    public void movieAdd(){
        keyboard.nextLine();
        System.out.println("Title:");
        String name = keyboard.nextLine();

        System.out.println("Description:");
        String desc = keyboard.nextLine();

        System.out.println("Price:");
        int price = keyboard.nextInt();

        System.out.println("Rating:");
        int rate = keyboard.nextInt();

        MovieDTO movieDTO = new MovieDTO(name, desc, price, rate);
        movieProvider.save(movieDTO);

        System.out.println();
        System.out.println("Movie successfully added!");
        System.out.println();
    }

    public void movieRemove()
    {
        keyboard.nextLine();
        System.out.println("Movie ID:");
        Long ID = keyboard.nextLong();

        movieProvider.delete(ID);

        System.out.println();
        System.out.println("Movie removed successfully!");
        System.out.println();
    }

    public void movieUpdate()
    {
        keyboard.nextLine();
        System.out.println("Movie ID:");
        Long ID = keyboard.nextLong();

        keyboard.nextLine();
        System.out.println("Title:");
        String name = keyboard.nextLine();

        System.out.println("Description:");
        String desc = keyboard.nextLine();

        System.out.println("Price:");
        int price = keyboard.nextInt();

        System.out.println("Rating:");
        int rate = keyboard.nextInt();

        MovieDTO movieDTO = new MovieDTO(name, desc, price, rate);
        movieDTO.setId(ID);
        movieProvider.update(movieDTO);

        System.out.println();
        System.out.println("Movie successfully updated!");
        System.out.println();
    }

    public void movieASCBy()
    {
        keyboard.nextLine();
        System.out.println("Field:");
        String field = keyboard.nextLine();

        System.out.println();
        movieProvider.getAllSortedAscendingByFields(field).getMovies().forEach(System.out::println);
        System.out.println();
    }

    public void movieDESCBy()
    {
        keyboard.nextLine();
        System.out.println("Field:");
        String field = keyboard.nextLine();

        System.out.println();
        movieProvider.getAllSortedDescendingByFields(field).getMovies().forEach(System.out::println);
        System.out.println();
    }

    public void movieFilterName()
    {
        keyboard.nextLine();
        System.out.println("Name:");
        String name = keyboard.nextLine();

        System.out.println();
        movieProvider.filterMoviesByName(name).getMovies().forEach(System.out::println);
        System.out.println();
    }

    public void movieFilterDescription()
    {
        keyboard.nextLine();
        System.out.println("Description:");
        String desc = keyboard.nextLine();

        System.out.println();
        movieProvider.filterMoviesByDescription(desc).getMovies().forEach(System.out::println);
        System.out.println();
    }

    public void movieFilterPrice()
    {
        keyboard.nextLine();
        System.out.println("Price:");
        int price = keyboard.nextInt();

        System.out.println();
        movieProvider.filterMoviesByPrice(price).getMovies().forEach(System.out::println);
        System.out.println();
    }

    public void movieFilterRate()
    {
        keyboard.nextLine();
        System.out.println("Rate:");
        int rate = keyboard.nextInt();

        System.out.println();
        movieProvider.filterMoviesByRating(rate).getMovies().forEach(System.out::println);
        System.out.println();
    }

    private void loopMenu()
    {
        int cmd = -1;
        while(true)
        {
            printMenu();

            cmd = keyboard.nextInt();

            switch(cmd)
            {
                case 1:
                    clientsShow();
                    break;
                case 2:
                    clientAdd();
                    break;
                case 3:
                    clientUpdate();
                    break;
                case 4:
                    clientRemove();
                    break;
                case 5:
                    clientASCBy();
                    break;
                case 6:
                    clientDESCBy();
                    break;
                case 7:
                    clientFilterFirstName();;
                    break;
                case 8:
                    clientFilterSecondName();
                    break;
                case 9:
                    clientFilterAge();
                    break;

                case 10:
                    moviesShow();
                    break;
                case 11:
                    movieAdd();
                    break;
                case 12:
                    movieUpdate();
                    break;
                case 13:
                    movieRemove();
                    break;
                case 14:
                    movieASCBy();
                    break;
                case 15:
                    movieDESCBy();
                    break;
                case 16:
                    movieFilterName();
                    break;
                case 17:
                    movieFilterDescription();
                    break;
                case 18:
                    movieFilterPrice();
                    break;
                case 19:
                    movieFilterRate();
                    break;

                case 0:
                    return;
            }
        }
    }
}
