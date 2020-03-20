package com.example.restservice.entities.interfaces;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/**
 * Aggregates a list of endpoint addresses into a single PID value
 *
 * For example:
 *
 * "PID1" : "192.0.2.0/24", "198.51.100.0/25"
 * "PID2" : "198.51.100.128/25"
 * "PID3" : "0.0.0.0/0"
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt
 */
public interface NetworkMap<T> extends Map<T, List<InetAddress>> {
}
