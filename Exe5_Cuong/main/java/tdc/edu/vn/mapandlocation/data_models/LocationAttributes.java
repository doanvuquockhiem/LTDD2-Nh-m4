package tdc.edu.vn.mapandlocation.data_models;
import android.os.Bundle;
public class LocationAttributes {
    private String countryCode, countryName, addressKine, featureName, locality, postalCode;
    private Bundle bundle;

    public LocationAttributes(String countryCode, String countryName, String addressKine, String featureName, String locality, String postalCode, Bundle bundle) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.addressKine = addressKine;
        this.featureName = featureName;
        this.locality = locality;
        this.postalCode = postalCode;
        this.bundle = bundle;
    }

    public LocationAttributes() {

    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAddressKine() {
        return addressKine;
    }

    public void setAddressKine(String addressKine) {
        this.addressKine = addressKine;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
