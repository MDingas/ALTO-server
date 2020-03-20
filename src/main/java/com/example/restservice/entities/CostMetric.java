package com.example.restservice.entities;

/**
 * Identifies what a cost represents
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt chapter 6.1.1
 * and https://www.ietf.org/id/draft-ietf-alto-performance-metrics-09.txt
 */
public enum CostMetric {
    ONE_WAY_DELAY,
    ROUND_TRIP_DELAY,
    PACKET_DELAY_VARIATION,
    HOP_COUNT,
    PACKET_LOSS,
    THROUGHPUT,
    MAX_RESERVABLE_BANDWIDTH,
    RESIDUE_BANDWIDTH
}
