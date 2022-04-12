package ru.netology.sender;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;



class MessageSenderImplTest {
    @Test
    void messageRussia() {
        Map<String, String> headers = new HashMap<String, String>();
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn( "Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void messageUSA() {
        Map<String, String> headers = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String expected = "Welcome";
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected,
                actual);
    }
}