package de.dlh.lhind.warehouse.shared.fault.model;

import org.springframework.http.HttpStatus;

public final class ResourceNotFound extends CustomException {
    public ResourceNotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
