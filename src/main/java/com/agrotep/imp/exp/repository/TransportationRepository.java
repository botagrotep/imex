package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.City;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.entity.Truck;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static com.agrotep.imp.exp.entity.enums.LoadingType.*;
import static com.agrotep.imp.exp.repository.PersonRepository.*;
import static com.agrotep.imp.exp.repository.TruckRepository.*;
import static com.agrotep.imp.exp.service.TransportationService.DD_MM;

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
    public static final long ID10 = 10L;
    public static final long ID11 = 11L;

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
    public static final City EDINBURGH = new City("Единбург", 55.9520600, -3.1964800, GB);
    public static final City TULCHYN = new City("Тульчин", 48.6744800, 28.8464100, UA);
    public static final City TARASIVKA = new City("Тарасівка", 51.5693603, 33.0484255, UA);

    static Set<Transportation> TRANSPORTATIONS = new HashSet<>(Set.of(Transportation.builder()
                    .id(ID1)
                    .transportationFillingDate(LocalDate.now().minusDays(1))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(1L)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(1)
                                    .loadingDate(LocalDate.now().minusDays(1))
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingCityPostalIndex("5780")
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(2L)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingCountry(BUDAPESHT.country())
                                    .loadingCity(BUDAPESHT.name())
                                    .loadingNo(2)
                                    .loadingDate(LocalDate.now().plusDays(1))
                                    .loadingTime(LocalTime.of(7, 44))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BUDAPESHT.latitude())
                                    .loadingLongitude(BUDAPESHT.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
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
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID2)
                    .transportationFillingDate(LocalDate.now())
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(3L)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(1)
                                    .loadingDate(LocalDate.now())
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build(),
                            Loading.builder()
                                    .loadingCity(BUDAPESHT.name())
                                    .loadingCountry(BUDAPESHT.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusDays(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BUDAPESHT.latitude())
                                    .loadingLongitude(BUDAPESHT.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .truck(TRUCK3)
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .transportationComment(String.format("перенесено на %s",
                            LocalDate.now().plusWeeks(2).format(DD_MM)))
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID3)
                    .transportationFillingDate(LocalDate.now().plusDays(2))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(3L)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(2)
                                    .loadingType(COMPLEX_LOADING)
                                    .loadingDate(LocalDate.now().plusDays(1))
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(4L)
                                    .loadingCity(BUDAPESHT.name())
                                    .loadingCountry(BUDAPESHT.country())
                                    .loadingNo(2)
                                    .loadingType(COMPLEX_UNLOADING)
                                    .loadingDate(LocalDate.now().plusDays(3))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BUDAPESHT.latitude())
                                    .loadingLongitude(BUDAPESHT.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("Оріфлейм")
                    .manager(SVITLANA)
                    .cargo("косметика")
                    .isCargoAdr(true)
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID4)
                    .transportationFillingDate(LocalDate.now().minusWeeks(1))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(5L)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now().minusWeeks(1).minusDays(1))
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(6L)
                                    .loadingCity(BUDAPESHT.name())
                                    .loadingCountry(BUDAPESHT.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().minusDays(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BUDAPESHT.latitude())
                                    .loadingLongitude(BUDAPESHT.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .truck(TRUCK2)
                    .comment("комент")
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID5)
                    .transportationFillingDate(LocalDate.now().plusWeeks(1))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(7L)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now().plusWeeks(1).plusDays(1))
                                    .loadingTime(LocalTime.of(0, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(8L)
                                    .loadingCity(BUDAPESHT.name())
                                    .loadingCountry(BUDAPESHT.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusWeeks(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BUDAPESHT.latitude())
                                    .loadingLongitude(BUDAPESHT.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("Рошен")
                    .manager(SVITLANA)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 9:00")
                    .transportationComment("відміна")
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID6)
                    .transportationFillingDate(LocalDate.now())
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(10L)
                                    .loadingCity(BERLIN.name())
                                    .loadingCountry(BERLIN.country())
                                    .loadingNo(1)
                                    .loadingType(COMPLEX_LOADING)
                                    .loadingDate(LocalDate.now())
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(KYIV.latitude())
                                    .loadingLongitude(KYIV.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(11L)
                                    .loadingCity(KYIV.name())
                                    .loadingCountry(KYIV.country())
                                    .loadingNo(2)
                                    .loadingType(COMPLEX_UNLOADING)
                                    .loadingDate(LocalDate.now().plusWeeks(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(BERLIN.latitude())
                                    .loadingLongitude(BERLIN.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Дорогуцьк")
                    .typeOfTruck("реф")
                    .clientCompany("Fererro")
                    .manager(ANTON)
                    .truck(TRUCK6)
                    .cargo("шоколад")
                    .temperatureMin(10)
                    .temperatureMax(12)
                    .coordinatorComment("завантаження о 10:00")
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .build(),
            Transportation.builder()
                    .id(ID7)
                    .transportationFillingDate(LocalDate.now().minusDays(1))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(12L)
                                    .loadingCity(LADYGYN.name())
                                    .loadingCountry(LADYGYN.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now().minusWeeks(1).minusDays(1))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(LADYGYN.latitude())
                                    .loadingLongitude(LADYGYN.longitude())
                                    .build(),
                            Loading.builder()
                                    .loadingCity(LONDON.name())
                                    .loadingCountry(LONDON.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusDays(1))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(LONDON.latitude())
                                    .loadingLongitude(LONDON.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("МХП")
                    .manager(OKSANA)
                    .truck(TRUCK7)
                    .cargo("курка")
                    .temperatureMin(4)
                    .temperatureMax(10)
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .comment("ЕКМТ")
                    .build(),
            Transportation.builder()
                    .id(ID8)
                    .transportationFillingDate(LocalDate.now().minusDays(3))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(13L)
                                    .loadingCity(MILAN.name())
                                    .loadingCountry(MILAN.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now().minusDays(3))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(MILAN.latitude())
                                    .loadingLongitude(MILAN.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(14L)
                                    .loadingCity(OSLO.name())
                                    .loadingCountry(OSLO.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now())
                                    .loadingTime(LocalTime.of(9, 18))
                                    .loadingAddress("LS")
                                    .loadingLatitude(OSLO.latitude())
                                    .loadingLongitude(OSLO.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("тент")
                    .clientCompany("Ганекс")
                    .manager(OKSANA)
                    .cargo("тнп")
                    .temperatureMin(4)
                    .temperatureMax(10)
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .transportationComment("відміна")
                    .build(),
            Transportation.builder()
                    .id(ID9)
                    .transportationFillingDate(LocalDate.now().minusDays(4))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(15L)
                                    .loadingCity(KYIV.name())
                                    .loadingCountry(KYIV.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now())
                                    .loadingTime(LocalTime.of(12, 18))
                                    .loadingAddress("LS")
                                    .loadingLatitude(KYIV.latitude())
                                    .loadingLongitude(KYIV.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(16L)
                                    .loadingCity(LVIV.name())
                                    .loadingCountry(LVIV.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.of(2023, Month.DECEMBER, 22))
                                    .loadingTime(LocalTime.of(9, 16))
                                    .loadingAddress("LS")
                                    .loadingLatitude(LVIV.latitude())
                                    .loadingLongitude(LVIV.longitude())
                                    .build()
                    )))
                    .typeOfTruck("тент")
                    .clientCompany("Ганекс")
                    .manager(OKSANA)
                    .cargo("тнп")
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .transportationComment(String.format("перенесено на %s",
                            LocalDate.now().plusDays(2).format(DD_MM)))
                    .build(),
            Transportation.builder()
                    .id(ID10)
                    .transportationFillingDate(LocalDate.now().plusDays(2))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(17L)
                                    .loadingCity(EDINBURGH.name())
                                    .loadingCountry(EDINBURGH.country())
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now().plusWeeks(1).minusDays(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(EDINBURGH.latitude())
                                    .loadingLongitude(EDINBURGH.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(18L)
                                    .loadingCity(VINNYTSYA.name())
                                    .loadingCountry(VINNYTSYA.country())
                                    .loadingNo(4)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusMonths(1))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(VINNYTSYA.latitude())
                                    .loadingLongitude(VINNYTSYA.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("МХП")
                    .manager(OKSANA)
                    .truck(TRUCK7)
                    .cargo("комбікорм")
                    .temperatureMin(4)
                    .temperatureMax(10)
                    .loadingSenderReceiverLegalEntity("Arlon")
                    .comment("ЕКМТ")
                    .build(),
            Transportation.builder()
                    .id(ID11)
                    .transportationFillingDate(LocalDate.now().plusDays(2))
                    .loadings(new ArrayList<>(List.of(
                            Loading.builder()
                                    .id(18L)
                                    .loadingCity(TULCHYN.name())
                                    .loadingCountry(TULCHYN.country())
                                    .loadingRegion("Вінницька обл.")
                                    .loadingNo(1)
                                    .loadingType(SIMPLE_LOADING)
                                    .loadingDate(LocalDate.now())
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(TULCHYN.latitude())
                                    .loadingLongitude(TULCHYN.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(19L)
                                    .loadingCity(KYIV.name())
                                    .loadingCountry(KYIV.country())
                                    .loadingNo(2)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusDays(1))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(KYIV.latitude())
                                    .loadingLongitude(KYIV.longitude())
                                    .build(),
                            Loading.builder()
                                    .id(20L)
                                    .loadingCity(TARASIVKA.name())
                                    .loadingCountry(TARASIVKA.country())
                                    .loadingRegion("Чернігівська обл.")
                                    .loadingAddress("Коропський район")
                                    .loadingNo(3)
                                    .loadingType(SIMPLE_UNLOADING)
                                    .loadingDate(LocalDate.now().plusDays(2))
                                    .loadingTime(LocalTime.of(10, 8))
                                    .loadingAddress("LS")
                                    .loadingLatitude(TARASIVKA.latitude())
                                    .loadingLongitude(TARASIVKA.longitude())
                                    .build()
                    )))
                    .borderCrossingPoint("Чоп")
                    .typeOfTruck("реф")
                    .clientCompany("ЛЕВ ЛОГІСТИК")
                    .clientContactPerson("Ткаченко Олена Олександрівна - директо")
                    .manager(ANTON)
                    .truck(TRUCK7)
                    .cargo("Молочна продукція")
                    .temperatureMin(-2)
                    .comment("ЕКМТ")
                    .build()
    ));

    public static final Collection<String> COUNTRIES = getCountries();
    public static final Collection<String> COMPANIES = getCompanies();
    public static final Collection<String> BORDER_CROSSING_POINTS = getBorderCrossingPoints();

    private static Collection<String> getCountries() {
        Set<String> countries = new LinkedHashSet<>(List.of("АГРОТЕП", "UA", "FR", "DE"));
        countries.addAll(TRANSPORTATIONS.stream()
                .map(Transportation::getLoadings)
                .flatMap(Collection::stream)
                .map(Loading::getLoadingCountry)
                .collect(Collectors.toSet()));
        return countries;
    }

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

    private static Collection<String> getCompanies() {

        List<String> predefinedCompanies = new ArrayList<>(List.of(
                "Новий клієнт",
                "Fererro (оплата 45 календарних днів по виставленню інвойсу, ліміт 300000 грн)",
                "МХП (по факту вивантаження, ліміт 50000 грн)",
                "Веселі бобри (10 днів по вивантаженню, ліміт 40000 грн)"
        ));
        predefinedCompanies.addAll(TRANSPORTATIONS.stream()
                .map(Transportation::getClientCompany)
                .distinct()
                .toList());
        return predefinedCompanies;
    }

    private static Collection<String> getBorderCrossingPoints() {
        return TRANSPORTATIONS.stream()
                .map(Transportation::getBorderCrossingPoint)
                .filter(StringUtils::hasText)
                .collect(Collectors.toSet());
    }

    public List<Transportation> findByTruckAndLoadingDateAfterEqual(Truck truck, LocalDate date) {
        return TRANSPORTATIONS.stream()
                .filter(t -> Objects.equals(t.getTruck(), truck))
                .filter(t -> {
                    LocalDate loadingDate = Objects.requireNonNull(CollectionUtils.firstElement(t.getLoadings())).getLoadingDate();
                    return loadingDate != null && (loadingDate.equals(date) || loadingDate.isAfter(date));
                })
                .collect(Collectors.toList());
    }
}
