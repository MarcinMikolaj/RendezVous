package project.rendezvous.panel.localization;

import org.springframework.stereotype.Service;

@Service
public class GeoLocalizationService {

    // Radius of the earth
    private final int R = 6371;

    public int getDistanceBetweenUsers(GeoLocalization firstLocalization, GeoLocalization secondLocalization){

        Double distance = CalculateDistanceBetweenUsers(firstLocalization, secondLocalization);

        int toReturn = (int) Math.round(distance);

        return toReturn;
    }

    // Haversine formula to find distance between two points on a sphere
    private double CalculateDistanceBetweenUsers(GeoLocalization firstLocalization, GeoLocalization secondLocalization){

        Double lat1 = firstLocalization.getLatitude();
        Double lon1 = firstLocalization.getLongitude();
        Double lat2 = secondLocalization.getLatitude();
        Double lon2 = secondLocalization.getLongitude();

        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                           Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double distance = R * c;

        return distance;
    }

    private Double toRad(Double value) {
        return value * Math.PI / 180;
    }



    // A method of testing a function for a specific location
    public void test(){
        GeoLocalization secondLocalization = new GeoLocalization( 39.7645187, -104.9951948);
        GeoLocalization firstLocalization = new GeoLocalization( 40.6976637, -74.1197643);

        double distance = getDistanceBetweenUsers(firstLocalization, secondLocalization);

        // The distance between two lat and long is::2609.591643180815
        System.out.println("The distance between two lat and long is::" + distance);

    }
    // A method of testing a function for a specific location
    public void test2(){
        GeoLocalization secondLocalization = new GeoLocalization( 25.7825453, -80.2994985);
        GeoLocalization firstLocalization = new GeoLocalization( 40.6976637, -74.1197643);

        double distance = getDistanceBetweenUsers(firstLocalization, secondLocalization);

        // The distance between two lat and long is::1753.9953107681392
        System.out.println("The distance between two lat and long is::" + distance);

    }

}
