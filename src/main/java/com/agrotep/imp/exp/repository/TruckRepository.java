package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Truck;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
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
            .operationalStatusComment(String.format("%s %s Рух за призначенням", LocalDate.now(), LocalTime.now()))
            .build();

    public static final Truck TRUCK2 = Truck.builder()
            .id(2L)
            .equipage("AA1111BB/CC2222BB")
            .driver("Ось Юрій")
            .domesticCompany("ВП")
            .status(READY)
            .ekmt("DU, HU, IT 2023")
            .truckType(REF)
            .operationalStatusComment(String.format("%s %s Рух за призначенням", LocalDate.now(), LocalTime.now()))
            .build();

    public static final String NOT_READY = "НЕГОТ";
    public static final Truck TRUCK3 = Truck.builder()
            .id(3L)
            .truckType(REF)
            .status(NOT_READY)
            .operationalStatusComment("Просто стоїть")
            .build();

    public static final Truck TRUCK4 = Truck.builder()
            .id(4L)
            .status(NOT_READY)
            .driver("Юсь О.А.")
            .domesticCompany("АТ")
            .operationalStatusComment("Стоїть, як вкопаний")
            .build();
    private static final Truck TRUCK5 = Truck.builder()
            .id(5L)
            .status(NOT_READY)
            .equipage("AA1111ss/GC2222NN")
            .driver("Юсь А.А.")
            .domesticCompany("ВП")
            .operationalStatusComment("Став і стоїть")
            .dangerousStatus("ADR")
            .truckType("Відкрита платформа")
            .build();

    private static final Set<Truck> TRUCKS = Set.of(
            TRUCK1, TRUCK2, TRUCK3, TRUCK4, TRUCK5
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
