package com.carpark.carparks.dtos;

import com.google.gson.Gson;
import lombok.*;


public class BookingMsg {
    private String carParkNo;
    private Integer quality;
    private String msg;

    public String getCarParkNo() {
        return carParkNo;
    }

    public void setCarParkNo(String carParkNo) {
        this.carParkNo = carParkNo;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BookingMsg [" +
                "carParkNo='" + carParkNo + '\'' +
                ", quality=" + quality +
                ", msg='" + msg + '\'' +
                ']';
    }
}
