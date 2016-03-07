package com.baranovskiy.webapp.controller;

public class Filler {

    public class Url {

        public static final String DISTRIBUTOR        = "/distributor";
        public static final String PRODUCT            = "/product";
        public static final String SUPPLY             = "/linked";
        public static final String ALL                = "/all";
        public static final String SAVE_OR_UPDATE     = "/save-or-update";
        public static final String DISTRIBUTOR_UPDATE = "/update/distributor";
        public static final String PRODUCT_UPDATE     = "/update/product";

    }

    public class View {

        public static final String DISTRIBUTOR_VIEW        = "distributor";
        public static final String PRODUCT_VIEW            = "product";
        public static final String SUPPLY_DISTRIBUTOR_VIEW = "linked/distributor/supply";
        public static final String SUPPLY_PRODUCT_VIEW     = "linked/product/supply";

    }

    public class Message {

        public static final String SUCCESS_SAVE = "Model was successfully saved";
        public static final String SUCCESS_DELETE = "Model was successfully deleted";
        public static final String MODEL_NOT_FOUND = "Model not found";
        public static final String EMPTY_REPOSITORY = "No model in the repository";
        public static final String NONEXISTENT_MODEL = "Nonexistent distributor or product";

    }

}
