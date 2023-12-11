package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.dto.LoadingDto;
import com.agrotep.imp.exp.entity.Transportation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

import static com.agrotep.imp.exp.repository.PersonRepository.SVITLANA;

@Repository
public class TransportationRepository {

    Set<Transportation> TRANSPORTATIONS = Set.of(
            Transportation.builder()
                    .id(1L)
                    .transportationDate(LocalDate.of(2023, Month.DECEMBER, 1))
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
                    .equipage("AA4545BB/CC8787BB")
                    .driver("Юсь Сергій АТ")
                    .orderDate(LocalDate.of(2023, 11, 7))
                    .orderTime(LocalTime.of(15, 27))
                    .clientContactPerson("Florian Hoffman")
                    .cargoWeightTons(20F)
                    .cargoVolumeM3(96F)
                    .cargoPlacesNumber(23)
                    .cargoPlacesType("піддони")
                    .cargoTemperatureFrom(2)
                    .loadings(Collections.singletonList(
                            LoadingDto.builder()
                                    .no(1)
                                    .loadingDate(LocalDate.of(2023, 11, 7))
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingType("Зав")
                                    .senderReceiverLegalEntity("Arlon")
                                    .loadingCity("Arlon")
                                    .loadingCountry("Бельгія")
                                    .loadingAddress("LS")
                                    .loadingLatitude(49.6951348876953125)
                                    .loadingLongitude(5.81048059469500977)
                                    .build()
                    ))
                    .build(),
            Transportation.builder()
                    .id(2L)
                    .transportationDate(LocalDate.of(2023, Month.DECEMBER, 1))
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
                    .transportationComment("перенесено на 3.12.2023")
                    .build(),
            Transportation.builder()
                    .id(3L)
                    .transportationDate(LocalDate.of(2023, Month.DECEMBER, 1))
                    .loadingCity("Вінниця")
                    .loadingCountry("UA")
                    .borderCrossingPoint("Чоп")
                    .unloadingCity("Будапешт")
                    .unloadingCountry("HU")
                    .typeOfTruck("реф")
                    .clientCompany("Оріфлейм")
                    .manager(SVITLANA)
                    .cargo("косметика")
                    .dangerousStatus("ADR")
                    .build(),
            Transportation.builder()
                    .id(4L)
                    .transportationDate(LocalDate.of(2023, Month.DECEMBER, 2))
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
                    .equipage("AA1111BB/CC2222BB")
                    .driver("Ось Юрій ВТ")
                    .comment("комент")
                    .build(),
            Transportation.builder()
                    .id(5L)
                    .transportationDate(LocalDate.of(2023, Month.DECEMBER, 2))
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
                    .build()
    );

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
        TRANSPORTATIONS.add(t);
        return t;
    }

    public Optional<Transportation> findById(Long id) {
        return TRANSPORTATIONS.stream()
                .filter(t -> Objects.equals(id, t.getId()))
                .findAny();
    }
}
