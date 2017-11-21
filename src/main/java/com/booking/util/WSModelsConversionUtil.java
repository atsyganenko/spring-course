package com.booking.util;

import com.booking.booking_web_service.Auditorium;
import com.booking.booking_web_service.Event;
import com.booking.booking_web_service.Rate;
import com.booking.booking_web_service.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Anastasiia Tsyganenko
 * on 11/20/2017.
 */
public class WSModelsConversionUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static User convertToWsUser(com.booking.beans.models.User userToConvert) {
        if (userToConvert == null) {
            return null;
        }
        User result = new User();
        result.setId(userToConvert.getId());
        result.setName(userToConvert.getName());
        result.setEmail(userToConvert.getEmail());
        result.setBirthday(userToConvert.getBirthday().format(DATE_FORMATTER));
        result.setRoles(userToConvert.getRoles());
        return result;
    }

    public static com.booking.beans.models.User convertToUser(User wsUser) {
        if (wsUser == null) {
            return null;
        }
        com.booking.beans.models.User result = new com.booking.beans.models.User();
        result.setId(wsUser.getId());
        result.setName(wsUser.getName());
        result.setEmail(wsUser.getEmail());
        result.setBirthday(LocalDate.parse(wsUser.getBirthday()));
        result.setRoles(wsUser.getRoles());
        result.setEncryptedPassword(wsUser.getEncryptedPassword());
        return result;
    }

    public static Event convertToWsEvent(com.booking.beans.models.Event eventToConvert) {
        if (eventToConvert == null) {
            return null;
        }
        Event event = new Event();
        event.setId(eventToConvert.getId());
        event.setName(eventToConvert.getName());
        event.setAuditorium(convertToWsAuditorium(eventToConvert.getAuditorium()));
        event.setDateTime(eventToConvert.getDateTime().format(DATE_TIME_FORMATTER));
        event.setPrice(eventToConvert.getBasePrice());
        event.setRate(Rate.valueOf(eventToConvert.getRate().name()));
        return event;
    }

    public static com.booking.beans.models.Event convertToEvent(Event wsEvent) {
        if (wsEvent == null) {
            return null;
        }
        com.booking.beans.models.Event event = new com.booking.beans.models.Event();
        event.setId(wsEvent.getId());
        event.setName(wsEvent.getName());
        event.setAuditorium(convertToAuditorium(wsEvent.getAuditorium()));
        event.setDateTime(LocalDateTime.parse(wsEvent.getDateTime()));
        event.setBasePrice(wsEvent.getPrice());
        event.setRate(com.booking.beans.models.Rate.valueOf(wsEvent.getRate().name()));
        return event;
    }

    private static Auditorium convertToWsAuditorium(com.booking.beans.models.Auditorium auditoriumToConvert) {
        if (auditoriumToConvert == null) {
            return null;
        }
        Auditorium result = new Auditorium();
        result.setId(auditoriumToConvert.getId());
        result.setName(auditoriumToConvert.getName());
        result.setSeatsNumber(auditoriumToConvert.getSeatsNumber());
        result.setVipSeats(auditoriumToConvert.getVipSeats());
        return result;
    }

    private static com.booking.beans.models.Auditorium convertToAuditorium(Auditorium wsAuditorium) {
        if (wsAuditorium == null) {
            return null;
        }
        com.booking.beans.models.Auditorium result = new com.booking.beans.models.Auditorium();
        result.setId(wsAuditorium.getId());
        result.setName(wsAuditorium.getName());
        result.setSeatsNumber(wsAuditorium.getSeatsNumber());
        result.setVipSeats(wsAuditorium.getVipSeats());
        return result;
    }
}
