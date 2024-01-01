package com.agrotep.imp.exp.service.converter;

import com.agrotep.imp.exp.dto.AlertDto;
import com.agrotep.imp.exp.entity.Alert;
import com.agrotep.imp.exp.repository.PersonRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.agrotep.imp.exp.repository.PersonRepository.PEOPLE_BACKGROUND_CLASSES;
import static com.agrotep.imp.exp.service.converter.TransportationDtoConverter.TRANSPORTATION_DATE_TIME_FORMATTER;

@Mapper(componentModel = "spring")
public abstract class AlertDtoConverter {
    @Autowired
    protected PersonRepository personRepository;
    @Mapping(target = "creatorName", source = "a.creator.name")
    public abstract AlertDto toAlertDto(Alert a);
    public abstract List<AlertDto> toAlertDto(List<Alert> a);

    public abstract Alert toAlert(AlertDto dto);

    @AfterMapping
    protected void toStringFields(Alert a, @MappingTarget AlertDto dto) {
        String background = PEOPLE_BACKGROUND_CLASSES.get(a.getCreator());
        dto.setBackgroundClass(background);

        dto.setDateStr(a.getTime().format(TRANSPORTATION_DATE_TIME_FORMATTER));

        String name = a.getCreator().getName();
        String[] names = name.split(" ");
        if (names.length > 1) {
            name = String.format("%s %c.", names[0], names[1].charAt(0));
        }
        dto.setCreatorName(name);

        String text = a.getText();
        if (StringUtils.hasText(text)) {
            if (text.contains("\r\n")) {
                dto.setText(text.replace("\r\n", "<br />\r\n"));
            } else {
                dto.setText(text.replace("\n", "<br />\n"));
            }
        }
    }

    @AfterMapping
    protected void parseStrings(AlertDto dto, @MappingTarget Alert a) {
        personRepository.findByName(dto.getCreatorName())
                        .ifPresent(a::setCreator);
        String text = dto.getText();
        if (StringUtils.hasText(text) && !text.contains("<br")) {
            a.setText(text.replace("\r\n", "<br />")
                    .replace("\n", "<br />"));
        }
    }
}
