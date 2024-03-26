package com.dov.javapoo.poo.businessCar;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class CarList {
        private List<Car> cars;

        public CarList() {
            this.cars = new ArrayList<>();
        }

        public void addCar(Car car) {
            this.cars.add(car);
        }

        public void addCars() {
            // Création et ajout de 10 véhicules
            addCar(new Car("ABC123", "Toyota", "Corolla", LocalDate.of(110, 3, 1), 15000)); // Exemple d'année : 2010 (année 110 + 1900)
            addCar(new Car("DEF456", "Honda", "Civic", LocalDate.of(1995, 5, 1), 16000));
            addCar(new Car("GHI789", "Ford", "Focus", LocalDate.of(115, 7, 1), 14000));
            addCar(new Car("JKL012", "Chevrolet", "Malibu", LocalDate.of(117, 2, 1), 18000));
            addCar(new Car("MNO345", "Tesla", "Model 3", LocalDate.of(119, 2, 1), 35000));
            addCar(new Car("PQR678", "BMW", "320i",LocalDate.of(120, 8, 1), 25000));
            addCar(new Car("STU901", "Audi", "A4",LocalDate.of(99, 11, 1), 27000));
            addCar(new Car("VWX234", "Mercedes", "C-Class", LocalDate.of(122, 1, 1), 30000));
            addCar(new Car("YZA567", "Volkswagen", "Golf",LocalDate.of(118, 1, 1), 19000));
            addCar(new Car("BCD890", "Nissan", "Leaf", LocalDate.of(119, 3, 1), 22000));
        }

        public List<Car> getCars() {
            return cars;
        }
    public static void affichageCar(List<Car> cars) {
        for (Car car : cars) {
            System.out.println("Immatriculation: " + car.getImmatriculation() +
                    ", Marque: " + car.getMarque() +
                    ", Modèle: " + car.getModele() +
                    ", Année: " + car.getAnnee().getYear() +
                    ", Prix: " + car.getPrix());
        }
    }
    public static void printCarsRegisteredBetween(List<Car> cars, int startYear, int endYear) {
        cars.stream()
                .filter(car -> car.getAnnee() != null && !car.getAnnee().isBefore(LocalDate.of(startYear, 1, 1))
                        && !car.getAnnee().isAfter(LocalDate.of(endYear, 12, 31)))
                .forEach(car -> System.out.println(car.getImmatriculation() + " - " + car.getMarque() + " " + car.getModele() + " (" + car.getAnnee().getYear() + ")"));
    }

}


