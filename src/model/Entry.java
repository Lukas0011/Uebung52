package model;

import java.util.ArrayList;
import java.util.Collections;

public class Entry {
    private String name;
    private String street;

    public String getStreet() {
        return street;
    }

    private String district;
    private String location;
    private String note;
    private String mail;
    private String website;

    private ArrayList<String> phoneNumbers = new ArrayList<>();

    public Entry(String name, String street, String district, String location, String note, String mail, String website, String... phone) {
        this.name = name;
        this.street = street;
        this.district = district;
        this.location = location;
        this.note = note;
        this.mail = mail;
        this.website = website;

        if(phone != null) {
            Collections.addAll(phoneNumbers, phone);
        }
    }

    public String[][] allInformation() {
        String[][] information = new String[2][];

        information[0] = new String[]{name, street, district, location, note, mail, website};

        information[1] = new String[phoneNumbers.size()];

        for(int i = 0; i < information[1].length; ++i) {
            information[1][i] = phoneNumbers.get(i);
        }

        return information;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}
