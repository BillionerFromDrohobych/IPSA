package misterpanchak.com.mapich;

public class City {
    private String Name;
    private int imgUrl;
    private int imgUrl1;
    private int imgUrl2;
    private String location;
    private String desctription;
    private boolean sightorcity;
    private String adress;
    private double longtitude;
    private double latitude;



    public City(String name, int imgUrl,int imgUrl1,int imgUrl2, String location, String desctriptionk,boolean sightorcity, String adress, double longtitude, double latitude) {
        Name = name;
        this.imgUrl = imgUrl;
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.sightorcity = sightorcity;
        this.adress = adress;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.location = location;
        this.desctription = desctription;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctriptionk) {
        this.desctription = desctriptionk;
    }

    public boolean getSightorcity() {
        return sightorcity;
    }

    public void setSightorcity(boolean sightorcity) {
        this.sightorcity = sightorcity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(int imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public int getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(int imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }
    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}