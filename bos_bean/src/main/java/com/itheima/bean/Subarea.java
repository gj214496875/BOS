package com.itheima.bean;

public class Subarea {
    private String id;
    private String addresskey;
    private String startnum;
    private String endnum;
    private String single;
    private String position;
    private Decidedzone decidedzone;
    private Region region;
    private String subareaId = null;

    public String getSubareaId(){
        subareaId = this.id;
        return subareaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        this.addresskey = addresskey;
    }

    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum;
    }

    public String getEndnum() {
        return endnum;
    }

    public void setEndnum(String endnum) {
        this.endnum = endnum;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (addresskey != null ? addresskey.hashCode() : 0);
        result = 31 * result + (startnum != null ? startnum.hashCode() : 0);
        result = 31 * result + (endnum != null ? endnum.hashCode() : 0);
        result = 31 * result + (single != null ? single.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    public Decidedzone getDecidedzone() {
        return decidedzone;
    }

    public void setDecidedzone(Decidedzone decidedzone) {
        this.decidedzone = decidedzone;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
