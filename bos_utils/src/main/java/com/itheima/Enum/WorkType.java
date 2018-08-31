package com.itheima.Enum;

public enum WorkType {
    NEW_ORDER(){
        @Override
        public String toString() {
            return "新单";
        }
    },
    TRACK_ORDER(){
        @Override
        public String toString() {
            return "追单";
        }
    },
    CHANGE_ORDER(){
        @Override
        public String toString() {
            return "改单";
        }
    },
    DESTROY_ORDER(){
        @Override
        public String toString() {
            return "销单";
        }
    }
}
