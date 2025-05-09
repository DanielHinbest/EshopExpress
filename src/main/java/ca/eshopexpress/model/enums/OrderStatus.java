package ca.eshopexpress.model.enums;

/**
 * Enum for each of the possible order statuses
 * @author Daniel Hinbest
 * @version 1.0
 * @since 2025-05-09
 */
public enum OrderStatus {
    /**
     * Order is pending
     */
    PENDING,
    /**
     * Order is processing
     */
    PROCESSING,
    /**
     * Order has shipped
     */
    SHIPPED,
    /**
     * Order has been delivered
     */
    DELIVERED,
    /**
     * Order has been cancelled
     */
    CANCELLED,
    /**
     * Order has been returned
     */
    RETURNED,
    /**
     * Order has failed
     */
    FAILED
}
