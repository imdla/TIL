package org.example;

public class MP3Player {
    public String modelName;
    public int volume;
    public boolean isOn;

    public MP3Player(String modelName) {
        this.modelName = modelName;
        this.volume = 0;
        this.isOn = false;
    }

    boolean pushPowerButton() {
        if (this.isOn) {
            return this.turnOff();
        } else {
            return this.turnOn();
        }
    }

    public boolean turnOn() {
        this.isOn = true;
        this.volume = 40;
        return this.isOn;
    }

    public boolean turnOff() {
        this.isOn = false;
        this.volume = 0;
        return this.isOn;
    }

    public int volumeUp() {
        if (!isOn) {
            return 0;
        }
        this.volume += 20;
        if (this.volume > 100) {
            this.volume = 100;
        }
        return this.volume;
    }

    public int volumeDown() {
        if (!isOn) {
            return 0;
        }
        this.volume -= 20;
        if (this.volume < 0) {
            this.volume = 0;
        }
        return this.volume;
    }

    public void showInfo() {
        System.out.println("Model Name: " + this.modelName);
        System.out.println("Volume: " + this.volume);
        System.out.println("Is On: " + this.isOn);
    }
}
