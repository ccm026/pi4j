package com.zonkware.pi4led.controller;


import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LedController {

    private static GpioPinDigitalOutput pin;

    @RequestMapping ("/")
    public String greeting() {
        return "Hello !";
    }

    @RequestMapping ("/toggle")
    public String toggle() {
        getPin().toggle();

        return "OK";
    }

    private String checkstate(){
        return  (getPin().isHigh() ? "Light is ON" : "Light is OFF");
    }

    @RequestMapping("/on")
    public String on(){
        getPin().high();

        return checkstate();
    }

    @RequestMapping("/off")
    public String off() {
        getPin().low();

        return checkstate();
    }

    @RequestMapping ("/blink")
        public String blink (){
            getPin().blink(200L,5000L);

            return "Light is blinking...";
    }

    @RequestMapping ("/pulse")
    public String pulse () {
        getPin().pulse(5000L);

        return "Light is pulsing...";
    }

    public static GpioPinDigitalOutput getPin() {
        if (pin==null){
            GpioController gpio = GpioFactory.getInstance();
            pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
        }

        return pin;

    }
}
