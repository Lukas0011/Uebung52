package model;

import java.util.ArrayList;

public interface TestData {
    Entry[] TESTDATA = new Entry[]{
            new Entry("Alex", "Teststraße 7", "12345 Testort", "Bechen", "Notiz 1", "@mail.com", ".com", "987123871", "123123848"),
            new Entry("Benni", "Teststraße 9", "54321 Test", "Bechen", "Notiz 2", null, ".com", "987123871", "123123848"),
            new Entry("Max", "Teststraße 97", "55555 Prüfung", "Bechen", null, "@mail.com", null, null),
            new Entry("Johann", "Teststraße 979", "18475 Beispiel", null, "Notiz 3", "@mail.com", ".com", "987123871", "123123848")
    };
}
