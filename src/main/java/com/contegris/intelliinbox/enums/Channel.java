package com.contegris.intelliinbox.enums;
public enum Channel {

    ALL("All"),
    CALL("Call"),
    WHATSAPP("Whatsapp"),
    INSTAGRAM_DM("Insta-Dm"),
    INSTAGRAM_Post("Insta-Post"),
    FACEBOOK_DM("Fb-DM"),
    FACEBOOK_Post("Fb-Post"),
    EMAIL("Email"),
    LINKEDIN("Li-Post"),
    SMS("SMS"),
    WEBCHAT("Chat");


    private final String label;
    // label is the bridge between your clean Java enum name and the messy real-world string inside the app.
    Channel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}