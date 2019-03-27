package com.example.digitalchecker;

public class Gas_readings {
    public int gas_reading;
    public double humidity;
    public double temp;
    public String objectId;
    public String foo;

    public int getGas_reading() {
        return gas_reading;
    }

    public void setGas_reading(int gas_reading) {
        this.gas_reading = gas_reading;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
