package br.com.maintenance.core.exceptions;

import org.hibernate.service.spi.ServiceException;

public class Exceptions {

    /**
     * @return Employee not found exception
     */
    public static ServiceException employeeNotFound() {
        return new ServiceException("Employee not found");
    }

    /**
     * @return Person not found exception
     */
    public static ServiceException personNotFound() {
        return new ServiceException("Person not found");
    }

    /**
     * @return Product not found exception
     */
    public static ServiceException productNotFound() {
        return new ServiceException("Product not found");
    }

    /**
     * @return Order not found exception
     */
    public static ServiceException orderNotFound() {
        return new ServiceException("Order not found");
    }

    /**
     * @return Order already started
     */
    public static ServiceException orderAlreadyStarted() {
        return new ServiceException("Order already started");
    }

    /**
     * @return Order already done
     */
    public static ServiceException orderAlreadyDone() {
        return new ServiceException("Order already done");
    }

    /**
     * @return Person's name already in use exception
     */
    public static ServiceException personNameAlreadyInUse() {
        return new ServiceException("Person's name already in use");
    }

    /**
     * @return Person's email already in use exception
     */
    public static ServiceException personEmailAlreadyInUse() {
        return new ServiceException("Person's email already in use");
    }

    /**
     * @return Person's phone already in use exception
     */
    public static ServiceException personPhoneAlreadyInUse() {
        return new ServiceException("Person's phone already in use");
    }
}
