package de.dlh.lhind.warehouse.shared.fault.model;

import org.springframework.http.HttpStatus;

public class BadRequest extends CustomException {
    public BadRequest(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
