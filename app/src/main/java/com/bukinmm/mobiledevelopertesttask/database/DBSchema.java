package com.bukinmm.mobiledevelopertesttask.database;



public class DBSchema {

    public static final class UsersTable{
        public static final String NAME = "users";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String LOGIN = "login";
            public static final String PASSWORD = "password";
        }
    }
    public static final class PropertiesTable{
        public static final String NAME = "properties";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String ADRESS = "adress";
            public static final String AREA = "area";
            public static final String PRICE = "price";
            public static final String NUMBER_OF_ROOMS = "numberOfRooms";
            public static final String PRICE_PER_METER = "pricePerMeter";
            public static final String FLOOR = "floor";
        }

    }
}
