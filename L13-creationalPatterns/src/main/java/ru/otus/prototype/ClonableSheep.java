package ru.otus.prototype;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 19.09.18.
 */
@SuppressWarnings({"java:S2975"})
class ClonableSheep implements Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(ClonableSheep.class);
    private String name;

    ClonableSheep(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public ClonableSheep clone() throws CloneNotSupportedException {
        ClonableSheep sheep = (ClonableSheep) super.clone();
        // ...
        logger.info("{}", sheep);
        return sheep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClonableSheep sheep = (ClonableSheep) o;
        return Objects.equals(name, sheep.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
