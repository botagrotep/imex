package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Transportation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
}
