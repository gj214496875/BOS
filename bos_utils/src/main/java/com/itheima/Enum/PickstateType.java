package com.itheima.Enum;

public enum PickstateType {
    PICKSTATE_NO() {
        @Override
        public String toString() {
            return "未取件";
        }
    },
    PICKSTATE_RUNNING() {
        @Override
        public String toString() {
            return "取件中";
        }
    },
    PICKSTATE_YES() {
        @Override
        public String toString() {
            return "已取件";
        }
    }
}
