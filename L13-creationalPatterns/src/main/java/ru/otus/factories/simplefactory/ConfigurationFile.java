package ru.otus.factories.simplefactory;

/**
 * @author sergey created on 19.09.18.
 */
public class ConfigurationFile implements Configuration {
    @Override
    public String params() {
        return "params from file";
    }
}
