package com.robertx22.config;

public interface ISerializedConfig {

    public abstract String fileName();

    public abstract String folder();

    public abstract void generateIfEmpty();

    public abstract void load();

    public default String getPath() {
        return folder() + "/" + fileName();
    }

}
