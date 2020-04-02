package com.example.restservice.exception;

public class MultipleInformationResourceDirectoriesException extends Exception {
    private static final String MESSAGE = "Multiple Information Resource Directories exist when only one should";
    private int existingIRDsCount;

    public MultipleInformationResourceDirectoriesException(int existingIRDsCount) {
        super(MESSAGE);
        this.existingIRDsCount = existingIRDsCount;
    }

    public int getExistingIRDsCount() {
        return existingIRDsCount;
    }
}
