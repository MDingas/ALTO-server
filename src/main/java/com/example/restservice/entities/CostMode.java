package com.example.restservice.entities;

/**
 * How to interpret the values of a given cost map
 *
 * Further defined in https://www.rfc-editor.org/rfc/rfc7285.txt chapter 6.1.2
 */

public enum CostMode {
    NUMERICAL, // 1, 2, 3.0, etc
    ORDINAL, // numerical value that states preference. The smallest the number, the higher the preference
    NOMINAL // String values
}