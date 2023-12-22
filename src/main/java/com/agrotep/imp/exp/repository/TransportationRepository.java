package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.City;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.entity.Truck;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static com.agrotep.imp.exp.repository.PersonRepository.*;
import static com.agrotep.imp.exp.repository.TruckRepository.*;

@Repository
public class TransportationRepository {

    public static final long ID3 = 3L;
    public static final long ID1 = 1L;
    public static final long ID2 = 2L;
    public static final long ID4 = 4L;
    public static final long ID5 = 5L;
    public static final long ID6 = 6L;
    public static final long ID7 = 7L;
    public static final long ID8 = 8L;
    public static final long ID9 = 9L;

    public static final String UA = "UA";
    public static final String HU = "HU";
    public static final String DE = "DE";
    public static final String GB = "GB";
    public static final String IT = "IT";
    public static final String SE = "SE";

    public static final City VINNYTSYA = new City("Вінниця", 49.232780, 28.480970, UA);
    public static final City BUDAPESHT = new City("Будапешт", 47.4980100, 19.0399100, HU);
    public static final City KYIV = new City("Київ", 50.4546600, 30.5238000, UA);
    public static final City BERLIN = new City("Berlin", 52.5243700, 13.4105300, DE);
    public static final City LADYGYN = new City("Ладижин", 48.6849600, 29.2367900, UA);
    public static final City LVIV = new City("Львів", 49.8382600, 24.0232400, UA);
    public static final City LONDON = new City("London", 51.5085300, -0.1257400, GB);
    public static final City MILAN = new City("Мілан", 45.4642700, 9.1895100, IT);
    public static final City OSLO = new City("Oslo", 59.9127300, 10.7460900, SE);
    Set<Transportation> TRANSPORTATIONS = new HashSet<>(Set.of(Transportation.builder()
                    .id(ID1)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity(VINNYTSYA.name())
                    .loadingCountry(VINNYTSYA.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(BUDAPESHT.name())
                    .unloadingCountry(BUDAPESHT.country())
                    .typeOfTruck("реф")
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .truck(TRUCK1)
                    .orderDate(LocalDate.of(2023, 11, 7))
                    .orderTime(LocalTime.of(15, 27))
                    .clientContactPerson("Florian Hoffman")
                    .cargoWeightTons(20F)
                    .cargoVolumeM3(96F)
                    .cargoPlacesNumber(23)
                    .cargoPlacesType("піддони")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 1))
                    .loadingTime(LocalTime.of(0, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
//                    .loadingCity("Arlon")
//                    .loadingCountry("Бельгія")
                    .loadingCityPostalIndex("5780")
                    .loadingAddress("LS")
                    .loadingLatitude(VINNYTSYA.latitude())
                    .loadingLongitude(VINNYTSYA.longitude())
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 23))
                    .unloadingTime(LocalTime.of(7, 44))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BUDAPESHT.latitude())
                    .unloadingLongitude(BUDAPESHT.longitude())
                    .build(),
            Transportation.builder()
                    .id(ID2)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity(VINNYTSYA.name())
                    .loadingCountry(VINNYTSYA.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(BUDAPESHT.name())
                    .unloadingCountry(BUDAPESHT.country())
                    .typeOfTruck("реф")
                    .truck(TRUCK3)
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .transportationComment("перенесено на 3.12.2023")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 1))
                    .loadingTime(LocalTime.of(0, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(VINNYTSYA.latitude())
                    .loadingLongitude(VINNYTSYA.longitude())

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BUDAPESHT.latitude())
                    .unloadingLongitude(BUDAPESHT.longitude())
                    .build(),

            Transportation.builder()
                    .id(ID3)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity(VINNYTSYA.name())
                    .loadingCountry(VINNYTSYA.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(BUDAPESHT.name())
                    .unloadingCountry(BUDAPESHT.country())
                    .typeOfTruck("реф")
                    .clientCompany("Оріфлейм")
                    .manager(SVITLANA)
                    .cargo("косметика")
                    .isCargoAdr(true)

                    .loadingNo(2)
                    .loadingDate(LocalDate.of(2023, 12, 1))
                    .loadingTime(LocalTime.of(0, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(VINNYTSYA.latitude())
                    .loadingLongitude(VINNYTSYA.longitude())

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BUDAPESHT.latitude())
                    .unloadingLongitude(BUDAPESHT.longitude())
                    .build(),
            Transportation.builder()
                    .id(ID4)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 2))
                    .loadingCity(VINNYTSYA.name())
                    .loadingCountry(VINNYTSYA.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(BUDAPESHT.name())
                    .unloadingCountry(BUDAPESHT.country())
                    .typeOfTruck("реф")
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .truck(TRUCK2)
                    .comment("комент")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 2))
                    .loadingTime(LocalTime.of(0, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(VINNYTSYA.latitude())
                    .loadingLongitude(VINNYTSYA.longitude())

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BUDAPESHT.latitude())
                    .unloadingLongitude(BUDAPESHT.longitude())
                    .build(),
            Transportation.builder()
                    .id(ID5)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 2))
                    .loadingCity(VINNYTSYA.name())
                    .loadingCountry(VINNYTSYA.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(BUDAPESHT.name())
                    .unloadingCountry(BUDAPESHT.country())
                    .typeOfTruck("реф")
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .transportationComment("відміна")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 2))
                    .loadingTime(LocalTime.of(0, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(VINNYTSYA.latitude())
                    .loadingLongitude(VINNYTSYA.longitude())

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BUDAPESHT.latitude())
                    .unloadingLongitude(BUDAPESHT.longitude())
                    .build(),
            Transportation.builder()
                    .id(ID6)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 2))
                    .loadingCity(BERLIN.name())
                    .loadingCountry(BERLIN.country())
                    .borderCrossingPoint("Дорогуцьк")
                    .unloadingCity(KYIV.name())
                    .unloadingCountry(KYIV.country())
                    .typeOfTruck("реф")
                    .clientCompany("Fererro")
                    .manager(ANTON)
                    .truck(TRUCK6)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 10:00")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 19))
                    .loadingTime(LocalTime.of(10, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(KYIV.latitude())
                    .loadingLongitude(KYIV.longitude())
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(BERLIN.latitude())
                    .unloadingLongitude(BERLIN.longitude())
                    .build(),
            Transportation.builder()
                    .id(ID7)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 19))
                    .loadingCity(LADYGYN.name())
                    .loadingCountry(LADYGYN.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(LONDON.name())
                    .unloadingCountry(LONDON.country())
                    .typeOfTruck("реф")
                    .clientCompany("МХП")
                    .manager(OKSANA)
                    .truck(TRUCK7)
                    .cargo("курка")
                    .temperatureMin(4)
                    .temperatureMax(10)
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 19))
                    .loadingTime(LocalTime.of(10, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(LADYGYN.latitude())
                    .loadingLongitude(LADYGYN.longitude())
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(LONDON.latitude())
                    .unloadingLongitude(LONDON.longitude())
                    .comment("ЕКМТ")
                    .build(),
            Transportation.builder()
                    .id(ID8)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 19))
                    .loadingCity(MILAN.name())
                    .loadingCountry(MILAN.country())
                    .borderCrossingPoint("Чоп")
                    .unloadingCity(OSLO.name())
                    .unloadingCountry(OSLO.country())
                    .typeOfTruck("тент")
                    .clientCompany("Ганекс")
                    .manager(OKSANA)
                    .cargo("тнп")
                    .temperatureMin(4)
                    .temperatureMax(10)
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, 12, 19))
                    .loadingTime(LocalTime.of(10, 8))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(MILAN.latitude())
                    .loadingLongitude(MILAN.longitude())
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 2))
                    .unloadingTime(LocalTime.of(9, 18))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(OSLO.latitude())
                    .unloadingLongitude(OSLO.longitude())
                    .transportationComment("відміна")
                    .build(),
            Transportation.builder()
                    .id(ID9)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 19))
                    .loadingCity(KYIV.name())
                    .loadingCountry(KYIV.country())
                    .unloadingCity(LVIV.name())
                    .unloadingCountry(LVIV.country())
                    .typeOfTruck("тент")
                    .clientCompany("Ганекс")
                    .manager(OKSANA)
                    .cargo("тнп")
                    .loadingNo(1)
                    .loadingDate(LocalDate.of(2023, Month.DECEMBER, 19))
                    .loadingTime(LocalTime.of(12, 18))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .loadingAddress("LS")
                    .loadingLatitude(KYIV.latitude())
                    .loadingLongitude(KYIV.longitude())
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, Month.DECEMBER, 22))
                    .unloadingTime(LocalTime.of(9, 16))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(LVIV.latitude())
                    .unloadingLongitude(LVIV.longitude())
                    .transportationComment("перенесено на 25.01")
                    .build()
    ));

    public Collection<Transportation> findAll() {
        return TRANSPORTATIONS;
    }

    public Transportation save(Transportation t) {
        if (!TRANSPORTATIONS.contains(t)) {
            long maxId = TRANSPORTATIONS.stream()
                    .mapToLong(Transportation::getId)
                    .max()
                    .orElse(1L);
            t.setId(maxId + 1);
        }
        TRANSPORTATIONS.remove(t);
        TRANSPORTATIONS.add(t);
        return t;
    }

    public Optional<Transportation> findById(Long id) {
        return TRANSPORTATIONS.stream()
                .filter(t -> Objects.equals(id, t.getId()))
                .findAny();
    }

    public Optional<Transportation> findTransportationForTruck(Truck truck) {
        return TRANSPORTATIONS.stream()
                .filter(t -> Objects.equals(truck, t.getTruck()))
                .findAny();
    }
}
