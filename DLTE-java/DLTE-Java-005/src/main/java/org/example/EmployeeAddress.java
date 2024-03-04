package org.example;

public class EmployeeAddress {
    private String houseName;
    private String areaName;
    private String localityName;
    private String pincode;

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "houseName='" + houseName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", localityName='" + localityName + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public EmployeeAddress(String houseName, String areaName, String localityName, String pincode) {
        this.houseName = houseName;
        this.areaName = areaName;
        this.localityName = localityName;
        this.pincode = pincode;
    }

    public void displayAddress() {
        System.out.println("House Name: " + houseName);
        System.out.println("Area Name: " + areaName);
        System.out.println("Locality Name: " + localityName);
        System.out.println("Pincode : " + pincode);
    }
}

