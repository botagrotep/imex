package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Truck;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Repository
public class TruckRepository {

    public static final String REF = "реф";
    public static final String READY = "ГОТ";
    public static final Truck TRUCK1 = Truck.builder()
            .id(1L)
            .equipage("AA4545BB/CC8787BB")
            .driver("Юсь Сергій")
            .domesticCompany("АТ")
            .status(READY)
            .ekmt("DU, HU, IT 2023")
            .truckType(REF)
            .operationalStatusCommentDateTime(LocalDateTime.of(2023, 12, 21, 11, 22))
            .operationalStatusComment("Рух за призначенням")
            .isVisaGbr(true)
            .build();

    public static final Truck TRUCK2 = Truck.builder()
            .id(2L)
            .equipage("AA1111BB/CC2222BB")
            .driver("Ось Юрій")
            .domesticCompany("ВП")
            .status(READY)
            .ekmt("DU, HU, IT 2023")
            .truckType(REF)
            .operationalStatusCommentDateTime(LocalDateTime.of(2023, 12, 1, 10, 22))
            .operationalStatusComment("Рух за призначенням")
            .build();

    public static final String NOT_READY = "НЕГОТ";
    public static final Truck TRUCK3 = Truck.builder()
            .id(3L)
            .truckType(REF)
            .status(NOT_READY)
            .operationalStatusCommentDateTime(LocalDateTime.now())
            .operationalStatusComment("Просто стоїть")
            .isVisaGbr(true)
            .build();

    public static final Truck TRUCK4 = Truck.builder()
            .id(4L)
            .status(NOT_READY)
            .driver("Юсь О.А.")
            .domesticCompany("АТ")
            .operationalStatusCommentDateTime(LocalDateTime.now())
            .operationalStatusComment("Стоїть, як вкопаний")
            .build();
    private static final Truck TRUCK5 = Truck.builder()
            .id(5L)
            .status(NOT_READY)
            .equipage("AA1111ss/GC2222NN")
            .driver("Юсь А.А.")
            .domesticCompany("ВП")
            .operationalStatusCommentDateTime(LocalDateTime.of(2023, 11, 11, 11, 11))
            .operationalStatusComment("Став і стоїть")
            .dangerousStatus("ADR")
            .truckType("Відкрита платформа")
            .build();
    public static final Truck TRUCK6 = Truck.builder()
            .id(6L)
            .status(READY)
            .equipage("AA1212SS/LL5555DD")
            .driver("Шило С.А.")
            .domesticCompany("АТ")
            .operationalStatusCommentDateTime(LocalDateTime.of(2023, 12, 20, 11, 11))
            .operationalStatusComment("Перетнув кордон у Дорогуцьку")
            .truckType("реф")
            .build();
    public static final Truck TRUCK7 = Truck.builder()
            .id(7L)
            .status(READY)
            .equipage("АА1277SS/LL5665DD ")
            .driver("Бондаренко С.")
            .domesticCompany("АТ")
            .operationalStatusCommentDateTime(LocalDateTime.of(2023, 12, 20, 11, 11))
            .operationalStatusComment("Під Ла-Маншем")
            .truckType("реф")
            .build();

    private static final Set<Truck> TRUCKS = Set.of(
            TRUCK1, TRUCK2, TRUCK3, TRUCK4, TRUCK5, TRUCK6, TRUCK7
    );

    public Collection<Truck> findAll() {
        return TRUCKS;
    }

    public Optional<Truck> findById(Long id) {
        return TRUCKS.stream()
                .filter(t -> Objects.equals(id, t.getId()))
                .findAny();
    }
}
