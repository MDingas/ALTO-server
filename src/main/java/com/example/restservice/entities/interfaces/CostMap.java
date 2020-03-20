package com.example.restservice.entities.interfaces;

/**
 * Maps a printable cost between two endpoints defined by PIDS
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt chapter 6
 */
public interface CostMap<T, V> {
    V getCost(T src,T dst);
    void setCost(T src, T dst, String cost);
    void clear();
}
