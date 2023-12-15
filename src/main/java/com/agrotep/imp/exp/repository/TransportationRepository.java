package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.entity.Truck;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static com.agrotep.imp.exp.repository.PersonRepository.SVITLANA;
import static com.agrotep.imp.exp.repository.TruckRepository.*;

@Repository
public class TransportationRepository {

    public static final long ID3 = 3L;
    public static final long ID1 = 1L;
    public static final long ID2 = 2L;
    public static final long ID4 = 4L;
    public static final long ID5 = 5L;
    Set<Transportation> TRANSPORTATIONS = new HashSet<>(Set.of(
            Transportation.builder()
                    .id(ID1)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
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
                    .loadingLatitude(49.6951348876953125)
                    .loadingLongitude(5.81048059469500977)
                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 23))
                    .unloadingTime(LocalTime.of(7, 44))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(12.6951348876953125)
                    .unloadingLongitude(5.81048059469500977)
                    .build(),
            Transportation.builder()
                    .id(ID2)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
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
                    .loadingLatitude(49.6951348876953125)
                    .loadingLongitude(5.81048059469500977)

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(50.6951348876953125)
                    .unloadingLongitude(7.81048059469500977)
                    .build(),

            Transportation.builder()
                    .id(ID3)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
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
                    .loadingLatitude(49.6951348876953125)
                    .loadingLongitude(5.81048059469500977)

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(50.6951348876953125)
                    .unloadingLongitude(7.81048059469500977)
                    .build(),
            Transportation.builder()
                    .id(ID4)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 2))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
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
                    .loadingLatitude(49.6951348876953125)
                    .loadingLongitude(5.81048059469500977)

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(50.6951348876953125)
                    .unloadingLongitude(7.81048059469500977)
                    .build(),
            Transportation.builder()
                    .id(ID5)
                    .transportationFillingDate(LocalDate.of(2023, Month.DECEMBER, 2))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
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
//                    .loadingCity("Arlon")
//                    .loadingCountry("Бельгія")
                    .loadingAddress("LS")
                    .loadingLatitude(49.6951348876953125)
                    .loadingLongitude(5.81048059469500977)

                    .unloadingNo(2)
                    .unloadingDate(LocalDate.of(2023, 12, 10))
                    .unloadingTime(LocalTime.of(10, 8))
                    .unloadingSenderReceiverLegalEntity("Arlon")
                    .unloadingAddress("LS")
                    .unloadingLatitude(50.6951348876953125)
                    .unloadingLongitude(7.81048059469500977)
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
