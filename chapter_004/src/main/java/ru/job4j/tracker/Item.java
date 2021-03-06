package ru.job4j.tracker;

import java.util.List;

/**
 * Item.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Item {
    private String id;
    private String name;
    private String desc;
    private long created;
    private List<String> comments;

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return this.created;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getComments() {
        return this.comments;
    }

}
