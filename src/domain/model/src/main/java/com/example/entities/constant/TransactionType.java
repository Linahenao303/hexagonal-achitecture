package com.example.entities.constant;

public enum TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER;

    public static TransactionType fromString(String type) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.name().equalsIgnoreCase(type)) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Unknown transaction type: " + type);
    }
}