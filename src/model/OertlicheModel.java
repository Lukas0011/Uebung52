package model;

import java.util.ArrayList;
import java.util.Collections;

public class OertlicheModel implements TestData {
    private ArrayList<Entry> entries = new ArrayList<>();

    public OertlicheModel() {
        Collections.addAll(entries, TestData.TESTDATA);
    }

    public Entry[] search(String mode, String name, String location, String phoneNumber) {
        if(mode.equals("Standardsuche")) {
            return standardSearch(name, location);
        } else if(mode.equals("Rückwärtssuche")) {
            return backwardsSearch(phoneNumber);
        } else {
            return null;
        }
    }

    private Entry[] backwardsSearch(String phoneNumber) {
        ArrayList<Entry> results = new ArrayList<>();

        for(Entry e : entries) {
            for(String p : e.getPhoneNumbers()) {
                if(phoneNumber.equals(p)) {
                    results.add(e);
                }
            }
        }

        return results.toArray(Entry[]::new);
    }

    private Entry[] standardSearch(String name, String district) {
        if(name.isEmpty() || district.isEmpty()) return null;

        ArrayList<Entry> results = new ArrayList<>();

        for (Entry e : entries) {
            if(e.getName().toLowerCase().contains(name.toLowerCase()) && e.getDistrict().toLowerCase().contains(district.toLowerCase())) {
                results.add(e);
            }
        }

        return results.toArray(Entry[]::new);
    }

    public String[] entryInformation(String resultInformation) {
        if(resultInformation == null) return null;

        String name = resultInformation.split(", ")[0];
        String street = resultInformation.split(", ")[1];

        Entry result = null;

        for (Entry e : entries) {
            if(e.getName().toLowerCase().equals(name.toLowerCase()) && e.getStreet().toLowerCase().equals(street.toLowerCase())) {
                result = e;
            }
        }

        if(result != null) {
            String[][] information = result.allInformation();

            String[] returnInformation = new String[information[0].length + information[1].length];

            int index = 0;

            for(int i = 0; i < information.length; ++i) {
                for(int j = 0; j < information[i].length; ++j) {
                    if(information[i][j] == null) {
                        returnInformation[index] = "";
                    } else {
                        returnInformation[index] = information[i][j];
                    }

                    index++;
                }
            }

            return returnInformation;
        }

        return null;
    }
}
