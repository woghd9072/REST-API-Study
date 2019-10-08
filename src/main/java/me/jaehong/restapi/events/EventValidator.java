package me.jaehong.restapi.events;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {

    public void validate(EventDto eventDto, Errors errors) {
        if (eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            errors.rejectValue("basePrice", "wrongValue", "BasePrice is Wrong.");
            errors.rejectValue("maxPrice", "wrongValue", "MaxPrice is Wrong.");
            errors.reject("wrongPrices", "Values for prices are wrong");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if (endEventDateTime.isBefore(eventDto.getEndEventDateTime()) ||
        endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
        endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is Wrong.");
        }

        // TODO beginEventDateTime
        LocalDateTime beginEventDateTime = eventDto.getBeginEventDateTime();
        if (beginEventDateTime.isAfter(eventDto.getEndEventDateTime()) ||
                beginEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
                beginEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
            errors.rejectValue("beginEventDateTime", "wrongValue", "BeginEventDateTime is Wrong.");
        }
        // TODO closeEnrollmentDateTime
        LocalDateTime closeEnrollmentDateTime = eventDto.getCloseEnrollmentDateTime();
        if (closeEnrollmentDateTime.isBefore(eventDto.getBeginEnrollmentDateTime()) ||
                closeEnrollmentDateTime.isAfter(eventDto.getBeginEventDateTime()) ||
                closeEnrollmentDateTime.isAfter(eventDto.getEndEventDateTime())) {
            errors.rejectValue("closeEnrollmentDateTime", "wrongValue", "CloseEnrollmentDateTime is Wrong.");
        }

    }
}
