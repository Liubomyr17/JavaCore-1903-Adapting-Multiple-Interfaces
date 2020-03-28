package com.company;

/*

1903 Adapting Multiple Interfaces
Adapt IncomeData to Customer and Contact.
The adapter class is IncomeDataAdapter.
Initialize countries before starting the program. Matching country code and name:
UA Ukraine
RU Russia
CA Canada
Add a telephone number with zeros to 10 digits if necessary (see examples).
Pay attention to the output format of the surname and name of the person.

Requirements:
1. The Solution class must contain a public static field of countries of type Map.
2. In the static block of the Solution class, initialize the countries field with test data according to the task.
3. The IncomeDataAdapter class must implement the Customer and Contact interfaces.
4. The IncomeDataAdapter class must contain a private data field of type IncomeData.
5. The IncomeDataAdapter class must contain a constructor with the IncomeData parameter.
6. In the IncomeDataAdapter class, implement the methods of the Customer and Contact interfaces using prompts in the form of comments in the interfaces.
 */

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("PA", "Passia");
        countries.put("CA", "Canada");
    }
    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;
        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getName() {
            return data.getContactLastName()+","+data.getContactFirstName();
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }

        @Override
        public String getPhoneNumber() {
            String full = String.format("%010d", data.getPhoneNumber());
            String telPart1 = "+" + data.getCountryPhoneCode();
            String telPart2 = full.substring(0, 3);
            String telPart3 = full.substring(3, 6);
            String telPart4 = full.substring(6, 8);
            String telPart5 = full.substring(8, 10);
            String telFormated = String.format("%s(%s)%s-%s-%s", telPart1, telPart2, telPart3, telPart4, telPart5);
            return telFormated;
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}



