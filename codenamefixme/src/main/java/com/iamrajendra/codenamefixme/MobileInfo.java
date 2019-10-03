package com.iamrajendra.codenamefixme;

public class MobileInfo {
    private String manufacturer;
    private String board;
    private  String hardware;
private String brand;
private String serialNumber;
private int osVersion;

    public int getOsVersion() {
        return osVersion;
    }

    public String getBrand() {
        return brand;
    }

    public MobileInfo setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public MobileInfo setManufacturerName(String vendorName) {
        this.manufacturer = vendorName;

        return this;
    }

    public String getBoard() {
        return board;
    }

    public MobileInfo setBoard(String version) {
        this.board = version;
        return this;
    }

    public String getHardware() {
        return hardware;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public MobileInfo setHardware(String osVersion) {
        this.hardware = osVersion;
        return this;
    }

    public MobileInfo setSerial(String serial) {
        this.serialNumber = serial;
        return this;
    }

    public MobileInfo setOSVersion(int sdkInt) {
        this.osVersion = sdkInt;
        return this;
    }
}
