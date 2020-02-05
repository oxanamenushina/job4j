package ru.job4j.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * NonBlockingCache.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingCache {

    /**
     * NonBlockingCache.
     */
    private ConcurrentHashMap<Integer, Base> cache = new ConcurrentHashMap<>();

    /**
     * The method adds the model to the cache.
     * @param model the model to add.
     * @return true - the model added to the cache,
     * false - the model didn't add to the cache.
     */
    public boolean add(Base model) {
        return model != null && this.cache.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * The method updates the model in the cache.
     * @param model the model to update.
     * @return true - the model updated,
     * false - the model didn't update.
     */
    public boolean update(Base model) {
        boolean result = false;
        if (model != null) {
            result = this.cache.computeIfPresent(model.getId(), (k, v) -> {
                if (v.getVersion() != model.getVersion()) {
                    throw new OptimisticException("Throw Exception in Thread");
                }
                model.setVersion(model.getVersion() + 1);
                return model;
            }) != null;
        }
        return result;
    }

    /**
     * The method deletes the model from the cache.
     * @param model the model to delete.
     * @return true - the model deleted,
     * false - the model didn't delete.
     */
    public boolean delete(Base model) {
        return model != null && this.cache.remove(model.getId(), model);
    }

    /**
     * The method returns a model with the specified id.
     * @param id model ID.
     * @return model with the specified id.
     */
    public Base get(int id) {
        return this.cache.get(id);
    }
}