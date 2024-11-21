package com.example.entities;

public class Account {
    private Integer id;
    private Integer number;
    private String type;
    private Integer userId;

    public Account(Integer id, Integer number, String type, Integer userId) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public static class Builder {
        private Integer id;
        private Integer number;
        private String type;
        private Integer userId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Account build() {
            return new Account(id, number, type, userId);
        }
    }
}
