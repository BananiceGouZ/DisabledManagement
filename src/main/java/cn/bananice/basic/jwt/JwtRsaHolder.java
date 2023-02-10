package cn.bananice.basic.jwt;

public enum JwtRsaHolder {
    INSTANCE;

    private byte[] jwtRsaPriData;
    private byte[] jwtRsaPubData;

    public byte[] getJwtRsaPriData() {
        return jwtRsaPriData;
    }

    public void setJwtRsaPriData(byte[] jwtRsaPriData) {
        this.jwtRsaPriData = jwtRsaPriData;
    }

    public byte[] getJwtRsaPubData() {
        return jwtRsaPubData;
    }

    public void setJwtRsaPubData(byte[] jwtRsaPubData) {
        this.jwtRsaPubData = jwtRsaPubData;
    }
}
