package com.example.entities;

import java.lang.ref.PhantomReference;

public class Transaction {
     private Long id;
     private String type;
     private Double amount;
     private Integer accountId;

        public Transaction(Long id, String type, Double amount, Integer accountId) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.accountId = accountId;
        }

        public Transaction() {
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public static class Builder {
        private Long id;
        private String type;
        private Double amount;
        private Integer accountId;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder accountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public Transaction build() {
            return new Transaction(id, type, amount, accountId);
        }
    }


}

