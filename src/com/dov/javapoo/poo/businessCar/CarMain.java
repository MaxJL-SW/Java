package com.dov.javapoo.poo.businessCar;

import java.time.LocalDate;
import java.util.List;
import static com.dov.javapoo.poo.businessCar.CarList.affichageCar;
import static com.dov.javapoo.poo.businessCar.CarList.printCarsRegisteredBetween;

public class CarMain {
    public static void main(String[] args) {
        CarList carList = new CarList();

        //Ajout des vehicules a la liste
        carList.addCars();

        // Afficher toutes les voitures
        CarList.affichageCar(carList.getCars());

        // Filtrer et afficher les voitures enregistrées entre 1990 et 2000
        CarList.printCarsRegisteredBetween(carList.getCars(), 1990, 2000);


    }
}
