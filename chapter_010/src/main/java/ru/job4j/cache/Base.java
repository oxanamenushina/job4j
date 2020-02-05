package ru.job4j.cache;

/**
 * Base.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Base {

    /**
     * Model ID.
     */
    private int id;

    /**
     * Version of the model.
     */
    private int version;

    /**
     * Model name.
     */
    private String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    /**
     * The method returns model ID.
     * @return model ID.
     */
    public int getId() {
        return id;
    }

    /**
     * The method returns version of the model.
     * @return version of the model.
     */
    public int getVersion() {
        return version;
    }

    /**
     * The method sets new version of the model.
     * @param version new version of the model.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * The method returns model name.
     * @return version of the model.
     */
    public String getName() {
        return name;
    }

    /**
     * The method sets new model name.
     * @param name new model name.
     */
    public void setName(String name) {
        this.name = name;
    }
}