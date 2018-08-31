package com.itheima.Enum;

public enum OrderType {
    ORDERTYPE_AUTO() {
        @Override
        public String toString() {
            return "自动分单";
        }
    }, ORDERTYPE_MAN() {
        @Override
        public String toString() {
            return "人工分单";
        }
    }
}
